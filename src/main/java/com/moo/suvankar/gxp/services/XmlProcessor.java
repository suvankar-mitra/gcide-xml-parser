/*
 * Copyright (C) 2025 Suvankar Mitra
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */

package com.moo.suvankar.gxp.services;

import com.moo.suvankar.gxp.models.DictionaryEntry;
import com.moo.suvankar.gxp.parser.XmlParser;
import com.moo.suvankar.gxp.utils.FileReaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class XmlProcessor {
    private static final Logger LOG = LoggerFactory
            .getLogger(XmlProcessor.class);

    private final XmlParser xmlParser;
    private final DictionaryEntryService dbService;

    public XmlProcessor(XmlParser xmlParser,
                        DictionaryEntryService dbService) {
        this.xmlParser = xmlParser;
        this.dbService = dbService;
    }

    public void processAndPersistXml(String fileName) {
        LOG.info("Parsing file {}",fileName);
        try {
            String xmlFileContent = FileReaderUtil.readFile(fileName);

            int pTagIndexStart = 0, pTagIndexEnd = 0;
            String pTagContent;
            DictionaryEntry prevEntry = null;

            List<DictionaryEntry> dictionaryEntries = new LinkedList<>();

            while ((pTagIndexStart = xmlFileContent.indexOf("<p>", pTagIndexStart)) != -1) {
                pTagIndexEnd = xmlFileContent.indexOf("</p>", pTagIndexStart + 3);
                if (pTagIndexEnd == -1)
                    break;

                pTagContent = xmlFileContent.substring(pTagIndexStart, pTagIndexEnd + 4);

                int pTagStartCount, pTagEndCount;
                pTagStartCount = countOccurrences(pTagContent, "<p>");
                pTagEndCount = countOccurrences(pTagContent, "</p>");

//                System.out.println(pTagContent);
//                System.out.println(pTagStartCount+" "+pTagEndCount);

                // more <p> tag inside <p></p> tag, keep reading
                while(pTagEndCount != pTagStartCount) {
                    pTagIndexEnd = xmlFileContent.indexOf("</p>", pTagIndexEnd + 4);
                    pTagContent = xmlFileContent.substring(pTagIndexStart, pTagIndexEnd + 4);
                    pTagStartCount = countOccurrences(pTagContent, "<p>");
                    pTagEndCount = countOccurrences(pTagContent, "</p>");
                }

                // pre processing to ignore some tags
                pTagContent = pTagContent
                        .replaceAll("(?s)<!--.*?-->", "") // remove all the comments
                        .replaceAll("<\\?/", "")// replace all (<?/) with ''
                        .replaceAll("<(\\w+)/", "{$1/}") // replace all <acr/ like patterns with /{acr/}
                        .replaceAll("(?i)\\{br\\s*/?}", "")
                        .replaceAll("&(?!\\w+;)", "&amp;") // ignore & characters
                        .replaceAll("<cs>", "").replaceAll("</cs>", "")
                        .replaceAll("<note>", "").replaceAll("</note>", "")
                ;

                Pattern incompleteCommentTagPattern = Pattern.compile("<p>.*?<!--(?!.*?-->).*?</p>",
                        Pattern.DOTALL);
                Matcher matcher = incompleteCommentTagPattern.matcher(pTagContent);
                if(matcher.find()) {
                    pTagIndexStart = pTagIndexEnd;
                    continue;
                }

                // get the definition
                DictionaryEntry entry = xmlParser.parseDefinition(pTagContent);
                if (entry != null) {
                    dictionaryEntries.add(entry);
                    prevEntry = entry;
                } else {
                    if (prevEntry != null) {
                        xmlParser.parseQuote(pTagContent, prevEntry);
                        xmlParser.parseMoreDefinition(pTagContent, prevEntry);
                        xmlParser.parseMoreSynonyms(pTagContent, prevEntry);
                    }
                }

                pTagIndexStart = pTagIndexEnd;
            }

            LOG.info("Parsing of file {} is complete, now persisting into Database.", fileName);
            // parsing is done, now persist into database
            saveIntoDatabase(dictionaryEntries);

            LOG.info("Database persist of {} complete.", fileName);

        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    private void saveIntoDatabase(List<DictionaryEntry> dictionaryEntries) {
        for(DictionaryEntry entry : dictionaryEntries) {
            dbService.save(entry);
        }
    }

    private static int countOccurrences(String str, String sub) {
        int count = 0, index = 0;

        while ((index = str.indexOf(sub, index)) != -1) {
            count++;
            index += sub.length();
        }

        return count;
    }
}

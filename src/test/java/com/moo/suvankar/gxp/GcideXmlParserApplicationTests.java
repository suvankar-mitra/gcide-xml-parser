package com.moo.suvankar.gxp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Locale;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import com.moo.suvankar.gxp.data.DictionaryEntry;
import com.moo.suvankar.gxp.repositories.DictionaryEntryRepository;

@SpringBootTest(classes = GcideXmlParserApplication.class)
@ActiveProfiles("test")
class GcideXmlParserApplicationTests {

	@Autowired
	private DictionaryEntryRepository dictionaryEntryRepository;

	@Test
	void testRetrieveDictionaryEntry() {

		List<DictionaryEntry> entries = dictionaryEntryRepository.findByEntryWord("abandon");
		assertEquals("abandon", entries.get(0).getEntryWord().toLowerCase(Locale.ENGLISH).trim());
	}

}

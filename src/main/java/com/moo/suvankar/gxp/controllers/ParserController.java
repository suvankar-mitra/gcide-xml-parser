package com.moo.suvankar.gxp.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moo.suvankar.gxp.services.XmlProcessor;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/v1")
public class ParserController {

    private static final Logger LOG = LoggerFactory
            .getLogger(ParserController.class);

    @Autowired
    private XmlProcessor xmlProcessor;

    @GetMapping("/parse")
    public ResponseEntity<HttpStatus> parse(@RequestParam String file) {
        Path directory = Paths.get("src/main/resources/GCIDE");

        try {
            if (file.equals("all")) {
                // loop though all CIDE.*.xml files
                try (Stream<Path> files = Files.list(directory)) {
                    files.filter(Files::isRegularFile)
                            .map(Path::getFileName)
                            .forEach(filePath -> xmlProcessor.processAndPersistXml(filePath.getFileName().toString()));
                }
            } else {
                xmlProcessor.processAndPersistXml(file);
            }
            LOG.info("Parsing completed");
        } catch (Exception e) {
            LOG.error("Fatal error {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ResponseEntity.ok(HttpStatus.OK);
    }

}

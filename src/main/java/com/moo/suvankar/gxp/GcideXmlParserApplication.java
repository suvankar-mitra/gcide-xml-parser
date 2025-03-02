package com.moo.suvankar.gxp;

import com.moo.suvankar.gxp.services.DictionaryEntryService;
import com.moo.suvankar.gxp.services.XmlProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@SpringBootApplication
public class GcideXmlParserApplication implements CommandLineRunner {

	private static Logger LOG = LoggerFactory
			.getLogger(GcideXmlParserApplication.class);

	@Autowired
	private XmlProcessor xmlProcessor;

	public static void main(String[] args) {
		SpringApplication.run(GcideXmlParserApplication.class, args);
	}

	@Override
	public void run(String... args) {

		// loop though all CIDE.*.xml files
		Path directory = Paths.get("src/main/resources/GCIDE");

		try (Stream<Path> files = Files.list(directory)) {
			files.filter(Files::isRegularFile)
					.map(Path::getFileName)
					.forEach(filePath -> xmlProcessor.processAndPersistXml(filePath.getFileName().toString()));
		} catch (IOException e) {
			LOG.error("Fatal error {}", e.getMessage());
			throw new RuntimeException(e);
		}


		//  xmlProcessor.processAndPersistXml("CIDE.A.xml");
	}

}

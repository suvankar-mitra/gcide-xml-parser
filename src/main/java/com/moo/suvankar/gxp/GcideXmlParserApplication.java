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

package com.moo.suvankar.gxp;

import com.moo.suvankar.gxp.services.XmlProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.moo.suvankar.gxp.repositories")
public class GcideXmlParserApplication implements CommandLineRunner {

	private static Logger LOG = LoggerFactory
			.getLogger(GcideXmlParserApplication.class);

	@Autowired
    private Environment environment;

	@Autowired
	private XmlProcessor xmlProcessor;

	public static void main(String[] args) {
		SpringApplication.run(GcideXmlParserApplication.class, args);
	}

	@Override
	public void run(String... args) {

		// This will make sure that for 'test' profile, we do not parse all the xml files
		if (environment.getActiveProfiles().length > 0) {
			for (String profile : environment.getActiveProfiles()) {
				if(profile.equals("test")) {
					LOG.info("Test profile active, only processing CIDE.A.xml");
					xmlProcessor.processAndPersistXml("CIDE.A.xml");
					return;
				}
			}
		} else {
			LOG.info("No active profile, processing all CIDE.*.xml files");
		}

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

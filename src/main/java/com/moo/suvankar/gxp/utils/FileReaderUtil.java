package com.moo.suvankar.gxp.utils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

public class FileReaderUtil {
    public static String readFile(String fileName) throws IOException, URISyntaxException {
        ClassLoader classLoader = FileReaderUtil.class.getClassLoader();
        return Files.readString(Paths.get(Objects.requireNonNull(classLoader.getResource("GCIDE/" + fileName)).toURI()));
    }
}

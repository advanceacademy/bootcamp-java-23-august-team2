package com.aacademy.moonlight.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ImageFileUtil {
    public static byte[] readImageDataFromFile(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        if (!Files.exists(path)){
            throw new IOException("File not found: " + filePath);
        }
        return Files.readAllBytes(path);
    }

}

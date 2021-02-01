package com.company;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUtility {

    public static void writeByte(byte[] data, String filename) throws IOException, IOException {
        Path filePath = Paths.get(filename);
        Files.write(filePath, data);
    }


}
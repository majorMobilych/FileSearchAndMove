package com.solutions;

import com.RunTests;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;

public class SolutionsImpl implements Solutions {

    @Override
    public void sortFilesUsingStreams(String sourceFileName, String currentFilesFolder,
                                      Map<String, String> identifiersAndFolders, String numberOfTestFolder) {
        try {
            Map<String, String> sourceFileNameToId = new HashMap<>();
            Files.lines(Paths.get(sourceFileName)).forEach(s ->
                    sourceFileNameToId.putIfAbsent(currentFilesFolder + s.substring(s.indexOf(":") + 1),
                            s.substring(0, s.indexOf(":"))));

            sourceFileNameToId.forEach((fileToTransportName, identifierName) -> {
                if (identifiersAndFolders.containsKey(identifierName)) {
                    if (Files.exists(Paths.get(identifiersAndFolders.get(identifierName)))) {
                        try {
                            Files.move(Paths.get(fileToTransportName),
                                    Paths.get(identifiersAndFolders.get(identifierName)),
                                    StandardCopyOption.REPLACE_EXISTING);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        /* if a directory, specified with an identifier doesn't exist -> ... */
                        try {
                            /* ... create it ... */
                            Files.createDirectories(Paths.get(RunTests.TESTS_FOLDER + numberOfTestFolder + "\\" +
                                    identifiersAndFolders.get(identifierName)));
                            /* ... move the file there */
                            Files.move(Paths.get(currentFilesFolder),
                                    Paths.get(identifiersAndFolders.get(identifierName)),
                                    StandardCopyOption.REPLACE_EXISTING);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    System.out.println("Identifier " + identifierName + "is not specified");
                }
            });
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}



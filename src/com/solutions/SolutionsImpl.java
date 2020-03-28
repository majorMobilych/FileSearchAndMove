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
            /* We create HashMap, where key is fileName and value is corresponding identifier
            *  we create a HashMap, because for each fileName there is only one corresponding identifier */
            Map<String, String> sourceFileContent = new HashMap<>();
            /* read sourceFile and write info to HashMap-sourceFileContent */
            Files.lines(Paths.get(sourceFileName)).forEach(s ->
                    sourceFileContent.putIfAbsent(currentFilesFolder + s.substring(/* use fileName as key, as far as there can not be
                    several identifiers for a single file. */s.indexOf(":") + 1),
                            /* identifier as value */s.substring(0, s.indexOf(":"))));


            //debug
            sourceFileContent.forEach((k, v) -> System.out.println("key=" + k + ";    value=" + v +"!"));



            /* for each (key, value) element of sourceFileContent we check, if there exists folder to transport file
            *  (key is file's name) */
            sourceFileContent.forEach((fileToTransportName, identifierName) -> {
                /* check if an identifier exists in identifiersAndFolders-HashMap  */
                if (identifiersAndFolders.containsKey(identifierName)) {



                    //debug
                    System.out.println("fileToTransportName =" + fileToTransportName + ";   identifierName" + identifierName);



                    /* check if folder, specified with the identifier, exists */
                    if (Files.exists(Paths.get(identifiersAndFolders.get(identifierName)))) {
                        try {
                            /* if folder exists -> move file there */
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



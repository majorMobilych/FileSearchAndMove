package com;

import com.solutions.Solutions;
import com.solutions.SolutionsImpl;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class RunTests {
    /* solve-object */
    private final Solutions solutions = new SolutionsImpl();
    /* path to file, where identifiers and folderNames are stored */
    private static String sourceFileName;
    /* path to folder, where all files are stored */
    private static String currentFilesFolder;
    public static final String TESTS_FOLDER = "C:\\Users\\Mihovel\\JavaProjects\\Algorithms\\RecursiveFileSearch\\tests\\";
    private static Map<String, String> identifiersAndFolders = new HashMap<>();

    private static void fillListForTest1() {
        identifiersAndFolders.put("Misha",
                "C:\\Users\\Mihovel\\JavaProjects\\Algorithms\\RecursiveFileSearch\\tests\\test1\\MishaFolder\\");
        identifiersAndFolders.put("Albert",
                "C:\\Users\\Mihovel\\JavaProjects\\Algorithms\\RecursiveFileSearch\\tests\\test1\\AlbertFolder\\");
    }

    @Test
    void test1() {
        sourceFileName = "C:\\Users\\Mihovel\\JavaProjects\\Algorithms\\RecursiveFileSearch\\tests\\test1\\sourceFile.txt";
        currentFilesFolder = "C:\\Users\\Mihovel\\JavaProjects\\Algorithms\\RecursiveFileSearch\\tests\\test1\\currentFilesFolder\\";
        fillListForTest1();
        solutions.sortFilesUsingStreams(sourceFileName, currentFilesFolder, identifiersAndFolders, "test1");
    }
}

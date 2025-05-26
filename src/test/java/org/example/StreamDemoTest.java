package org.example;


import org.junit.jupiter.api.*;
import java.io.*;
import static org.junit.jupiter.api.Assertions.*;

class StreamDemoTest {

    private static final String TEXT_FILE = "testfile.txt";
    private static final String COPY_FILE = "copyfile.txt";




    @Test
    void testWriteAndReadTextFile() throws IOException {
        String content = "Hello from Java!";
        StreamDemo.writeTextToFile(TEXT_FILE, content);

        String result = StreamDemo.readTextFromFile(TEXT_FILE);
        assertTrue(result.contains("Hello from Java"));
    }

    @Test
    void testCopyFileUsingBytes() throws IOException {
        String content = "Binary test";
        try (FileOutputStream out = new FileOutputStream(TEXT_FILE)) {
            out.write(content.getBytes());
        }

        StreamDemo.copyFileUsingBytes(TEXT_FILE, COPY_FILE);

        byte[] original = new FileInputStream(TEXT_FILE).readAllBytes();
        byte[] copy = new FileInputStream(COPY_FILE).readAllBytes();

        assertArrayEquals(original, copy);
    }

    @AfterEach
    void cleanUp() {
        new File(TEXT_FILE).delete();
        new File(COPY_FILE).delete();
    }
}


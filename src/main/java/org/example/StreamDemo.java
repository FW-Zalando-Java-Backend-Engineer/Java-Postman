package org.example;

import java.io.*;

/**
 * Demonstrates basic byte and character stream operations in Java.
 *
 */
public class StreamDemo {
    public static void main( String[] args ) {
        String filePath = "message.txt";
        String copyPath = "message_copy.txt";
        String message = "Dear Java Developers, \nWelcome to the world of Streams!\n";


        try {
            // Writing to a file
            System.out.println("Writing message to file...");
            writeTextToFile(filePath, message);

            // Reading from a file
            System.out.println("Reading message from file...");
            String result = readTextFromFile(filePath);
            System.out.println("Content read:\n"+ result);

            // Copying the file
            System.out.println("Copying the file using byte stream...");
            copyFileUsingBytes(filePath, copyPath);
            System.out.println("File copied successfully to: "+copyPath);

        } catch (IOException e) {
           System.err.println("An error occurred: "+ e.getMessage());
        }

    }

    /**
     * Copies a file using byte stream (suitable for binary files).
     * @param sourcePath Path to the original file you want to copy.
     * @param destPath Path whee you want to create the copied file.
     *
     * @throws IOException indicates that file-related errors will be thrown to the caller.
     * */
    public static void copyFileUsingBytes(String sourcePath, String destPath)  throws IOException {
        // Why Byte Stream:
        // because we don't want to interpret data as characters.
        // much faster.
        // The Byte Stream processes raw data as bytes, unlike character streams which decode to text.
        try(
                InputStream in = new BufferedInputStream(new FileInputStream(sourcePath));
                OutputStream out = new BufferedOutputStream(new FileOutputStream(destPath))
        ){
            // Core logic :
             // int size = 1024 * 1024;
            byte[] buffer = new byte[1024]; // 1KB buffer
            int length;
            while ((length = in.read(buffer)) > 0){
                // length = in.read(buffer) -> 350 at the end of the source file.
                // ['A', 'B', ...,'v', null, ....,null]
                out.write(buffer, 0,length); // only 350 bytes will be written
            }
        }
    }

    /**
     * Read text from a file using character stream.
     * */
    public static String readTextFromFile(String filePath) throws IOException{
        StringBuilder content = new StringBuilder();
        // FileReader: A Low-Level character stream reader for files.
        // BufferedReader: Adds a buffer to read chunks of characters at once,
        // better performance and enables methods like .readLine()
        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while((line = reader.readLine()) != null){
                content.append(line).append(System.lineSeparator());
                // using System.lineSeparator(): makes our code OS-agnostic.
            }
        }
        return content.toString();
    }

    /**
     * Writes a string to a file using character stream.
     * @param filePath the path of the file to write on.
     * @param content the String to be written
     * @throws IOException  e.g., if the file path is invalid.
     *
     * */
    public static void writeTextToFile(String filePath, String content) throws IOException{
        // Character stream with buffering for performance
        // FileWriter: it reads the filePath, and reach to the file then writes the characters to it.
        // FileWriter will create the file if it does not exist.
        // If the file exists, it will be overwritten.

        // BufferedWriter: Adds buffering, which greatly improves performance by reducing the number of disk write operations.
        // Instead of writing each character individually, it batches them and writes in chunks.

        //TIP: Buffered writes/readers are faster and more efficient than unbuffered ones,
        // especially when dealing with large content.
        try(BufferedWriter  writer = new BufferedWriter(new FileWriter(filePath))){
            writer.write(content);
        }

    }
}

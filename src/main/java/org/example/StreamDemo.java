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
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Writes a string to a file
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

package com.forallsecure.snappy_fuzz;

import org.xerial.snappy.Snappy;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.code_intelligence.jazzer.api.FuzzedDataProvider;
import com.code_intelligence.jazzer.junit.FuzzTest;

public class SnappyFuzz {

    public static void fuzzerTestOneInput(FuzzedDataProvider data) {
	
        /*
        String imageFileFP = args[0];
        Path p = Paths.get(imageFileFP);
        String imageFile = p.getFileName().toString();
        String directory = p.getParent().toString();

        int index = imageFile.lastIndexOf('.');
        String imageFileName = "";

        if (index == -1) {
            System.out.println("Error => " + imageFile + " has no extension?");
            System.exit(0);
        } else {
            imageFileName = imageFile.substring(0, index);
        }

        String compressedImageFile = directory + "/" + imageFileName + ".snappy";
        String decompressedImageFile = directory + "/new-" + imageFile;
	*/

        try {
            byte[] imageData = data.consumeRemainingAsBytes();
            byte[] compressedImageData = Snappy.compress(imageData);

            //FileOutputStream compressedStream = new FileOutputStream(compressedImageFile);
            //compressedStream.write(compressedImageData);
            //compressedStream.close();

            byte[] decompressedImageData = Snappy.uncompress(compressedImageData);

            // FileOutputStream decompressedStream = new FileOutputStream(decompressedImageFile);
            // decompressedStream.write(decompressedImageData);
            // decompressedStream.close();

            // System.out.println(imageFile + " => " + compressedImageFile + " => " + decompressedImageFile);
        } catch (IOException e) {
            //e.printStackTrace();
        }
    }
}

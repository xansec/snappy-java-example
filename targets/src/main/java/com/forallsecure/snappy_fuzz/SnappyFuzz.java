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
	
        try {
            byte[] imageData = data.consumeRemainingAsBytes();
            byte[] compressedImageData = Snappy.compress(imageData);
            byte[] decompressedImageData = Snappy.uncompress(compressedImageData);

            if (imageData.length != decompressedImageData.length) {
                throw new AssertionError("Decompressed data length does not match original data length");
            }
            
            for (int i = 0; i < imageData.length; i++) {
                if (imageData[i] != decompressedImageData[i]) {
                    throw new AssertionError("Decompressed data does not match original data at byte " + i);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

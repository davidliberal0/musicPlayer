package org.example;

import ws.schild.jave.*;
import ws.schild.jave.encode.AudioAttributes;
import ws.schild.jave.encode.EncodingAttributes;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("File Path: ");
        String filePath = scanner.nextLine();

        File originalFile = new File("src/main/java/org/example/" + filePath);
        boolean needsConversion = !filePath.toLowerCase().endsWith(".wav");

        File inputFile = originalFile;

        if (needsConversion) {
            try {
                inputFile = FileConverter.convertToWav(originalFile);
            } catch(EncoderException e) {
                System.out.println("Error converting file: " + e.getMessage());
                return;
            }
        }

        AudioPlayer.playAudio(inputFile);

    }
}
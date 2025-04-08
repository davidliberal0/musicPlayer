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

        boolean needsConversion;

        Scanner scanner = new Scanner(System.in);

        System.out.println("File Path: ");
        String filePath = scanner.nextLine();

        File originalFile = new File("src/main/java/org/example/" + filePath);
        File target;

        if (!filePath.toLowerCase().endsWith(".wav")) {
            needsConversion = true;
            String currentExtension = filePath.substring(filePath.lastIndexOf('.') + 1);
            String targetPath = filePath.replace(currentExtension, "wav");
            target = new File("src/main/java/org/example/"+ targetPath);

            AudioAttributes audio = new AudioAttributes();
            audio.setCodec("pcm_s16le");
            audio.setBitRate(128000);
            audio.setChannels(2);
            audio.setSamplingRate(44100);

            EncodingAttributes attrs = new EncodingAttributes();
            attrs.setInputFormat("wav");
            attrs.setAudioAttributes(audio);

            Encoder encoder = new Encoder();

            try {
                MultimediaObject source = new MultimediaObject(originalFile);
                encoder.encode(source, target, attrs);
                System.out.println("Conversion Complete: " + target.getAbsolutePath());
            } catch (EncoderException e) {
                e.printStackTrace();
            }
        } else {
            needsConversion = false;
        }

        File inputFile = new File("src/main/java/org/example/" +
                (needsConversion ? filePath.replace(filePath.substring(filePath.lastIndexOf('.') + 1), "wav") : filePath));

        try (AudioInputStream audioStream = AudioSystem.getAudioInputStream(inputFile)) {


            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);

            clip.start();
            while (!clip.isRunning()) {
                Thread.sleep(10); // wait for it to start
            }

            while (clip.isRunning()) {
                Thread.sleep(10);
            }

            clip.close();

        } catch (UnsupportedAudioFileException e) {
            System.out.println("Unsupported File type");
        } catch (IOException e) {
            System.out.println("Something went wrong");
        } catch (LineUnavailableException | InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            System.out.println("done");
            scanner.close();
        }
    }
}
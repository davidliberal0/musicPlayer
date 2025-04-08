package org.example;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class AudioPlayer {

    public static void playAudio(File audioFile) {
        try (AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile)) {

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
        }
    }

}

package org.example;

import ws.schild.jave.Encoder;
import ws.schild.jave.EncoderException;
import ws.schild.jave.MultimediaObject;
import ws.schild.jave.encode.AudioAttributes;
import ws.schild.jave.encode.EncodingAttributes;

import java.io.File;

public class FileConverter {
    public static File convertToWav(File originalFile) throws EncoderException {
        String targetPath = originalFile.getPath().replaceAll("\\.[^\\.]+$", ".wav");
        File target = new File("src/main/java/org/example/" + targetPath);

        AudioAttributes audio = new AudioAttributes();
        audio.setCodec("pcm_s16le");
        audio.setBitRate(128000);
        audio.setChannels(2);
        audio.setSamplingRate(44100);

        EncodingAttributes attrs = new EncodingAttributes();
        attrs.setInputFormat("wav");
        attrs.setAudioAttributes(audio);

        Encoder encoder = new Encoder();

        MultimediaObject source = new MultimediaObject(originalFile);
        System.out.println("Conversion Complete: " + target.getAbsolutePath());

        return target;

    }
}

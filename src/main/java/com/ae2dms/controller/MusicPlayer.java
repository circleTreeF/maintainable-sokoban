package com.ae2dms.controller;

import com.ae2dms.model.GameEngine;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;


/**
 * The project of AE2DMS Coursework of Yizirui FANG 20127091
 * <p>
 * Package: com.ae2dms.controller
 *
 * @description: This class is to play background music for this game
 * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
 * @date: 2020/11/24 22:45
 */

//TODO: could be refactored to apply javafx.media to be a better player
public class MusicPlayer {
    // to store current position
    Long currentFrame;
    Clip clip;

    // current status of clip
    Status status;

    AudioInputStream audioInputStream;
    static String musicPath;
//    File musicFile;
//    URL musicURL;

    /**
     * constructor
     * <p>
     * Set the audio stream for the specified music file and play the music
     *
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/25 16:02
     * @version: 1.0.0
     **/


    public MusicPlayer() {
        try {

            audioInputStream = AudioSystem.getAudioInputStream(MusicPlayer.class.getClassLoader().getResource("music/puzzle_theme.wav"));
            musicPath = MusicPlayer.class.getClassLoader().getResource("music/puzzle_theme.wav").getPath();
//            musicFile = new File(MusicPlayer.class.getClassLoader().getResource("music/puzzle_theme.wav").toURI());
//            //          musicFile = new File("https://raw.githubusercontent.com/circleTreeF/myImg/master/puzzle_theme.wav");
//            musicURL = MusicPlayer.class.getClassLoader().getResource("music/puzzle_theme.wav");
//            System.out.println("non arg getResource    " + MusicPlayer.class.getClassLoader().getResource("music/puzzle_theme.wav"));
//            System.out.println(musicFile);
//            System.out.println(musicFile.exists());
//            System.out.println(musicPath);
            // create clip reference
            clip = AudioSystem.getClip();

            // open audioInputStream to the clip
            clip.open(audioInputStream);

            clip.loop(Clip.LOOP_CONTINUOUSLY);
            status = Status.PLAY;
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
            if (GameEngine.isDebugActive()) {
                System.out.println(e);
            }
        }
    }


    /**
     * Construct the music playing the music file speficied by the input {@code musicFile}
     *
     * @param musicFile
     *         the specified music file need to be played as the background music
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/12/5 2:55
     * @version:
     **/

    public MusicPlayer(File musicFile) {
        try {
            //musicURL = new URL(musicFile.toString());
//            musicURL = musicFile.toURI().toURL();
            musicPath = String.valueOf(musicFile);
            System.out.println("Print file " + musicFile);
            audioInputStream = AudioSystem.getAudioInputStream(musicFile);
            // create clip reference
            clip = AudioSystem.getClip();

            // open audioInputStream to the clip
            clip.open(audioInputStream);

            clip.loop(Clip.LOOP_CONTINUOUSLY);
            status = Status.PLAY;
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
            if (GameEngine.isDebugActive()) {
                System.out.println(e);
            }
        }
    }

    /**
     * change the status of music according to the current status of music player.
     * If the music is currently playing, then pause this music; if paused, then resume the music
     *
     * @param
     * @return void
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/25 16:03
     * @version: 1.0.0
     **/


    public void togglePlay() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        if (status.equals(Status.PLAY)) {
            pause();
        } else {
            resumeAudio();
        }
    }

    /**
     * play the music
     *
     * @param
     * @return void
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/25 16:06
     * @version: 1.0.0
     **/


    public void play() {
        // open audioInputStream to the clip
        clip.start();
        status = Status.PLAY;
    }


    /**
     * pause the music
     *
     * @param
     * @return void
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/25 16:06
     * @version: 1.0.0
     **/


    public void pause() {
        if (status.equals(Status.PAUSED)) {
            if (GameEngine.isDebugActive()) {
                System.out.println("Audio is already paused");
            }
            return;
        }
        currentFrame = this.clip.getMicrosecondPosition();
        clip.stop();
        status = Status.PAUSED;
    }


    /**
     * resume the audio
     *
     * @param
     * @return void
     * @throws UnsupportedAudioFileException
     *         This exception indicating that an operation failed because a file did not contain valid data of a recognized file type and format.
     * @throws IOException
     *         This exception is the general class of exceptions produced by failed or interrupted I/O operations.
     * @throws LineUnavailableException
     *         an exception indicating that a line cannot be opened because it is unavailable. This situation arises most commonly when a requested line is already in use by another application.
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/25 16:07
     * @version: 1.0.0
     */
    //FIXME: 停止后启动会进入无响应 原装就有问题
    public void resumeAudio() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        if (status.equals(Status.PLAY)) {
            System.out.println("Audio is already being played");
            return;
        }
        clip.close();
        resetAudioStream();
        clip.setMicrosecondPosition(currentFrame);
        this.play();
    }

    /**
     * reset audio stream
     *
     * @param
     * @return void
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/25 16:07
     * @version: 1.0.0
     **/

    public void resetAudioStream() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        audioInputStream = AudioSystem.getAudioInputStream(new File(musicPath).getAbsoluteFile());
        clip.open(audioInputStream);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }


    /**
     * terminate the current music playing
     *
     * @param
     * @return void
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/12/2 14:36
     * @version:
     **/

    public void stop() {
        currentFrame = 0L;
        clip.stop();
        clip.close();
    }


    /**
     * This method is to restart the audio from the beginning
     *
     * @param
     * @return void
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/12/5 20:50
     * @version:
     **/


    public void restart() throws IOException, LineUnavailableException, UnsupportedAudioFileException {
        clip.stop();
        clip.close();
        resetAudioStream();
        currentFrame = 0L;
        clip.setMicrosecondPosition(0);
        this.play();
    }
}

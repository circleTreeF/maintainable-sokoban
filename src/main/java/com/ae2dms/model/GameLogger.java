package com.ae2dms.model;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * The project of AE2DMS Coursework of Yizirui FANG 20127091
 * <p>
 * Package: com.ae2dms.model.GameLogger
 *
 * @description: This is the class to log the activities of the game
 * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
 * @date: 2020/11/10 11:10 given
 * @version: 1.0
 */
public class GameLogger extends Logger {

    private static final Logger logger = Logger.getLogger("GameLogger");
    private final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    private final Calendar calendar = Calendar.getInstance();

    /**
     * constructor
     *
     * @param
     * @description: construct a instance to log this project with specified format
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/13 15:42 given
     * @version: 1.0.0
     **/


    public GameLogger() throws IOException {
        super("com.aes2dms.sokoban", null);

        File directory = new File(System.getProperty("user.dir") + "/" + "logs");
        directory.mkdirs();

        FileHandler fileHandler = new FileHandler(directory + "/" + GameEngine.GAME_NAME + ".log");
        logger.addHandler(fileHandler);
        SimpleFormatter formatter = new SimpleFormatter();
        fileHandler.setFormatter(formatter);
    }

    /**
     * @param message
     *         The message need to be logged
     * @return java.lang.String
     * @description: format the log message to the standard format
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/20 21:21
     * @version: 1.0.0
     **/


    private String createFormattedMessage(String message) {
        return dateFormat.format(calendar.getTime()) + " -- " + message;
    }

    /**
     * @param message
     *         log the information message with formatting
     * @return void
     * @description: log the information message in pre-defined format
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/20 22:36 given
     * @version:
     **/

    @Override
    public void info(String message) {
        logger.info(createFormattedMessage(message));
    }


    /**
     * @param message
     *         the warning message without pre-defined format
     * @return void
     * @description: log the warning message with formatting
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/20 22:40 given
     * @version:
     **/

    @Override
    public void warning(String message) {
        logger.warning(createFormattedMessage(message));
    }

    /**
     * @param message
     *         the severe message without pre-defined format
     * @return void
     * @description: log the severe message with formatting
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/20 22:43 given
     * @version:
     **/


    @Override
    public void severe(String message) {
        logger.severe(createFormattedMessage(message));
    }
}
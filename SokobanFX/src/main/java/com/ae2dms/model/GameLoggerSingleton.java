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
 * Package: com.ae2dms.model
 * <p>
 * The single object of GameLogger. This class has its constructor as private and have a static instance of itself. This class provides a static method to get its static instance to outside world.
 *
 * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
 * @date: 2020/11/23 20:24
 */

public class GameLoggerSingleton extends Logger {
    private static GameLoggerSingleton gameLoggerSingleton;
    private static final Logger logger = Logger.getLogger("GameLogger");
    private final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    private final Calendar calendar = Calendar.getInstance();

    /**
     * private constructor so that this class cannot be instantiated
     *
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/23 20:35
     * @version: 1.0.0
     **/

    private GameLoggerSingleton() throws IOException {
        super("com.aes2dms.sokoban", null);
        File directory = new File(System.getProperty("user.dir") + "/" + "logs");
        directory.mkdirs();

        FileHandler fileHandler = new FileHandler(directory + "/" + GameEngine.GAME_NAME + ".log");
        logger.addHandler(fileHandler);
        SimpleFormatter formatter = new SimpleFormatter();
        fileHandler.setFormatter(formatter);
    }

    /**
     * {@inheritDoc}
     * <p>
     * instantiate this class and return the only single instance of it
     *
     * @param
     * @return com.ae2dms.model.GameLoggerSingleton
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/23 21:03
     * @version: 1.0.0
     **/

//TODO: could be refactored to initialize this class with init()
    public static GameLoggerSingleton getGameLoggerSingleton() throws IOException {
        if (gameLoggerSingleton == null) {
            gameLoggerSingleton = new GameLoggerSingleton();
        }
        return gameLoggerSingleton;
    }


    /**
     * format the log message to the standard format. This method is extracted from the given GameLogger class.
     *
     * @param message
     *         The message need to be logged
     * @return java.lang.String
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/23 21:07
     * @version:
     **/


    private String createFormattedMessage(String message) {
        return dateFormat.format(calendar.getTime()) + " -- " + message;
    }


    /**
     * log the information message in pre-defined format. This method is extracted from the given GameLogger class.
     *
     * @param message
     *         log the information message with formatting
     * @return void
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/23 21:07
     * @version:
     **/


    @Override
    public void info(String message) {
        logger.info(createFormattedMessage(message));
    }


    /**
     * log the warning message with formatting. This method is extracted from the given GameLogger class.
     *
     * @param message
     *         the warning message without pre-defined format
     * @return void
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/23 21:07
     * @version:
     **/


    @Override
    public void warning(String message) {
        logger.warning(createFormattedMessage(message));
    }


    /**
     * log the severe message with formatting. This method is extracted from the given GameLogger class.
     *
     * @param message
     *         the severe message without pre-defined format
     * @return void
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/23 21:06
     * @version: 1.0.0
     **/


    @Override
    public void severe(String message) {
        logger.severe(createFormattedMessage(message));
    }
}

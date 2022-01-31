package server.helpers;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Parsers {

    public static final String PATH_SERVER = System.getProperty("user.dir") + File.separator + "File Server" + File.separator + "task" +
            File.separator + "src" + File.separator + "server" + File.separator + "data";

    public static final String PATH_CLIENT = System.getProperty("user.dir") + File.separator + "File Server" + File.separator + "task" +
            File.separator + "src" + File.separator + "client" + File.separator + "data";

    public static final String PATH_MAP = System.getProperty("user.dir") + File.separator + "File Server" + File.separator + "task" +
            File.separator + "src" + File.separator + "server" + File.separator + "idMap.data";

    public static final String EXTENSION = ".txt";
    public static final int FILENAME_LENGTH = 10;

//    public static final String PATH_MAP = System.getProperty("user.dir") +
//            File.separator + "src" + File.separator + "server" + File.separator;
//
//    public static final String PATH_SERVER = System.getProperty("user.dir") +
//            File.separator + "src" + File.separator + "server" + File.separator + "data";
//
//    public static final String PATH_CLIENT = System.getProperty("user.dir") +
//            File.separator + "src" + File.separator + "server" + File.separator + "idMap.data";

    public static String generateFilename() {        int leftLimit = 'a'; // letter 'a'
        int rightLimit = 'z'; // letter 'z'
        int targetStringLength = FILENAME_LENGTH;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);

        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }

        return buffer.toString() + EXTENSION;
    }

    public static String getFirstWord(String s) {
        String[] parts = s.split(" ");
        return parts[0];
    }

    public static String getStringWithoutFirstWord(String s) {
        String[] parts = s.split(" ");
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 1; i < parts.length; i++) {
            stringBuilder.append(parts[i]).append(" ");
        }

        if (stringBuilder.length() != 0) {
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }

        return stringBuilder.toString();
    }

    public static String getServerPath(String s) {
        return PATH_SERVER + File.separator + s;
    }

    public static String getClientPath(String s) {
        return PATH_CLIENT + File.separator + s;
    }

}

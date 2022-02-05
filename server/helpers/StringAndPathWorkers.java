package server.helpers;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class StringAndPathWorkers {

    public static final String PATH_SERVER = System.getProperty("user.dir") + File.separator + "File Server" + File.separator + "task" +
            File.separator + "src" + File.separator + "server" + File.separator + "data";

    public static final String PATH_MAP = System.getProperty("user.dir") + File.separator + "File Server" + File.separator + "task" +
            File.separator + "src" + File.separator + "server" + File.separator + "idMap.data";

//    public static final String PATH_MAP = System.getProperty("user.dir") +
//            File.separator + "src" + File.separator + "server" + File.separator + "idMap.data";
//
//    public static final String PATH_SERVER = System.getProperty("user.dir") +
//            File.separator + "src" + File.separator + "server" + File.separator + "data";


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
}

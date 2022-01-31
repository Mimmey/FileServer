package server.helpers.idMap;

import server.helpers.Parsers;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class IdMap implements Serializable {
    private static final long serialVersionUID = 1L;
    private static ConcurrentHashMap<Integer, String> map = new ConcurrentHashMap<Integer, String>();
    private static int maxId = 0;
    private static transient final String filename = Parsers.PATH_MAP;

    public static void initialize() throws IOException {
        File file = new File(filename);
        if (!file.exists()) {
            file.createNewFile();
            return;
        }
        deserialize();
    }

    public static boolean deleteByFilename(String filename) {
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            if (entry.getValue().equals(filename)) {
                map.remove(entry.getKey());
                return true;
            }
        }
        return false;
    }

    public static Optional<String> deleteById(int id) {
        String filename = map.get(id);
        map.remove(id);
        return Optional.of(filename);
    }

    public static String getFilenameById(int id) {
        return map.get(id);
    }

    public static int put(String filename) {
        map.put(maxId++, filename);
        return maxId - 1;
    }

    public static void serialize() {
        try {
            SerializationUtils.serialize(map, filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deserialize() {
        try {
            map = (ConcurrentHashMap<Integer, String>) SerializationUtils.deserialize(filename);
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }
}

package javabasic.io.basic.diffstream;

import javabasic.io.basic.PathManager;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lee
 * @since 2025/3/11
 */
public class ObjectStreamDemo {
    private static final String dataFile = "object.dat";
    public static void main(String[] args) throws IOException {
        Map<String, Object> map = new HashMap<>();
        map.put("a", 1);
        map.put("b", 2);
        map.put("c", 3);
        Map<String,String> inner = new HashMap<>();
        map.put("inner",inner);
        inner.put("x","xx");
        inner.put("y","yy");

        try (ObjectOutputStream output = new ObjectOutputStream(Files.newOutputStream(Paths.get(PathManager.OUTPUT_DIR + dataFile)))) {
            output.writeObject(map);
        }

        try(ObjectInputStream input = new ObjectInputStream(Files.newInputStream(Paths.get(PathManager.OUTPUT_DIR + dataFile)))){
            Map<String, Object> newMap = (Map<String, Object>) input.readObject();
            System.out.println(newMap);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

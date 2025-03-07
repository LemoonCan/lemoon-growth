package javabasic.io.basic;

import java.io.File;

/**
 * @author lee
 * @since 2025/3/5
 */
public class Path {
    public static String SPACECRAFT = "unit-demo/src/main/resource/io/太空船.txt";
    public static String NUMS = "unit-demo/src/main/resource/io/usnumbers.txt";
    public static String OUTPUT_DIR = "unit-demo/target/resource/io/";

    public static void checkOutputDir(){
        File directory = new File(OUTPUT_DIR);
        directory.mkdirs();
    }
}

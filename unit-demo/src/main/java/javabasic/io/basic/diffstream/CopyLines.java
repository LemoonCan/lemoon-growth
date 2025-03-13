package javabasic.io.basic.diffstream;

import javabasic.io.basic.PathManager;

import java.io.*;

/**
 * Line-Oriented IO 一次读一行
 * 继承自Reader、Writer
 * @author lee
 * @since 2025/3/5
 */
public class CopyLines {
    public static void main(String[] args) throws IOException {
        PathManager.checkOutputDir();
        try (
                BufferedReader br = new BufferedReader(new FileReader(PathManager.SPACECRAFT));
                PrintWriter pw = new PrintWriter(new FileWriter(PathManager.OUTPUT_DIR + "太空船副本line.txt"))
        ) {
            String s;
            while ((s = br.readLine()) != null) {
                pw.println(s);
            }
        }
    }
}

package javabasic.io.stream.copy;

import javabasic.io.stream.Path;

import java.io.*;

/**
 * Line-Oriented IO 一次读一行
 * 继承自Reader、Writer
 * @author lee
 * @since 2025/3/5
 */
public class CopyLines {
    public static void main(String[] args) throws IOException {
        Path.checkOutputDir();
        try (
                BufferedReader br = new BufferedReader(new FileReader(Path.SPACECRAFT));
                PrintWriter pw = new PrintWriter(new FileWriter(Path.OUTPUT_DIR + "太空船副本line.txt"))
        ) {
            String s;
            while ((s = br.readLine()) != null) {
                pw.println(s);
            }
        }
    }
}

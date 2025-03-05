package javabasic.io.stream.copy;

import javabasic.io.stream.Path;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Character Stream 一次读2byte
 * 继承自Reader/Writer
 *
 * @author lee
 * @since 2025/3/5
 */
public class CopyCharacters {
    public static void main(String[] args) throws IOException {
        Path.checkOutputDir();
        try (
                FileReader fileReader = new FileReader(Path.SPACECRAFT);
                FileWriter fileWriter = new FileWriter(Path.OUTPUT_DIR + "太空船副本char.txt")
        ) {
            int c;
            while ((c = fileReader.read()) != -1) {
                fileWriter.write(c);
            }
        }
    }
}

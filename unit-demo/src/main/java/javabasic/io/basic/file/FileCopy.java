package javabasic.io.basic.file;

import javabasic.io.basic.PathManager;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * @author lee
 * @since 2025/3/15
 */
public class FileCopy {
    public static void main(String[] args) throws IOException, InterruptedException {
        //零拷贝实现文件复制
        Files.copy(Paths.get(PathManager.SPACECRAFT), Paths.get(PathManager.OUTPUT_DIR + "copy.txt"));

        Files.delete(Paths.get(PathManager.OUTPUT_DIR + "copy.txt"));
    }
}

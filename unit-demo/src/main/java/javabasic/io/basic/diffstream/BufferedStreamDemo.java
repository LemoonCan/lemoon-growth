package javabasic.io.basic.diffstream;

import javabasic.io.basic.PathManager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author lee
 * @since 2025/3/12
 */
public class BufferedStreamDemo {
    public static void main(String[] args) throws IOException {
        markAndReset(PathManager.SPACECRAFT);
        markAndResetInvalid(PathManager.SPACECRAFT);
    }

    public static void markAndReset(String file) throws IOException {
        try (BufferedReader bis = new BufferedReader(new FileReader(file))) {
            bis.skip(10);
            bis.mark(10);

            char[] buffer = new char[10];
            if (bis.read(buffer) != -1) {
                System.out.println("重置前读取: " + new String(buffer));
            }

            // 重置到标记位置，通过缓冲区实现的回退
            bis.reset();

            // 再次读取
            if (bis.read(buffer) != -1) {
                System.out.println("重置后读取: " + new String(buffer));
            }
        }
    }

    public static void markAndResetInvalid(String file) throws IOException {
        try (BufferedReader bis = new BufferedReader(new FileReader(file), 10)) {
            bis.mark(10);

            char[] buffer = new char[20];
            if (bis.read(buffer) != -1) {
                System.out.println("重置前读取: " + new String(buffer));
            }

            // 重置到标记位置，通过缓冲区实现的回退
            // 若回退的字符数大于缓冲区剩余容纳字符数，必定抛出Mark invalid异常，和mark的参数无关
            bis.reset();

            // 再次读取
            if (bis.read(buffer) != -1) {
                System.out.println("重置后读取: " + new String(buffer));
            }
        }
    }
}

package javabasic.io.basic.diffstream;

import javabasic.io.basic.PathManager;

import java.io.*;

/**
 * Byte Stream 只推荐用在原始IO,一次读1byte
 * 继承自InputStream、OutputStream
 *
 * @author lee
 * @since 2025/3/4
 */
public class CopyBytes {
    public static void main(String[] args) throws IOException {
        PathManager.checkOutputDir();
        //查看当前工作目录
        System.out.println(System.getProperty("user.dir"));
        FileInputStream in = null;
        FileOutputStream out = null;

        try {
            //linux系统 非/开头为使用相对路径，/开头为使用绝对路径
            in = new FileInputStream(PathManager.SPACECRAFT);
            out = new FileOutputStream(PathManager.OUTPUT_DIR + "太空船副本.txt");

            int c;
            //循环读取：每次读1byte
            while ((c = in.read()) != -1) {
                out.write(c);
            }
        } finally {
            //=== 正确关闭！===
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }

        }
    }
}

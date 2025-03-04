package javabasic.io.stream;

import java.io.*;

/**
 * ByteStream 只推荐用在原始IO
 * @author lee
 * @since 2025/3/4
 */
public class CopyBytes {
    public static void main(String[] args) throws IOException {
        //查看当前工作目录
        System.out.println(System.getProperty("user.dir"));
        FileInputStream in = null;
        FileOutputStream out = null;

        try {
            //linux系统 非/开头为使用相对路径，/开头为使用绝对路径
            in = new FileInputStream("unit-demo/src/main/resource/io/太空船.txt");

            String directoryPath = "unit-demo/target/resource/io";
            String outName = directoryPath + "/太空船副本.txt";
            File directory = new File(directoryPath);
            directory.mkdirs();
            out = new FileOutputStream(outName);

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

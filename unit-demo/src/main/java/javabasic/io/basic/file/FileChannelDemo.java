package javabasic.io.basic.file;

import javabasic.io.basic.PathManager;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.StandardOpenOption.READ;
import static java.nio.file.StandardOpenOption.WRITE;

/**
 * @author lee
 * @since 2025/3/18
 */
public class FileChannelDemo {
    public static void main(String[] args) throws IOException {
        Path file = Paths.get(PathManager.OUTPUT_DIR + "logfile.txt");

        String s = "I was here!\n";
        byte[] data = s.getBytes();
        ByteBuffer out = ByteBuffer.wrap(data);

        ByteBuffer cut = ByteBuffer.allocate(12);

        try (FileChannel fc = (FileChannel.open(file, READ, WRITE))) {
            // cut暂存文件的前12个字节
            int nread;
            do {
                nread = fc.read(cut);
            } while (nread != -1 && cut.hasRemaining());

            // 替换文件开头的12字节为"I was here!"
            fc.position(0);
            while (out.hasRemaining())
                fc.write(out);
            // "I was here!"倒带到0位置，准备再次写入
            out.rewind();

            // 移动到文件末尾，写入先前暂存的12字节，再写入"I was here!"
            long length = fc.size();
            fc.position(length - 1);
            cut.flip();
            while (cut.hasRemaining())
                fc.write(cut);
            while (out.hasRemaining())
                fc.write(out);
        }
    }
}

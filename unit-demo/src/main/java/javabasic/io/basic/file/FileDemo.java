package javabasic.io.basic.file;

import com.alibaba.fastjson2.JSON;
import javabasic.io.basic.PathManager;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.PosixFileAttributes;
import java.nio.file.attribute.PosixFilePermissions;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

/**
 * @author lee
 * @since 2025/3/15
 */
public class FileDemo {
    public static void main(String[] args) throws IOException, InterruptedException {
        Path file = Paths.get(PathManager.SPACECRAFT);
        //零拷贝实现文件复制
        Files.copy(file, Paths.get(PathManager.OUTPUT_DIR + "copy.txt"));
        //删除文件
        Files.delete(Paths.get(PathManager.OUTPUT_DIR + "copy.txt"));

        //基本文件元数据
        BasicFileAttributes attr = Files.readAttributes(file, BasicFileAttributes.class);

        System.out.println("文件元数据:" + JSON.toJSONString(attr));
        printFileAttributes(attr);
        System.out.println();

        //POSIX文件元数据
        PosixFileAttributes pAttr = Files.readAttributes(file, PosixFileAttributes.class);
        System.out.format("%s %s %s%n",
                pAttr.owner().getName(),
                pAttr.group().getName(),
                PosixFilePermissions.toString(pAttr.permissions()));
        System.out.println();

        //文件存储空间
        FileStore store = Files.getFileStore(file);
        long total = byteToGB(store.getTotalSpace());
        long used = byteToGB(store.getTotalSpace() - store.getUnallocatedSpace());
        long avail = byteToGB(store.getUsableSpace());
        System.out.println("Total space: " + total + " GB");
        System.out.println("Used space: " + used + " GB");
        System.out.println("Available space: " + avail + " GB");

        byte[] data = "Hello World! ".getBytes();
        Path p = Paths.get(PathManager.OUTPUT_DIR + "logfile.txt");
        //文件不存在新建，存在追加
        try (OutputStream out = new BufferedOutputStream(
                Files.newOutputStream(p, CREATE, APPEND))) {
            out.write(data, 0, data.length);
        }
    }

    private static long byteToGB(long bytes) {
        return bytes / 1024 / 1024 / 1024;
    }

    private static void printFileAttributes(BasicFileAttributes attr) {
        System.out.println("creationTime: " + attr.creationTime());
        System.out.println("lastAccessTime: " + attr.lastAccessTime());
        System.out.println("lastModifiedTime: " + attr.lastModifiedTime());

        System.out.println("isDirectory: " + attr.isDirectory());
        System.out.println("isOther: " + attr.isOther());
        System.out.println("isRegularFile: " + attr.isRegularFile());
        System.out.println("isSymbolicLink: " + attr.isSymbolicLink());
        System.out.println("size: " + attr.size());
    }
}

package javabasic.io.basic.file;

import javabasic.io.basic.PathManager;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Set;

import static java.nio.file.FileVisitResult.CONTINUE;

/**
 * @author lemoon
 * @since 2025/4/1
 */
public class DirectoryDemo {
    public static void main(String[] args) throws IOException {
        FileSystems.getDefault().getRootDirectories().forEach(System.out::println);

        // glob筛选文件
        System.out.println("\n筛选txt文件：");
        Path dir = Path.of(PathManager.OUTPUT_DIR);
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir, "*.txt")) {
            for (Path file : stream) {
                System.out.println(file.getFileName());
            }
        }

        // 创建目录
        Path world = Path.of(PathManager.OUTPUT_DIR + "world");
        Set<PosixFilePermission> perms = PosixFilePermissions.fromString("rwxr-x---");
        FileAttribute<Set<PosixFilePermission>> attr = PosixFilePermissions.asFileAttribute(perms);
        Files.createDirectory(world, attr);

        // 自定义filter筛选目录
        System.out.println("\n筛选目录：");
        DirectoryStream.Filter<Path> filter = path -> {
            try {
                return Files.isDirectory(path);
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        };
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir, filter)) {
            for (Path entry : stream) {
                System.out.println(entry.getFileName());
            }
        }

        // 删除目录
        Files.deleteIfExists(world);

        System.out.println("\n遍历target目录：");
        PrintFiles pf = new PrintFiles();
        Files.walkFileTree(Path.of(PathManager.VISIT_DIR), pf);
    }

    public static class PrintFiles extends SimpleFileVisitor<Path> {
        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attr) {
            if (attr.isSymbolicLink()) {
                System.out.format("Symbolic link: %s ", file);
            } else if (attr.isRegularFile()) {
                System.out.format("Regular file: %s ", file);
            } else {
                System.out.format("Other: %s ", file);
            }
            System.out.println("(" + attr.size() + "bytes)");
            return CONTINUE;
        }

        // 访问目录前调用
        @Override
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
            System.out.format("Directory: %s%n", dir);
            return CONTINUE;
        }

        // 访问文件失败后调用
        @Override
        public FileVisitResult visitFileFailed(Path file, IOException exc) {
            System.err.println(exc);
            return CONTINUE;
        }
    }

}

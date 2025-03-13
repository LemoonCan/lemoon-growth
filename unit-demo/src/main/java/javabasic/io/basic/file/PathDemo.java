package javabasic.io.basic.file;

import javabasic.io.basic.PathManager;

import java.io.IOException;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author lee
 * @since 2025/3/13
 */
public class PathDemo {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("/my/world/space.txt");

        System.out.format("toString: %s%n", path);
        System.out.format("getFileName: %s%n", path.getFileName());
        System.out.format("getName(0): %s%n", path.getName(0));
        System.out.format("getNameCount: %d%n", path.getNameCount());
        //按照/分割得到的序列
        System.out.format("subpath(0,2): %s%n", path.subpath(0,2));
        System.out.format("getParent: %s%n", path.getParent());
        System.out.format("getRoot: %s%n", path.getRoot());
        System.out.format("toAbsolutePath: %s%n", path.toAbsolutePath());
        //两个都为相对路径，会假定路径在同一级
        Path p1 = Paths.get("x/joe");
        Path p2 = Paths.get("y/sally");
        System.out.format("relativize p1-p2: %s%n", p1.relativize(p2));
        System.out.format("relativize p2-p1: %s%n", p2.relativize(p1));

        //有同级目录的情况
        Path p3 = Paths.get("home");
        Path p4 = Paths.get("home/sally/bar");
        System.out.format("relativize p3-p4: %s%n", p3.relativize(p4));
        System.out.format("relativize p4-p3: %s%n", p4.relativize(p3));

        //会真实检查目录是否存在
        System.out.format("toRealPath: %s%n", path.toRealPath(LinkOption.NOFOLLOW_LINKS));
    }
}

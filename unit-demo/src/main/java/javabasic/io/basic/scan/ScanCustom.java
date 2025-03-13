package javabasic.io.basic.scan;

import javabasic.io.basic.PathManager;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 * @author lee
 * @since 2025/3/6
 */
public class ScanCustom {
    public static void main(String[] args) throws FileNotFoundException {
        try(Scanner scanner = new Scanner(new BufferedReader(new FileReader(PathManager.SPACECRAFT)))){
            //换行+空行作为分隔符
            scanner.useDelimiter("\n\\s*\n");
            while (scanner.hasNext()){
                System.out.println(scanner.next());
                System.out.println("====");
            }
        }
    }
}

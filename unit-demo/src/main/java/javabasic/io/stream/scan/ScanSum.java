package javabasic.io.stream.scan;

import javabasic.io.stream.Path;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Locale;
import java.util.Scanner;

/**
 * @author lee
 * @since 2025/3/6
 */
public class ScanSum {
    public static void main(String[] args) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(Path.NUMS)))) {
            scanner.useLocale(Locale.US);
            double sum = 0;
            while (scanner.hasNext()) {
                if (scanner.hasNextDouble()) {
                    double num = scanner.nextDouble();
                    sum += num;
                } else {
                    scanner.next();
                }
            }
            System.out.println("sum:" + sum);
        }
    }
}

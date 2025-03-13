package javabasic.io.basic.diffstream;

import javabasic.io.basic.PathManager;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * 二进制文件没有明确的结束标记，使用异常反而是一种可靠的方式来表示文件末尾？
 * @author lee
 * @since 2025/3/10
 */
public class DataStreamDemo {
    private static final String dataFile = "invoicedata.dat";

    private static final double[] prices = {19.99, 9.99, 15.99, 3.99, 4.99};
    private static final int[] units = {12, 8, 13, 29, 50};
    private static final String[] descs = {
            "Java T-shirt",
            "Java Mug",
            "Duke Juggling Dolls",
            "Java Pin",
            "Java Key Chain"
    };

    public static void main(String[] args) throws IOException {
        try (DataOutputStream out = new DataOutputStream(Files.newOutputStream(Paths.get(PathManager.OUTPUT_DIR + dataFile)))) {
            for (int i = 0; i < prices.length; i++) {
                out.writeDouble(prices[i]);
                out.writeInt(units[i]);
                out.writeUTF(descs[i]);
            }
        }

        DataInputStream in = null;
        try {
            in = new DataInputStream(Files.newInputStream(Paths.get(PathManager.OUTPUT_DIR + dataFile)));
            while (true) {
                double price = in.readDouble();
                int unit = in.readInt();
                String desc = in.readUTF();
                System.out.format("You ordered %d units of %s at $%.2f%n", unit, desc, price);
            }
        } catch (EOFException e) {
            System.out.println("End of stream");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                in.close();
            }
        }
    }
}

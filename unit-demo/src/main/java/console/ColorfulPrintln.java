package console;

/**
 * @author lee
 * @date 6/1/21
 * 彩色打印
 * https://cloud.tencent.com/developer/article/1142372
 */
public class ColorfulPrintln {
    public static void colorfulBack(String msg){
        System.out.println("\033[42;1m"+msg+"\033[m");
    }

    public static void main(String[] args) {
        for (int i = 1; i < 8; i++) {
            System.out.println("\033[3"+i+";1m"+"彩色字3"+i+"\033[0m");
            System.out.println("\033[9"+i+";1m"+"彩色字9"+i+"\033[0m");
        }
        for (int i = 1; i < 8; i++) {
            System.out.println("\033[97;4"+i+";1m"+"彩色背景4"+i+"\033[0m");
        }

        System.out.println("\033[0m"+"无样式(默认)"+"\033[0m");
        System.out.println("\033[1m"+"粗体"+"\033[0m");
        System.out.println("\033[4m"+"下划线"+"\033[0m");
        System.out.println("\033[7m"+"反色"+"\033[0m");

        System.out.println("\033[31;1m"+"红色粗体"+"\033[0m");
        System.out.println("\033[31;42;1m"+"happy"+"\033[0m");

    }
}

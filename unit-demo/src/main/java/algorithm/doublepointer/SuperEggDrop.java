package algorithm.doublepointer;

/**
 * @author lee
 * @since 2022/1/5
 */
public class SuperEggDrop {
    public static void main(String[] args) {
        SuperEggDrop demo = new SuperEggDrop();
        System.out.println(demo.superEggDrop(3,14));
        System.out.println(demo.superEggDrop(2,6));
        System.out.println(demo.superEggDrop(1,2));
        System.out.println(demo.superEggDrop(2,2));

    }

    public int superEggDrop(int k, int n) {
        int right = n, step = 0;
        while (right>=1) {
            if(k==1){
                step+=right;
                return step;
            }
            int mid = right/2;
            k--;
            step++;
            right=mid-1;
        }
        return step;
    }
}

package designpattern.structured.combination;

/**
 * 组合模式构建文件系统
 * @author lee
 * @since 2022/10/10
 */
public class Demo {
    public static void main(String[] args) {
        Directory music = new Directory("/music");
        Directory spring = new Directory("/music/春-日光");
        Directory summer = new Directory("/music/夏-狂热");
        Directory autumn = new Directory("/music/秋-故事");
        Directory winter = new Directory("/music/冬-未了");
        music.addSubNode(spring);
        music.addSubNode(summer);
        music.addSubNode(autumn);
        music.addSubNode(winter);

        File sun = new File("/music/春-日光/日光");
        File fountain = new File("/music/春-日光/一千座喷泉");
        spring.addSubNode(sun);
        spring.addSubNode(fountain);

        File hisSummer = new File("/music/夏-狂热/他夏了夏天");
        File sleepless = new File("/music/夏-狂热/无眠");
        summer.addSubNode(hisSummer);
        summer.addSubNode(sleepless);

        File leaves = new File("/music/秋-故事/从一片落叶开始");
        File star = new File("/music/秋-故事/小星星");
        File sunny = new File("/music/秋-故事/天天晴朗");
        File goodbye = new File("/music/秋-故事/说了再见以后");
        File miss = new File("/music/秋-故事/我好想你");
        autumn.addSubNode(leaves);
        autumn.addSubNode(star);
        autumn.addSubNode(sunny);
        autumn.addSubNode(goodbye);
        autumn.addSubNode(miss);

        File outWall = new File("/music/冬-未了/墙外的风景");
        File lift = new File("/music/冬-未了/他举起右手点名");
        winter.addSubNode(outWall);
        winter.addSubNode(lift);

        System.out.println("文件数："+music.countFiles());
    }
}

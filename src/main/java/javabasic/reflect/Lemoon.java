package javabasic.reflect;

/**
 * @author lee
 * @date 5/27/21
 */
public class Lemoon {
    private String can;
    private String moon;
    Integer id;

    public String name;

    public Lemoon() {
    }

    public Lemoon(String can, String moon) {
        this.can = can;
        this.moon = moon;
    }

    public void plantMoon(){
        System.out.println(moon+" plant");
    }

    private void fillCan(String material){
        this.can = material;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}


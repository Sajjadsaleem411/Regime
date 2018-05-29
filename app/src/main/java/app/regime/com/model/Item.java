package app.regime.com.model;

/**
 * Created by Muhammad Sajjad on 5/25/2018.
 */

public class Item {

    String name;
    boolean check1=false;
    boolean check2=false;

    public Item(String name){
        this.name=name;
        this.check1=false;
    }

    public boolean isCheck2() {
        return check2;
    }

    public void setCheck2(boolean check2) {
        this.check2 = check2;
    }

    public boolean isCheck1() {
        return check1;
    }

    public void setCheck1(boolean check1) {
        this.check1 = check1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

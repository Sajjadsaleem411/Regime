package app.regime.com.model;

/**
 * Created by Muhammad Sajjad on 5/25/2018.
 */

public class Item {

    String name;
    boolean check=false;

    public Item(String name){
        this.name=name;
        this.check=false;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

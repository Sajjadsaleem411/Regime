package app.regime.com.model;

import java.sql.Time;

/**
 * Created by Muhammad Sajjad on 5/26/2018.
 */

public class Title {
    String name;
    boolean select=false;

    public Title(String name){
        this.name=name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }
}

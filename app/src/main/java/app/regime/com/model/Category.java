
package app.regime.com.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Category implements Serializable{

    @SerializedName("CategoryName")
    @Expose
    private String categoryName;
    @SerializedName("Items")
    @Expose
    public List<Item> items=new ArrayList<>();
    @SerializedName("DailyImages")
    @Expose
    private String dailyImages;
    public  Category(){

    }
    public Category(String categoryName,List<Item> items){
        this.items=items;
        this.categoryName=categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDailyImages() {
        return dailyImages;
    }

    public void setDailyImages(String dailyImages) {
        this.dailyImages = dailyImages;
    }

}

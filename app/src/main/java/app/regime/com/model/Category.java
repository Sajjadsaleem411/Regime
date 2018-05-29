
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
    private String date;
    private boolean isFullDay;
    public  Category(){

    }
    public Category(String categoryName,List<Item> items){
        this.items=items;
        this.categoryName=categoryName;
    }

    public boolean isFullDay() {
        return isFullDay;
    }

    public void setFullDay(boolean fullDay) {
        isFullDay = fullDay;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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
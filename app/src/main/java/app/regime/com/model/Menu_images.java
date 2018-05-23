
package app.regime.com.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Menu_images {

    @SerializedName("ItemImage")
    @Expose
    private String itemImage;

    public String getItemImage() {
        return itemImage;
    }

    public void setItemImage(String itemImage) {
        this.itemImage = itemImage;
    }

}

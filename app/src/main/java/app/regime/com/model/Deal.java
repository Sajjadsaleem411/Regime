
package app.regime.com.model;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Deal {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("categories")
    @Expose
    public List<Category> categories = new ArrayList<>();

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

}

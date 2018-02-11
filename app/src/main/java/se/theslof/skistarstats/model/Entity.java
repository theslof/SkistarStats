
package se.theslof.skistarstats.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@android.arch.persistence.room.Entity(tableName = "friends")
public class Entity {

    @PrimaryKey
    @SerializedName("id")
    @Expose
    private Integer id;

    @ColumnInfo
    @SerializedName("name")
    @Expose
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

/*
    @SerializedName("displayStatus")
    @Expose
    private Integer displayStatus;
    @SerializedName("image")
    @Expose
    private Image image;
    @SerializedName("link")
    @Expose
    private Link link;
    @SerializedName("type")
    @Expose
    private Integer type;

    public Integer getDisplayStatus() {
        return displayStatus;
    }

    public void setDisplayStatus(Integer displayStatus) {
        this.displayStatus = displayStatus;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Link getLink() {
        return link;
    }

    public void setLink(Link link) {
        this.link = link;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
*/
}

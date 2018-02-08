
package se.theslof.skistarstats.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Entry {

    @SerializedName("entity")
    @Expose
    private Entity entity;
    @SerializedName("score")
    @Expose
    private Score score;
    @SerializedName("winner")
    @Expose
    private Boolean winner;

    public Entity getEntity() {
        return entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }

    public Boolean getWinner() {
        return winner;
    }

    public void setWinner(Boolean winner) {
        this.winner = winner;
    }

}

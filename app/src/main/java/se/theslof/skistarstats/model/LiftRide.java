package se.theslof.skistarstats.model;

/**
 * Created by Martin on 2018-01-25.
 */

public class LiftRide {

    private String date;
    private String liftName;
    private Destination destination;
    private Integer dropHeight;
    private String timestamp;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLiftName() {
        return liftName;
    }

    public void setLiftName(String liftName) {
        this.liftName = liftName;
    }

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    public Integer getDropHeight() {
        return dropHeight;
    }

    public void setDropHeight(Integer dropHeight) {
        this.dropHeight = dropHeight;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getFDate(){
        String[] date = getDate().split("T");
        return date[0] + " " + date[1].substring(0,5);
    }

}
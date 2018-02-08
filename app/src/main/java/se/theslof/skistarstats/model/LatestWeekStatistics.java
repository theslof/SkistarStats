package se.theslof.skistarstats.model;

import java.util.List;

public class LatestWeekStatistics {

private Week week;
private List<Day> days = null;
private Integer dropHeight;
private Integer liftRides;
private Integer kcal;
private Integer pinCount;
private Integer liftCount;
private Integer dayCount;
private Integer destinationCount;
private String entityId;

public Week getWeek() {
return week;
}

public void setWeek(Week week) {
this.week = week;
}

public List<Day> getDays() {
return days;
}

public void setDays(List<Day> days) {
this.days = days;
}

public Integer getDropHeight() {
return dropHeight;
}

public void setDropHeight(Integer dropHeight) {
this.dropHeight = dropHeight;
}

public Integer getLiftRides() {
return liftRides;
}

public void setLiftRides(Integer liftRides) {
this.liftRides = liftRides;
}

public Integer getKcal() {
return kcal;
}

public void setKcal(Integer kcal) {
this.kcal = kcal;
}

public Integer getPinCount() {
return pinCount;
}

public void setPinCount(Integer pinCount) {
this.pinCount = pinCount;
}

public Integer getLiftCount() {
return liftCount;
}

public void setLiftCount(Integer liftCount) {
this.liftCount = liftCount;
}

public Integer getDayCount() {
return dayCount;
}

public void setDayCount(Integer dayCount) {
this.dayCount = dayCount;
}

public Integer getDestinationCount() {
return destinationCount;
}

public void setDestinationCount(Integer destinationCount) {
this.destinationCount = destinationCount;
}

public String getEntityId() {
return entityId;
}

public void setEntityId(String entityId) {
this.entityId = entityId;
}

}

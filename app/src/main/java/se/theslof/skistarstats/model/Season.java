package se.theslof.skistarstats.model;

public class Season {

private Integer seasonId;
private String name;
private Integer seasonType;
private String seasonName;
private String startDate;
private String endDate;
private Object months;
private Object weeks;

public Integer getSeasonId() {
return seasonId;
}

public void setSeasonId(Integer seasonId) {
this.seasonId = seasonId;
}

public String getName() {
return name;
}

public void setName(String name) {
this.name = name;
}

public Integer getSeasonType() {
return seasonType;
}

public void setSeasonType(Integer seasonType) {
this.seasonType = seasonType;
}

public String getSeasonName() {
return seasonName;
}

public void setSeasonName(String seasonName) {
this.seasonName = seasonName;
}

public String getStartDate() {
return startDate;
}

public void setStartDate(String startDate) {
this.startDate = startDate;
}

public String getEndDate() {
return endDate;
}

public void setEndDate(String endDate) {
this.endDate = endDate;
}

public Object getMonths() {
return months;
}

public void setMonths(Object months) {
this.months = months;
}

public Object getWeeks() {
return weeks;
}

public void setWeeks(Object weeks) {
this.weeks = weeks;
}

}

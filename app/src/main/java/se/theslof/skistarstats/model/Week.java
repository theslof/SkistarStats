package se.theslof.skistarstats.model;

public class Week {

private Integer weekNumber;
private String monday;
private Integer month;
private Integer year;
private Object days;
private String startDate;
private String endDate;
private Object leaderboardString;
private Object monthFilter;
private Object prefixString;
private Object suffixString;

public Integer getWeekNumber() {
return weekNumber;
}

public void setWeekNumber(Integer weekNumber) {
this.weekNumber = weekNumber;
}

public String getMonday() {
return monday;
}

public void setMonday(String monday) {
this.monday = monday;
}

public Integer getMonth() {
return month;
}

public void setMonth(Integer month) {
this.month = month;
}

public Integer getYear() {
return year;
}

public void setYear(Integer year) {
this.year = year;
}

public Object getDays() {
return days;
}

public void setDays(Object days) {
this.days = days;
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

public Object getLeaderboardString() {
return leaderboardString;
}

public void setLeaderboardString(Object leaderboardString) {
this.leaderboardString = leaderboardString;
}

public Object getMonthFilter() {
return monthFilter;
}

public void setMonthFilter(Object monthFilter) {
this.monthFilter = monthFilter;
}

public Object getPrefixString() {
return prefixString;
}

public void setPrefixString(Object prefixString) {
this.prefixString = prefixString;
}

public Object getSuffixString() {
return suffixString;
}

public void setSuffixString(Object suffixString) {
this.suffixString = suffixString;
}

}

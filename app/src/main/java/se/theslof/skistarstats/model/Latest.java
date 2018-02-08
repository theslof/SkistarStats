package se.theslof.skistarstats.model;

public class Latest {

private LatestSeasonStatistics latestSeasonStatistics;
private LatestWeekStatistics latestWeekStatistics;
private LatestDayStatistics latestDayStatistics;
private Chart chart;

public LatestSeasonStatistics getLatestSeasonStatistics() {
return latestSeasonStatistics;
}

public void setLatestSeasonStatistics(LatestSeasonStatistics latestSeasonStatistics) {
this.latestSeasonStatistics = latestSeasonStatistics;
}

public LatestWeekStatistics getLatestWeekStatistics() {
return latestWeekStatistics;
}

public void setLatestWeekStatistics(LatestWeekStatistics latestWeekStatistics) {
this.latestWeekStatistics = latestWeekStatistics;
}

public LatestDayStatistics getLatestDayStatistics() {
return latestDayStatistics;
}

public void setLatestDayStatistics(LatestDayStatistics latestDayStatistics) {
this.latestDayStatistics = latestDayStatistics;
}

public Chart getChart() {
return chart;
}

public void setChart(Chart chart) {
this.chart = chart;
}

}

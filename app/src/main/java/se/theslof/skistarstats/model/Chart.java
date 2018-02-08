package se.theslof.skistarstats.model;

import java.util.List;

public class Chart {

private List<ChartEntry> chartEntries = null;
private List<String> chartTitles = null;

public List<ChartEntry> getChartEntries() {
return chartEntries;
}

public void setChartEntries(List<ChartEntry> chartEntries) {
this.chartEntries = chartEntries;
}

public List<String> getChartTitles() {
return chartTitles;
}

public void setChartTitles(List<String> chartTitles) {
this.chartTitles = chartTitles;
}
}



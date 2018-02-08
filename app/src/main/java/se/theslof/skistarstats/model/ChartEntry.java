package se.theslof.skistarstats.model;

public class ChartEntry {

private String value;
private String title;
private String percent;
private Boolean empty;
private String date;
private Boolean lowValue;
private Link link;

public String getValue() {
return value;
}

public void setValue(String value) {
this.value = value;
}

public String getTitle() {
return title;
}

public void setTitle(String title) {
this.title = title;
}

public String getPercent() {
return percent;
}

public void setPercent(String percent) {
this.percent = percent;
}

public Boolean getEmpty() {
return empty;
}

public void setEmpty(Boolean empty) {
this.empty = empty;
}

public String getDate() {
return date;
}

public void setDate(String date) {
this.date = date;
}

public Boolean getLowValue() {
return lowValue;
}

public void setLowValue(Boolean lowValue) {
this.lowValue = lowValue;
}

public Link getLink() {
return link;
}

public void setLink(Link link) {
this.link = link;
}

}

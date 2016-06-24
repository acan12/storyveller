package core.model;

import java.util.Date;

import io.realm.RealmObject;

/**
 * Created by arysuryawan on 5/15/16.
 */
public class Itienary extends RealmObject {
    private String name;
    private String latlong;
    private String category;
    private String transportation;
    private Date startDateTime;
    private Date endDateTime;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLatlong() {
        return latlong;
    }

    public void setLatlong(String latlong) {
        this.latlong = latlong;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTransportation() {
        return transportation;
    }

    public void setTransportation(String transportation) {
        this.transportation = transportation;
    }

    public Date getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(Date startDateTime) {
        this.startDateTime = startDateTime;
    }

    public Date getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(Date endDateTime) {
        this.endDateTime = endDateTime;
    }
}

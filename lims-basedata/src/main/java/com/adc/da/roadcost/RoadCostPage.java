package com.adc.da.roadcost;

import com.adc.da.base.page.BasePage;

import java.util.List;

public class RoadCostPage extends BasePage {
    private List<String> searchPhrase;
    private String id;
    private String roadLine;
    private String carType;
    private String horsePower;

    public List<String> getSearchPhrase() {
        return searchPhrase;
    }

    public void setSearchPhrase(List<String> searchPhrase) {
        this.searchPhrase = searchPhrase;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoadLine() {
        return roadLine;
    }

    public void setRoadLine(String roadLine) {
        this.roadLine = roadLine;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getHorsePower() {
        return horsePower;
    }

    public void setHorsePower(String horsePower) {
        this.horsePower = horsePower;
    }
}

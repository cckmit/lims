package com.adc.da.roadtestcost.page;

import com.adc.da.base.page.BasePage;

import java.util.List;

public class RoadTestCostEoPage extends BasePage {
    private List<String> searchPhrase;
    private String id;
    private String testRoadSituation;
    private String carType;
    private String content;
    private String carState;
    private String fuelType;
    private String horsePower;
    private String hasFuelCost;

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

    public String getTestRoadSituation() {
        return testRoadSituation;
    }

    public void setTestRoadSituation(String testRoadSituation) {
        this.testRoadSituation = testRoadSituation;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCarState() {
        return carState;
    }

    public void setCarState(String carState) {
        this.carState = carState;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getHorsePower() {
        return horsePower;
    }

    public void setHorsePower(String horsePower) {
        this.horsePower = horsePower;
    }

    public String getHasFuelCost() {
        return hasFuelCost;
    }

    public void setHasFuelCost(String hasFuelCost) {
        this.hasFuelCost = hasFuelCost;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stockprog.entity;


public class Stock {

    private String companyCode;
    private String companyName;

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    private double highInitialOpeningPrice;
    private double lowInitialOpeningPrice;
    private double movingAverage;
    private double percent;
    private String companyCategory;
    private double change;


    public Stock() {
    }

    public double getChange() {
        return change;
    }

    public void setChange(double change) {
        this.change = change;
    }

    public double getHighInitialOpeningPrice() {
        return highInitialOpeningPrice;
    }

    public void setHighInitialOpeningPrice(double highInitialOpeningPrice) {
        this.highInitialOpeningPrice = highInitialOpeningPrice;
    }

    public double getLowInitialOpeningPrice() {
        return lowInitialOpeningPrice;
    }

    public void setLowInitialOpeningPrice(double lowInitialOpeningPrice) {
        this.lowInitialOpeningPrice = lowInitialOpeningPrice;
    }

    public double getMovingAverage() {
        return movingAverage;
    }

    public void setMovingAverage(double movingAverage) {
        this.movingAverage = movingAverage;
    }

    public double getPercent() {
        return percent;
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }

    public String getCompanyCategory() {
        return companyCategory;
    }

    public void setCompanyCategory(String companyCategory) {
        this.companyCategory = companyCategory;
    }

    }

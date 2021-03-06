package com.example.moneymanager.ListView;

public class DataProvider {
    String date;
    String purpose;
    String income;
    String expenditure;

    public DataProvider(String date, String purpose, String income, String expenditure) {
        this.date = date;
        this.purpose = purpose;
        this.income = income;
        this.expenditure = expenditure;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getIncome() {
        return income;
    }

    public void setIncome(String income) {
        this.income = income;
    }

    public String getExpenditure() {
        return expenditure;
    }

    public void setExpenditure(String expenditure) {
        this.expenditure = expenditure;
    }
}

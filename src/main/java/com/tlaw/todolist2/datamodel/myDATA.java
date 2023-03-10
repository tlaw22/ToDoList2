package com.tlaw.todolist2.datamodel;

import java.time.LocalDate;

public class myDATA {
    private String shortDescription;
    private String details;
    private LocalDate date;

    public myDATA(String shortDescription, String details, LocalDate date) {
        this.shortDescription = shortDescription;
        this.details = details;
        this.date = date;
    }

    public static void addTodoItem(myDATA item) {
        myDATA.addTodoItem(item);

    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return  shortDescription;
    }
}

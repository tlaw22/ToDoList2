package com.tlaw.todolist2.datamodel;

import javafx.collections.FXCollections;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;

public class TodoData {
    private static final TodoData instance = new TodoData();
    private static final String filename = "todoListItems.txt";
    private List<myDATA> todoItems;
    private final DateTimeFormatter formatter;

    public static TodoData getInstance() {
        return instance;
    }

    private TodoData() {
        formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    }

    public List<myDATA> getTodoItems() {
        return todoItems;
    }

    public void setTodoItems(List<myDATA> todoItems) {
        this.todoItems = todoItems;
    }

    public void loadTodoItems() throws IOException {
        todoItems = FXCollections.observableArrayList();
        Path path = Paths.get(filename);
        BufferedReader br = Files.newBufferedReader(path);
        String input;


        try {
            while ((input = br.readLine()) != null) {
                String[] itemPieces = input.split("\t");
                String shortDescription = itemPieces[0];
                String details = itemPieces[1];
                String dateString = itemPieces[2];
                LocalDate date = LocalDate.parse(dateString, formatter);
                myDATA todoItem = new myDATA(shortDescription, details, date);
                todoItems.add(todoItem);
            }

        } finally {
            if (br != null) {
                br.close();
            }
        }

    }

    public void storeTodoItems() throws IOException {

        Path path = Paths.get(filename);
        BufferedWriter bw = Files.newBufferedWriter(path);
        try {
            Iterator<myDATA> iter = todoItems.iterator();
            while(iter.hasNext()) {
                myDATA todoItem = iter.next();
                bw.write(String.format("%s\t%s\t%s",
                        todoItem.getShortDescription(),
                        todoItem.getDetails(),
                        todoItem.getDate().format(formatter)));
                bw.newLine();
            }
        } finally {
            if (bw != null) {
                bw.close();
            }
        }
    }

}


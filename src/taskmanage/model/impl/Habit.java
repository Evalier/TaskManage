package taskmanage.model.impl;

import taskmanage.utility.impl.DatabaseConnector;
import taskmanage.model.interfaces.ModelInterface;
import taskmanage.utility.facades.UtilityFacade;

public class Habit implements ModelInterface {
    private String name;
    private int occurrences;

    // Constructor
    public Habit(String name, int occurrences) {
        this.name = name;
        this.occurrences = occurrences;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOccurrences() {
        return occurrences;
    }

    public void setOccurrences(int occurrences) {
        this.occurrences = occurrences;
    }

    @Override
    public boolean validate() {
        // Basic validation logic
        return true; // Replace with actual validation logic for your class
    }

    @Override
    public void save() {
        if (!validate()) {
            System.out.println("Validation failed. Cannot save.");
            return;
        }

        UtilityFacade dbConnector = new UtilityFacade();
        String query = "INSERT INTO tablename (fields) VALUES (values)"; // Replace with actual table name and fields
        dbConnector.executeUpdate(query);
        System.out.println("Record saved.");
    }


    @Override
    public String toString() {
        return "Habit{" +
                "name='" + name + '\'' +
                ", occurrences=" + occurrences +
                '}';
    }
}


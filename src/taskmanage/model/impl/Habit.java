package taskmanage.model;

public class Habit {
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
    public String toString() {
        return "Habit{" +
                "name='" + name + '\'' +
                ", occurrences=" + occurrences +
                '}';
    }
}


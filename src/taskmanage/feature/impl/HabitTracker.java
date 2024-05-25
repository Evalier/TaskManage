package taskmanage.feature;
import taskmanage.model.Habit;

import java.util.ArrayList;
import java.util.List;

public class HabitTracker {
    private List<Habit> habits;

    // Constructor
    public HabitTracker() {
        this.habits = new ArrayList<>();
    }

    // Method to add a habit to the tracker
    public void addHabit(Habit habit) {
        habits.add(habit);
        System.out.println("Habit added: " + habit.getName());
    }

    // Method to remove a habit from the tracker
    public void removeHabit(Habit habit) {
        habits.remove(habit);
        System.out.println("Habit removed: " + habit.getName());
    }

    // Method to get all habits
    public List<Habit> getHabits() {
        return habits;
    }

    // Method to analyze user habits
    public void analyzeHabits() {
        // Habit analysis logic
        System.out.println("Analyzing user habits");

        for (Habit habit : habits) {
            // Example analysis: count occurrences
            System.out.println("Habit: " + habit.getName() + " - Occurrences: " + habit.getOccurrences());
        }
    }

    // Method to display habit insights
    public void displayHabitInsights() {
        System.out.println("Habit Insights:");

        for (Habit habit : habits) {
            // Example insight: display name and occurrences
            System.out.println(" - " + habit.getName() + ": " + habit.getOccurrences() + " occurrences");
        }
    }

    @Override
    public String toString() {
        return "HabitTracker{" +
                "habits=" + habits +
                '}';
    }
}


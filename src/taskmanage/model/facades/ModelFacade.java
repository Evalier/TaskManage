package taskmanage.model.facades;

import taskmanage.constants.EnumsAndConstants;
import taskmanage.constants.EnumsAndConstants.PriorityLevel;
import taskmanage.model.impl.*;

import java.time.LocalDateTime;

public class ModelFacade {

    public Task createTask(String name, String description, String dueDate, PriorityLevel priority) {
        Task task = new Task(name, description, dueDate, priority);
        task.save();
        return task;
    }

    public boolean validateTask(Task task) {
        return task.validate();
    }

    public void saveTask(Task task) {
        task.save();
    }

    public void updateTaskStatus(Task task) {
        task.updateStatus();
    }

    public void addTagToTask(Task task, String tag) {
        task.addTag(tag);
    }

    public void removeTagFromTask(Task task, String tag) {
        task.removeTag(tag);
    }

    public void addSubTaskToTask(Task task, SubTask subTask) {
        task.addSubTask(subTask);
    }

    public void removeSubTaskFromTask(Task task, SubTask subTask) {
        task.removeSubTask(subTask);
    }

    public void setTaskCompletionDate(Task task, String completionDate) {
        task.setCompletionDate(completionDate);
    }

    public SubTask createSubTask(String name, String description, String dueDate, PriorityLevel priority) {
        SubTask subTask = new SubTask(name, description, dueDate, priority);
        subTask.save();
        return subTask;
    }

    public boolean validateSubTask(SubTask subTask) {
        return subTask.validate();
    }

    public void saveSubTask(SubTask subTask) {
        subTask.save();
    }

    public void updateSubTaskStatus(SubTask subTask, EnumsAndConstants.TaskStatus status) {
        subTask.setStatus(status);
    }

    //public void setSubTaskCompletionDate(SubTask subTask, String completionDate) {
    //    subTask.setCompletionDate(completionDate);
    //}

    public Reminder createReminder(Task task, LocalDateTime reminderTime) {
        Reminder reminder = new Reminder(task, reminderTime);
        reminder.save();
        return reminder;
    }

    public boolean validateReminder(Reminder reminder) {
        return reminder.validate();
    }

    public void saveReminder(Reminder reminder) {
        reminder.save();
    }

    public void updateReminderTime(Reminder reminder, LocalDateTime newReminderTime) {
        reminder.setReminderTime(newReminderTime);
    }

    public Task getReminderTask(Reminder reminder) {
        return reminder.getTask();
    }

    public Habit createHabit(String name, int occurrences) {
        Habit habit = new Habit(name, occurrences);
        habit.save();
        return habit;
    }

    public boolean validateHabit(Habit habit) {
        return habit.validate();
    }

    public void saveHabit(Habit habit) {
        habit.save();
    }

    public void updateHabitOccurrences(Habit habit, int occurrences) {
        habit.setOccurrences(occurrences);
    }

    public String getHabitName(Habit habit) {
        return habit.getName();
    }

    public int getHabitOccurrences(Habit habit) {
        return habit.getOccurrences();
    }

    public CalendarModel createCalendarModel() {
        CalendarModel calendarModel = new CalendarModel();
        calendarModel.save();
        return calendarModel;
    }

    public boolean validateCalendarModel(CalendarModel calendarModel) {
        return calendarModel.validate();
    }

    public void saveCalendarModel(CalendarModel calendarModel) {
        calendarModel.save();
    }
}


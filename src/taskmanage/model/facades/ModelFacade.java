package taskmanage.model.facades;

import taskmanage.constants.EnumsAndConstants;
import taskmanage.constants.EnumsAndConstants.PriorityLevel;
import taskmanage.model.impl.*;
import taskmanage.utility.facades.UtilityFacade;

import java.time.LocalDateTime;

public class ModelFacade {

    public Task currentTask;
    public SubTask  currentSubTask;
    public CalendarModel currentCalendarModel;
    public Habit currentHabit;
    public Reminder currentReminder;
    public User currentUser;

    public UtilityFacade utilityFacade;

    // Methods to manage Task
    public Task createTask(int id, String name, String description, String dueDate, String completionDate, PriorityLevel priority, EnumsAndConstants.TaskStatus status) {
        currentTask = new Task(id, name, description, dueDate, completionDate, priority, status);
        currentTask.save();
        return currentTask;
    }

    public boolean validateTask(Task task) {
        return task.validate();
    }

    public void saveTask(Task task) {
        task.save();
    }

    public void updateTaskStatus(Task task) {
        task.updateStatus(task);
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


        public SubTask createSubTask(int id, String name, String description, String dueDate, PriorityLevel priority, EnumsAndConstants.TaskStatus status) {
            currentSubTask = new SubTask(id, name, description, dueDate, priority, status);
            currentSubTask.save();
            return currentSubTask;
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

        // Other code sections remain unchanged...




    public Reminder createReminder(Task task, LocalDateTime reminderTime) {
        currentReminder = new Reminder(task, reminderTime);
        currentReminder.save();
        return currentReminder;
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
        currentHabit = new Habit(name, occurrences);
        currentHabit.save();
        return currentHabit;
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
        currentCalendarModel = new CalendarModel();
        currentCalendarModel.save();
        return currentCalendarModel;
    }

    public boolean validateCalendarModel(CalendarModel calendarModel) {
        return calendarModel.validate();
    }

    public void saveCalendarModel(CalendarModel calendarModel) {
        calendarModel.save();
    }

    public User createUser(String username, String password, byte[] salt, UtilityFacade securityManager) {
        currentUser = new User(username, password, salt, securityManager);
        currentUser.save();
        return currentUser;
    }

    public boolean validateUser(User user) {
        return user.validate();
    }

    public void saveUser(User user) {
        user.save();
    }

    public void updateUserPassword(User user, String newPassword) {
        byte[] newSalt = utilityFacade.generateSalt();
        user.setSalt(newSalt);
        user.setHashedPassword(utilityFacade.hashPasswordWithSalt(user.getUsername(), newPassword, newSalt));
    }

    public void updateUserUsername(User user, String newUsername) {
        user.setUsername(newUsername);
    }

    public String getUserDetails(User user) {
        return user.toString();
    }
}



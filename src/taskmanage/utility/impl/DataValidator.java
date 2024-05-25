package taskmanage.utility.impl;

import taskmanage.constants.EnumsAndConstants;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DataValidator {
    // Method to validate non-null and non-empty strings
    public static boolean validateString(String data) {
        return data != null && !data.trim().isEmpty();
    }

    // Method to validate date formats (e.g., "yyyy-MM-dd")
    public static boolean validateDate(String date) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate.parse(date, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    // Method to validate task priority
    public static boolean validatePriority(String priority) {
        try {
            EnumsAndConstants.PriorityLevel.valueOf(priority.toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    // Method to validate task status
    public static boolean validateStatus(String status) {
        try {
            EnumsAndConstants.TaskStatus.valueOf(status.toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    // Method to validate tags (e.g., non-empty and no special characters)
    public static boolean validateTag(String tag) {
        return tag != null && !tag.trim().isEmpty() && tag.matches("^[a-zA-Z0-9 ]+$");
    }
}

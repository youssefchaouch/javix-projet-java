package Utlis;

import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;

public class InputChecker {

    public static boolean isEmpty(TextField field) {
        return field.getText() == null || field.getText().trim().isEmpty();
    }

    public static boolean isEmpty(TextArea area) {
        return area.getText() == null || area.getText().trim().isEmpty();
    }

    public static boolean isValidEmail(String email) {
        if (email == null) return false;
        return email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    }

    public static boolean isValidPhone(String phone) {
        if (phone == null) return false;
        return phone.matches("^[0-9]{8,15}$");
    }

    public static boolean isValidInteger(String value) {
        if (value == null || value.trim().isEmpty()) return false;
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}

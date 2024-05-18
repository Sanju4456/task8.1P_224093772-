package web.service;

/**
 * Business logic to handle login functions.
 * 
 * @author sanju.
 */
public class LoginService {
    public static boolean login(String username, String password, String dob) {
        // Improved authentication logic
        if (username.equals("user") && password.equals("pass")) {
            return true;
        }
        return false;
    }
}


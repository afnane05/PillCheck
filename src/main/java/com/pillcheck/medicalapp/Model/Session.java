
package com.pillcheck.medicalapp.Model;

public class Session {

    private static Session instance;
    private User currentUser;

    // Private constructor for singleton
    private Session() {}

    // Get the single instance of Session
    public static Session getInstance() {
        if (instance == null) {
            instance = new Session();
        }
        return instance;
    }

    // Set the logged-in user
    public void setCurrentUser(User user) {
        this.currentUser = user;
    }

    // Get the logged-in user
    public User getCurrentUser() {
        return currentUser;
    }

    // Clear session (e.g., logout)
    public void clear() {
        currentUser = null;
    }
}

package librarysystem.session;

public final class UserSession {
    private static int userId = -1;
    private static String username = "";
    private static String email = "";

    private UserSession() {
    }

    public static void setCurrentUser(int currentUserId, String currentUsername, String currentEmail) {
        userId = currentUserId;
        username = currentUsername == null ? "" : currentUsername;
        email = currentEmail == null ? "" : currentEmail;
    }

    public static void clear() {
        userId = -1;
        username = "";
        email = "";
    }

    public static boolean isLoggedIn() {
        return userId > 0;
    }

    public static int getUserId() {
        return userId;
    }

    public static String getUsername() {
        return username;
    }

    public static String getEmail() {
        return email;
    }
}
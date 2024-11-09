package Kevin.backend.constant;

public class Constant {
    // Defines the path to store uploaded photos in the user's home directory
    public static final String PHOTO_DIRECTORY = System.getProperty("user.home") + "/Downloads/uploads/";

    // Represents the header key for detecting AJAX requests
    public static final String X_REQUESTED_WITH = "X-Requested-With";
}

package common.helpers;

public class MathHelper {
    public static String getFileSizeMegaBytes(java.io.File file) {
        return (double) file.length() / (1024 * 1024) + " mb";
    }
}

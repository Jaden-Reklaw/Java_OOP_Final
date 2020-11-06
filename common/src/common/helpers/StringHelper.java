package common.helpers;

public class StringHelper {
    public static String getExtension(String s) {
        int i = s.lastIndexOf('.');
        if (i > 0) {
            s = s.substring(i+1);
        }
        return s;
    }
}

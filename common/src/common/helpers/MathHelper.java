package common.helpers;

import java.io.File;

public class MathHelper {
    public static String getFileSizeMegaBytes(long num) {
        return (double) num / (1024 * 1024) + " mb";
    }

    public static long getFolderSize(File folder) {
        long length = 0;
        File[] files = folder.listFiles();

        int count = files.length;

        for (int i = 0; i < count; i++) {
            if (files[i].isFile()) {
                length += files[i].length();
            }
            else {
                length += getFolderSize(files[i]);
            }
        }
        return length;
    }
}

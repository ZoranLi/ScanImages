import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ScanImages {
    public static List<File> images = new ArrayList<>();
    private static final String[] okFileExtensions = new String[]{"jpg", "jpeg", "png", "gif"};

    public static void main(String[] args) {
        File file = new File("C:\\code\\androidApp");
        scanDirectory(file);
        System.out.println("args = " + images);
    }

    public static void scanDirectory(File directory) {
        System.out.println("directory = " + directory);
        if (directory.isDirectory()) {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        scanDirectory(file);
                    } else {
                        for (String extension : okFileExtensions) {
                            if (file.getName().toLowerCase().endsWith(extension)) {
                                images.add(file);
                            }
                        }
                    }
                }
            }
        } else {
            for (String extension : okFileExtensions) {
                if (directory.getName().toLowerCase().endsWith(extension)) {
                    images.add(directory);
                }
            }
        }
    }

    static class ImageFileFilter implements FilenameFilter {
        private final String[] okFileExtensions = new String[]{"jpg", "jpeg", "png", "gif"};

        @Override
        public boolean accept(File file, String name) {
            for (String extension : okFileExtensions) {
                if (file.getName().toLowerCase().endsWith(extension)) {
                    return true;
                }
            }
            return false;
        }
    }

}

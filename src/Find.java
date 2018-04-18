import java.io.File;
import java.io.IOException;
import java.util.Objects;

import static java.io.File.separator;

public class Find {

    static String findWithoutDir(String file) throws IOException {
        String result = "File not found";
        File f = new File(file);
        if (f.exists()) return convert(f);
        return result;
    }

    static String findWithoutDirR(String file) throws IOException {
        File f = new File(file);
        if (f.exists()) return convert(f);
        else {
            File parent = new File(".");
            return findInside(parent, file);
        }
    }

    static String find(File dir, String file) throws IOException {
        String result = "File not found";
        String[] dirNameFiles = dir.list();
        File[] dirFiles = dir.listFiles();
        if (dirFiles != null && dirNameFiles != null) {
            for (int i=0; i<dirFiles.length; i++) {
                if (Objects.equals(dirNameFiles[i], file)) {
                    return convert(dirFiles[i]);
                }
            }
        }
        return result;
    }

    static String findInside(File dir, String file) throws IOException {
        String result = "File not found";
        String[] dirNameFiles = dir.list();
        File[] dirFiles = dir.listFiles();
        if (dirFiles != null && dirNameFiles != null) {
            for (int i=0; i<dirFiles.length; i++) {
                if (Objects.equals(dirNameFiles[i], file)) {
                    return convert(dirFiles[i]);
                }
                if (dirFiles[i].isDirectory()) {
                    result = findInside(dirFiles[i], file);
                    if (!Objects.equals(result, "File not found")) return result;
                }
            }
        }
        return result;
    }

    private static String convert(File file) {
        StringBuilder path = new StringBuilder(file.getAbsolutePath());
        String[] parts = path.toString().split(separator + separator);
        path = new StringBuilder(parts[0]);
        for (int i = 1; i < parts.length; i++) {
            if (Objects.equals(parts[i], ".")) continue;
            path.append(separator).append(parts[i]);
        }
        return path.toString();
    }

}
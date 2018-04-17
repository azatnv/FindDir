import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.io.File.separator;

public class Find {

    static List<File> findWithoutDir(String file) throws IOException {
        List<File> result = new ArrayList<>();
        File f = new File(file);
        if (f.exists()) return convert(f);
        return result;
    }

    static List<File> findWithoutDirR(String file) throws IOException {
        File f = new File(file);
        if (f.exists()) return convert(f);
        else {
            File parent = new File(".");
            return findInside(parent, file);
        }
    }

    static List<File> find(File dir, String file) throws IOException {
        List<File> result = new ArrayList<>();
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

    static List<File> findInside(File dir, String file) throws IOException {
        List<File> result = new ArrayList<>();
        String[] dirNameFiles = dir.list();
        File[] dirFiles = dir.listFiles();
        if (dirFiles != null && dirNameFiles != null) {
            for (int i=0; i<dirFiles.length; i++) {
                if (Objects.equals(dirNameFiles[i], file)) {
                    return convert(dirFiles[i]);
                }
                if (dirFiles[i].isDirectory()) {
                    if (dirFiles[i].isHidden()) continue;
                    result = findInside(dirFiles[i], file);
                    if (!result.isEmpty()) return result;
                }
            }
        }
        return result;
    }

    private static List<File> convert(File file) {
        List<File> result = new ArrayList<>();
        StringBuilder path = new StringBuilder(file.getAbsolutePath());
        String[] parts = path.toString().split(separator + separator);
        for (int i = 1; i < parts.length; i++) {
            path = new StringBuilder(parts[0]);
            for (int g = 1; g <= i; g++)  {
                if (Objects.equals(parts[g], ".")) continue;
                path.append(separator).append(separator).append(parts[g]);
            }
            result.add(new File(String.valueOf(path)));
        }
        return result;
    }

}
import java.io.File;
import java.io.IOException;
import java.util.*;

import static java.io.File.separator;

public class Find {

    static Set<File> findWithoutDir(String file) throws IOException {
        File parent = new File(".");
        return find(parent, file);
    }

    static Set<File> findWithoutDirR(String file) throws IOException {
        File parent = new File(".");
        return findInside(parent, file);
    }

    static Set<File> find(File dir, String file) throws IOException {
        Set<File> result = new HashSet<>();
        String[] dirNameFiles = dir.list();
        File[] dirFiles = dir.listFiles();
        if (dirFiles != null && dirNameFiles != null) {
            for (int i=0; i<dirFiles.length; i++) {
                if (Objects.equals(dirNameFiles[i], file)) {
                    result.add(dirFiles[i]);
                }
            }
        }
        return result;
    }

    static Set<File> findInside(File dir, String file) throws IOException {
        Set<File> result = new HashSet<>();
        String[] dirNameFiles = dir.list();
        File[] dirFiles = dir.listFiles();
        if (dirFiles != null && dirNameFiles != null) {
            for (int i=0; i<dirFiles.length; i++) {
                if (Objects.equals(dirNameFiles[i], file)) {
                    result.add(dirFiles[i]);
                }
                if (dirFiles[i].isDirectory()) {
                    result.addAll(findInside(dirFiles[i], file));
                }
            }
        }
        return result;
    }

    static Set<File> convert(Set<File> list) {
        Set<File> result = new HashSet<>();
        for (File element: list) {
            StringBuilder path = new StringBuilder(element.getPath());
            String[] parts = path.toString().split(separator + separator);
            path = new StringBuilder();
            for (int i = 0; i < parts.length - 1; i++) {
                if (Objects.equals(parts[i], ".")) continue;
                path.append(parts[i]).append(separator);
            }
            path.append(parts[parts.length-1]);
            result.add(new File(path.toString()));
        }
        return result;
    }
}
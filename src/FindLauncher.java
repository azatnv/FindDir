import org.kohsuke.args4j.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FindLauncher {

    @Option(name = "-r", metaVar = "Key", usage = "The need to search in all subdirectories")
    private String searchInside;

    @Option(name = "-d", metaVar = "Directory", usage = "Search in this directory")
    private String dirFind;

    @Argument(required = true, metaVar = "FileName", usage = "Name of the file to be searched")
    private String fileName;

    public static void main(String[] args) throws IOException {
        new FindLauncher().launch(args);
    }

    private void launch(String[] args) throws IOException {

        CmdLineParser parser = new CmdLineParser(this);

        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            System.err.println("java -jar find.jar -r -d directory filename.txt");
            parser.printUsage(System.err);
            return;
        }

        if (fileName.matches(".+\\.txt")) {
            List<File> list = new ArrayList<>();

            if (dirFind == null) {
                if (searchInside != null) list = Find.findWithoutDirR(fileName);
                else list = Find.findWithoutDir(fileName);
            }

            if (dirFind != null && !(new File(dirFind)).isHidden()) {
                File directory = new File(dirFind);
                if (searchInside != null)  list = Find.findInside(directory, fileName);
                else list = Find.find(directory, fileName);
            }

            if (list.isEmpty()) System.out.println("File not found =(");
            else{
                System.out.println(list);
            }
        } else {
            System.err.println("Incorrect argument format");
            parser.printUsage(System.err);
        }
    }

}
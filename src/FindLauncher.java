import org.kohsuke.args4j.*;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class FindLauncher {

    @Option(name = "-r", metaVar = "Key", usage = "The need to search in all subdirectories")
    private boolean searchInside;

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
            PrintStream out = new PrintStream(System.out);
            if (dirFind == null) {
                if (searchInside) {
                    out.println(Find.convert(Find.findWithoutDirR(fileName)));
                }
                else out.print(Find.convert(Find.findWithoutDir(fileName)));
            }
            if (dirFind != null) {
                File directory = new File(dirFind);
                if (searchInside)  out.print(Find.convert(Find.findInside(directory, fileName)));
                else out.print(Find.convert(Find.find(directory, fileName)));
            }
        } else {
            System.err.println("Incorrect argument format");
            parser.printUsage(System.err);
        }
    }

}
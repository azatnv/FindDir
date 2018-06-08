import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.io.File.separator;
import static org.junit.jupiter.api.Assertions.*;

class FindTest {

    private List<File> empty = new ArrayList<>();
    private List<File> result = new ArrayList<>();
    private File file = new File("."+separator+"Text.txt");
    private File file1 = new File("files"+separator+"Text1.txt");
    private File file1_2 = new File("."+separator+"files"+separator+"Text1.txt");
    private File file2 = new File("files"+separator+"file2"+separator+"Text2.txt");
    private File file3_1 = new File("files"+separator+"file2"+separator+"Text3.txt");
    private File file3_2 = new File("files"+separator+"file3"+separator+"Text3.txt");
    private File file3_3 = new File("."+separator+"files"+separator+"file2"+separator+"Text3.txt");
    private File file3_4 = new File("."+separator+"files"+separator+"file3"+separator+"Text3.txt");
    private File file4 = new File("files"+separator+"file2"+separator+"file4"+separator+"Text4.txt");
    private File file5 = new File("files"+separator+"file2"+separator+"file4"+separator+"file5"+separator+"Text5.txt");
    private File file5_2 = new File("."+separator+"files"+separator+"file2"+separator+"file4"+separator+"file5"+separator+"Text5.txt");
    private File dir = new File("files");
    private File dir1 = new File("files"+separator+"file1");
    private File dir2 = new File("files"+separator+"file2");
    private File dir4 = new File("files"+separator+"file2"+separator+"file4");

    @Test
    void findInside() throws IOException {
        result.add(file1);
        assertEquals(Find.findInside(dir, "Text1.txt"), result);
        result.remove(file1);
        result.add(file5);
        assertEquals(Find.findInside(dir, "Text5.txt"), result);
        result.remove(file5);
        assertEquals(Find.findInside(dir1, "Text1.txt"), empty);
        assertEquals(Find.findInside(dir1, "Text3.txt"), empty);
        result.add(file4);
        assertEquals(Find.findInside(dir4, "Text4.txt"), result);
        result.remove(file4);
        result.add(file3_1);
        result.add(file3_2);
        assertEquals(Find.findInside(dir, "Text3.txt"), result);
        result.remove(file3_1);
        result.remove(file3_2);
    }

    @Test
    void find() throws IOException {
        result.add(file1);
        assertEquals(Find.find(dir, "Text1.txt"), result);
        result.remove(file1);
        result.add(file5);
        assertEquals(Find.find(dir, "Text5.txt"), empty);
        result.remove(file5);
        result.add(file1);
        assertEquals(Find.find(dir1, "Text1.txt"), empty);
        result.remove(file1);
        result.add(file2);
        assertEquals(Find.find(dir2, "Text2.txt"), result);
        result.remove(file2);
        result.add(file4);
        assertEquals(Find.find(dir4, "Text4.txt"), result);
        result.remove(file4);
    }

    @Test
    void findWithoutDirR() throws IOException {
        result.add(file);
        assertEquals(Find.findWithoutDirR("Text.txt"), result);
        result.remove(file);
        result.add(file5_2);
        assertEquals(Find.findWithoutDirR("Text5.txt"), result);
        result.remove(file5_2);
        result.add(file1_2);
        assertEquals(Find.findWithoutDirR("Text1.txt"), result);
        result.remove(file1_2);
        assertEquals(Find.findWithoutDirR("Text123.txt"), empty);
        result.add(file3_3);
        result.add(file3_4);
        assertEquals(Find.findWithoutDirR("Text3.txt"), result);
        result.add(file3_3);
        result.add(file3_4);
    }

    @Test
    void findWithoutDir() throws IOException {
        result.add(file);
        assertEquals(Find.findWithoutDir("Text.txt"), result);
        result.remove(file);
        assertEquals(Find.findWithoutDir("Text1.txt"), empty);
    }
}
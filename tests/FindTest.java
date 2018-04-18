import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class FindTest {

    private String notFound = "File not found";
    private File dir = new File("files");
    private File dir1 = new File("files\\file1");
    private File dir2 = new File("files\\file2");
    private File dir4 = new File("files\\file2\\file4");

    @Test
    void findInside() throws IOException {
        assertEquals(Find.findInside(dir, "Text1.txt"), "D:\\FindDir\\files\\Text1.txt");
        assertEquals(Find.findInside(dir, "Text5.txt"), "D:\\FindDir\\files\\file2\\file4\\file5\\Text5.txt");
        assertEquals(Find.findInside(dir1, "Text1.txt"), notFound);
        assertEquals(Find.findInside(dir2, "Text3.txt"), notFound);
        assertEquals(Find.findInside(dir4, "Text4.txt"), "D:\\FindDir\\files\\file2\\file4\\Text4.txt");
    }

    @Test
    void find() throws IOException {
        assertEquals(Find.find(dir, "Text1.txt"), "D:\\FindDir\\files\\Text1.txt");
        assertEquals(Find.find(dir, "Text5.txt"), notFound);
        assertEquals(Find.find(dir1, "Text1.txt"), notFound);
        assertEquals(Find.find(dir2, "Text2.txt"), "D:\\FindDir\\files\\file2\\Text2.txt");
        assertEquals(Find.find(dir4, "Text4.txt"), "D:\\FindDir\\files\\file2\\file4\\Text4.txt");
    }

    @Test
    void findWithoutDirR() throws IOException {
        assertEquals(Find.findWithoutDirR("Text.txt"), "D:\\FindDir\\Text.txt");
        assertEquals(Find.findWithoutDirR("Text5.txt"), "D:\\FindDir\\files\\file2\\file4\\file5\\Text5.txt");
        assertEquals(Find.findWithoutDirR("Text1.txt"), "D:\\FindDir\\files\\Text1.txt");
        assertEquals(Find.findWithoutDirR("Text123.txt"), notFound);
    }

    @Test
    void findWithoutDir() throws IOException {
        assertEquals(Find.findWithoutDir("Text.txt"), "D:\\FindDir\\Text.txt");
        assertEquals(Find.findWithoutDir("Text1.txt"), notFound);

    }

}
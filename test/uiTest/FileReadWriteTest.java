package uiTest;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ui.FileReadWrite;

import static org.junit.Assert.*;

public class FileReadWriteTest {
    FileReadWrite fileReadWrite;

    @Before
    public void setUp() throws Exception {
        fileReadWrite = new FileReadWrite();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void readTxtOneLine() {
        String s = fileReadWrite.readTxt("res/test.txt");
        Assert.assertEquals("aabc", s);
    }

    @Test
    public void readTxtMoreLines() {
        String s = fileReadWrite.readTxt("res/testWrite.txt");
        Assert.assertEquals("aabcxyz", s);
    }

    @Test
    public void writeTxt() {
        fileReadWrite.writeTxt("res/testWrite.txt", "aabc\nxyz");
    }
}
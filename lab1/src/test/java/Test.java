import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import java.util.Formatter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

class CmmTest {

    InputStream in = null;
    PrintStream out =  null;

    InputStream inputStream = null;
    OutputStream outputStream = null;

    @Before
    public void setUp(){
        in = System.in;
        out = System.out;

        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @After
    public void tearDown(){
        System.setIn(in);
        System.setOut(out);
    }

    @org.junit.Test
    public void test1(){
        check("A-6.0.cmm", "A-6.0.output");
    }

    private void check(String testFile, String outFile) {

    }
}

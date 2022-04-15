import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class HelloTest {

    @Test
    public void testConstructor(){
        try{
            new Hello();
        }
        catch (Exception e){
            fail("Constructor failure");
        }
    }

    @Test
    public void testMain(){
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Hello hello = new Hello();
        hello.main(null);
        assertEquals("Hello world!", outContent.toString());

    }
}

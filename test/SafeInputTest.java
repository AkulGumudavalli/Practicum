import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
public class SafeInputTest {
    @BeforeEach
    void create(){

    }
    @Test
    public void testGetNonZeroLenString() {
        String input = "\nHello\n";
        SafeInput safeInput = new SafeInput(new Scanner(new ByteArrayInputStream(input.getBytes())));

        String result = safeInput.getNonZeroLenString("Enter a non-zero length string");
        assertEquals("Hello", result, "The method should return a valid non-empty string.");
    }

    @Test
    public void testGetInt() {
        String input = "123\n";
        SafeInput safeInput = new SafeInput(new Scanner(new ByteArrayInputStream(input.getBytes())));

        int result = safeInput.getInt(new Scanner(new ByteArrayInputStream(input.getBytes())), "Enter an integer");
        assertEquals(123, result, "The method should return the correct integer.");
    }

    @Test
    public void testGetDouble() {
        String input = "123.45\n";
        SafeInput safeInput = new SafeInput(new Scanner(new ByteArrayInputStream(input.getBytes())));

        double result = safeInput.getDouble("Enter a double");
        assertEquals(123.45, result, 0.01, "The method should return the correct double value.");
    }

    @Test
    public void testGetRangedInt() {
        String input = "15\n";
        SafeInput safeInput = new SafeInput(new Scanner(new ByteArrayInputStream(input.getBytes())));

        int result = safeInput.getRangedInt("Enter a number between 10 and 20", 10, 20);
        assertEquals(15, result, "The method should return a valid integer within the range.");
    }

    @Test
    public void testGetRangedDouble() {
        String input = "12.5\n";
        SafeInput safeInput = new SafeInput(new Scanner(new ByteArrayInputStream(input.getBytes())));

        double result = safeInput.getRangedDouble(new Scanner(new ByteArrayInputStream(input.getBytes())), "Enter a double between 10 and 20", 10.0, 20.0);
        assertEquals(12.5, result, 0.01, "The method should return a valid double within the range.");
    }

    @Test
    public void testGetYNconfirm() {
        String input = "Y\n";
        SafeInput safeInput = new SafeInput(new Scanner(new ByteArrayInputStream(input.getBytes())));

        boolean result = safeInput.getYNconfirm("Confirm action");
        assertTrue(result, "The method should return true for 'Y' input.");
    }

    @Test
    public void testGetRegExString() {
        String input = "abc123\n";
        SafeInput safeInput = new SafeInput(new Scanner(new ByteArrayInputStream(input.getBytes())));

        String result = safeInput.getregExString("Enter a string matching the pattern", "[a-z]+[0-9]+");
        assertEquals("abc123", result, "The method should return a valid string matching the regex pattern.");
    }

}

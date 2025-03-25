package tests;
import banking.Option;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class OptionTest {
    
    @Test
    void testNameStorage() {
        Option testName = new Option("Correct Name",()->{});
        assertTrue(testName.getOptionName().equals("Correct Name"));
    }

    @Test 
    void testCallBackFunctionality() {
        Option testCallback = new Option("name",()->{});
        assertTrue(testCallback.execute());
    }
}

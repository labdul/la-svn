package laGroup;
import static org.junit.Assert.fail;

/*
 * This class adds logs to the console output
 */
public class Logger {
    
    public void LogInfo(String text) {
	System.out.println("INFO: " + text);
    }

    /**
     * Method to stop JUnit test from executing test and reporting a fail
     */
    public static void logFail(String textToLog) {
	System.err.println("FAIL: " + textToLog);
	fail(textToLog);
    }
    
    public static void logError(String textToLog) {
	System.err.println("ERROR: " + textToLog);
    }

    
    public static void logWarning(String textToLog)
    {
	System.out.println("WARN: " + textToLog);
    }
    

    public static void logPass(String textToLog) {
	System.out.println("PASS: " + textToLog);
    }

}


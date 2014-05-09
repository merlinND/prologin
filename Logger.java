/**
 * Simple Logger class (in order to be able to disable output)
 * @author Merlin Nimier-David
 *
 */
public class Logger {

	/**
	 * Log levels: 1..3
	 * (higher => more precise)
	 */
	public static final int DEFAULT_LOG_LEVEL = 3;
	public static int currentLogLevel = DEFAULT_LOG_LEVEL;
	
	public static void log(String s) {
		log(s, DEFAULT_LOG_LEVEL);
	}
	/**
	 * 
	 * @param s
	 * @param logLevel Higher log level => more precise / useless messages
	 */
	public static void log(String s, int logLevel) {
		if (logLevel <= currentLogLevel)
			System.out.println(s);
	}
	
	
	public static void err(String s) {
		err(s, DEFAULT_LOG_LEVEL);
	}
	
	public static void err(String s, int logLevel) {
		if (logLevel <= currentLogLevel)
			System.err.println(s);
	}
	
	
	public static void setLogLevel(int level) {
		currentLogLevel = level;
	}
	public static void disableOutput() {
		setLogLevel(0);
	}
}
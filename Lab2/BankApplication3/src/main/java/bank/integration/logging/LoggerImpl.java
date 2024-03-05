package bank.integration.logging;

public class LoggerImpl implements Logger {

	public void log(String logString) {
		java.util.logging.Logger.getLogger("BankLogger").info(logString);
	}

}

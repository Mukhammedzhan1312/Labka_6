import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

enum LogLevel {
    INFO, WARNING, ERROR
}

class Logger {
    private static Logger _logger = null;
    private static LogLevel _logLevel = LogLevel.INFO;
    private static String logFilePath = "C:/Users/VICTUS/Desktop/2 - 3 курс сабактар отчет/text.txt";
    private static final Lock lock = new ReentrantLock();


    private Logger() {
    }

    public static Logger GetInstance() {
        if (_logger == null) {
            synchronized (Logger.class) {
                if (_logger == null) {
                    _logger = new Logger();
                }
            }
        }
        return _logger;
    }

    public static void SetLogLevel(LogLevel level) {
        _logLevel = level;
    }

    public static void SetLogFilePath(String path) {
        logFilePath = path;
    }

    public void Log(String message, LogLevel level) {
        if (level.ordinal() >= _logLevel.ordinal()) {
            Path filePath = Paths.get(logFilePath);
            String logMessage = level + " | " + message + System.lineSeparator();
            lock.lock();
            try {
                Files.write(filePath, logMessage.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    public void DisplayLogs() {
        Path filePath = Paths.get(logFilePath);
        try {
            List<String> logs = Files.readAllLines(filePath);
            for (String log : logs) {
                System.out.println(log);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Logger logger = Logger.GetInstance();
        Logger.SetLogFilePath("C:/Users/VICTUS/Desktop/2 - 3 курс сабактар отчет/new_logs.txt");
        Logger.SetLogLevel(LogLevel.WARNING);
        Thread t1 = new Thread(() -> logger.Log("Thread 1 - Info message", LogLevel.INFO));
        Thread t2 = new Thread(() -> logger.Log("Thread 2 - Warning message", LogLevel.WARNING));
        Thread t3 = new Thread(() -> logger.Log("Thread 3 - Error message", LogLevel.ERROR));
        t1.start();
        t2.start();
        t3.start();

        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.DisplayLogs();
    }
}


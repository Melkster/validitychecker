package melkster.validitychecker;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Logger {
    private static final Logger instance = new Logger();
    public static String filename = "log.txt";

    private Logger() {
        // Prevents instantiation outside Logger
    }

    public static Logger getInstance() {
        return instance;
    }

    public void log(String message) {
        System.out.println(message);
        writeToFile(message);
    }

    public void writeToFile(String message) {
        try {
            File file = new File(filename);
            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
            file.createNewFile();
            writer.newLine();
            writer.write(message);
            writer.close();
        } catch (IOException e) {
            System.out.println("Something went wrong when writing to log file.");
            e.printStackTrace();
        }
    }
}

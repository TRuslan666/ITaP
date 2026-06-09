package gradle;

import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.lang3.StringUtils;
public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        logger.info("Program started");
        
        Scanner sc = new Scanner(System.in);
        String name;
        if (sc.hasNextLine()) {
            name = StringUtils.capitalize(sc.nextLine());
        } else {
            name = "Ruslan";
        }
        logger.info("Hello, " + name + "!");
        logger.info("Program finished");
        sc.close();

        try {
        java.io.InputStream is = Main.class
                .getClassLoader()
                .getResourceAsStream("build-passport.properties");

        if (is != null) {
            java.util.Properties props = new java.util.Properties();
            props.load(is);

            logger.info("=== Build Passport ===");
            logger.info("User: " + props.getProperty("user"));
            logger.info("OS: " + props.getProperty("os"));
            logger.info("Java: " + props.getProperty("java.version"));
            logger.info("Message: " + props.getProperty("message"));
        } else {
            logger.warn("build-passport.properties not found!");
        }

    } catch (Exception e) {
        logger.error("Error reading build passport", e);
    }
    }
}
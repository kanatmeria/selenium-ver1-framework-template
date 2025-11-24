package kg.cdt.automation.framework.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    private static final Properties properties = new Properties();
    private static String environment;

    static {
        loadConfig();
    }

    private static void loadConfig() {
        try {
            // Read environment from system property (default: stage)
            environment = System.getProperty("env", "prod").toLowerCase();

            // 👇 Now points to /configs directory
            String configFile = String.format("configs/config.%s.properties", environment);
            System.out.println("🧭 Loading environment config: " + configFile);

            InputStream input = ConfigReader.class.getClassLoader().getResourceAsStream(configFile);
            if (input == null) {
                throw new RuntimeException(" Cannot find config file: " + configFile);
            }

            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException(" Failed to load environment config: " + e.getMessage(), e);
        }
    }

    public static String get(String key) {
//        return properties.getProperty(key);
        String value = properties.getProperty(key);



        // Resolve environment variable if the value starts with ENV_

        if (value != null && value.startsWith("${ENV_") && value.endsWith("}")) {

            String envVar = value.substring(6, value.length() - 1); // Extract variable name

            String envValue = System.getenv(envVar);

            if (envValue != null) {

                return envValue;

            } else {

                throw new RuntimeException("Environment variable " + envVar + " is not set!");

            }

        }



        // Resolve other ${...} placeholders

        while (value != null && value.contains("${")) {

            int start = value.indexOf("${");

            int end = value.indexOf("}", start);

            if (end == -1) break;

            String placeholder = value.substring(start + 2, end);

            String placeholderValue = properties.getProperty(placeholder, "");

            value = value.substring(0, start) + placeholderValue + value.substring(end + 1);

        }



        return value;


    }

    public static String getEnv() {
        return environment;
    }
}

package kg.cdt.automation.framework.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import kg.cdt.automation.framework.model.LoginData;

import java.io.IOException;
import java.io.InputStream;
public class TestDataReader {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static <T> T getTestData(String env, String fileName, String key, Class<T> clazz) {
        try {
            String path = String.format("testdata/%s/%s.json", env, fileName);
            InputStream input = TestDataReader.class.getClassLoader().getResourceAsStream(path);
            if (input == null) {
                throw new RuntimeException("Cannot find test data file: " + path);
            }

            JsonNode root = mapper.readTree(input);
            JsonNode node = root.path(key);
            if (node.isMissingNode()) {
                throw new RuntimeException("Key not found in JSON: " + key);
            }

            return mapper.treeToValue(node, clazz);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read test data file", e);
        }
    }

    // обертка для модели LoginData (По такому примеру можно сделать на другие модели тоже)
    public static LoginData getLoginData(String key) {
        return getTestData(
                ConfigReader.getEnv(),
                "login",
                key,
                LoginData.class
        );
    }
}

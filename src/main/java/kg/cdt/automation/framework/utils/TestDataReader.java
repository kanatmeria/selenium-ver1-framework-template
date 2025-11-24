package kg.cdt.automation.framework.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import kg.cdt.automation.framework.model.LoginData;

import java.io.File;
import java.io.IOException;

public class TestDataReader {
//    private static final ObjectMapper mapper = new ObjectMapper();
//
//    public static <T> List<T> readList(String resourcePath, Class<T> clazz){
//        try(InputStream is = JsonDataReader.class.getClassLoader().getResourceAsStream(resourcePath)){
//            if(is == null) {
//                throw new RuntimeException("Resource not found: " + resourcePath);
//            }
//            return mapper.readValue(is, mapper.getTypeFactory().constructCollectionType(List.class, clazz));
//        }catch (Exception e){
//            throw new RuntimeException("Error reading JSON: " + e.getMessage(), e);
//        }
//    }


    private static final String LOGIN_JSON = "src/test/resources/testdata/login.json";

    public static LoginData getLoginData(String key) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode root = mapper.readTree(new File(LOGIN_JSON));
            JsonNode node = root.path(key);
            if (node.isMissingNode()) {
                throw new RuntimeException("Login key not found in JSON: " + key);
            }
            return new LoginData(node.get("login").asText(), node.get("password").asText());
        } catch (IOException e) {
            throw new RuntimeException("Failed to read login.json", e);
        }
    }
}


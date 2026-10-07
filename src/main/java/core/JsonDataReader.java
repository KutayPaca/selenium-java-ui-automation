package core;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.FileReader;

public class JsonDataReader {

    private static JsonObject loginData;

    static {
        try {
            // loginData.json dosyasının yolu
            String path = "src/test/resources/loginData.json";
            FileReader reader = new FileReader(path);
            loginData = JsonParser.parseReader(reader).getAsJsonObject();
            reader.close();
        } catch (Exception e) {
            System.out.println("JSON dosyası okunamadı: " + e.getMessage());
        }
    }

    // İlgili test senaryosunun kullanıcı adını döndürür
    public static String getUsername(String userKey) {
        return loginData.getAsJsonObject(userKey).get("username").getAsString();
    }

    // İlgili test senaryosunun şifresini döndürür
    public static String getPassword(String userKey) {
        return loginData.getAsJsonObject(userKey).get("password").getAsString();
    }
}
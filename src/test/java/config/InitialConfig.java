package config;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class InitialConfig {
    private static Properties props;

    public InitialConfig() {
        Path resourceDir = Paths.get("src","test", "resources");
        String absPath = resourceDir.toFile().getAbsolutePath();
        File f = new File(absPath + "/application.properties");
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(-1);
        }
        props = new Properties();
        try {
            props.load(fileReader);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }
    public static String getDriverPath(){
        return props.getProperty("driverPath");
    }
    public static String getBaseURL(){
        return props.getProperty("baseUrl");
    }

    public static String getEmail() {
        return props.getProperty("email");
    }
    public static String getPassword(){
        return props.getProperty("password");
    }
}

package hu.masterfield.driver;

public class Settings {
    private static final String LOGIN_URL = "https://eng.digitalbank.masterfield.hu/bank/login";

    public static String getLoginUrl() {
        return LOGIN_URL;
    }
}
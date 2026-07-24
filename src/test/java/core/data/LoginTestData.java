package core.data;

import core.utils.PropertyReader;
import lombok.Getter;

@Getter
public class LoginTestData {

    private final String username;
    private final String password;
    private final String description;

    public LoginTestData(String username, String password, String description) {
        this.username = username;
        this.password = password;
        this.description = description;
    }

    public static LoginTestData validCredentials() {
        return new LoginTestData(
                PropertyReader.getProperty("user"),
                PropertyReader.getProperty("password"),
                "Valid credentials"
        );
    }

    public static LoginTestData invalidCredentialsWithUser() {
        return new LoginTestData(
                "some_emailname@gmail.com",
                PropertyReader.getProperty("password"),
                "Invalid credentials: email"
        );
    }

    public static LoginTestData invalidCredentialsWithPassword() {
        return new LoginTestData(
                PropertyReader.getProperty("user"),
                "invalid_password_1234",
                "Invalid credentials: password"
        );
    }

    @Override
    public String toString() {
        return description;
    }
}

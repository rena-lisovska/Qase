package core.data;

import lombok.Getter;

@Getter
public class LoginTestData {

    private final String username;
    private final String password;

    public LoginTestData(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return "Invalid credentials";
    }
}

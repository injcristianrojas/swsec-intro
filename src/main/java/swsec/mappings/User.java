package swsec.mappings;

public class User {

    int type;
    String username;
    String password;
    
    public User() {
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

	@Override
    public String toString() {
        return "User [username=" + username + ", type=" + type + "]";
    }

}

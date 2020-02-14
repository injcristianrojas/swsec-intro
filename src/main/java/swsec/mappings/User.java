package swsec.mappings;

public class User {

    String username;
    int type;
    
    public User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setType(int type) {
        this.type = type;
    }

	@Override
    public String toString() {
        return "User [username=" + username + ", type=" + type + "]";
    }

}

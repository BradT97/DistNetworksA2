import java.util.Date;

public class SessionKey {
    private String user, key;
    private Date lastUpdated;

    public SessionKey(String user, String key){
        this.user = user;
        this.key = key;
        this.lastUpdated = new Date();
    }

    public void updateKey(String key) {
        this.key = key;
        this.lastUpdated = new Date();
    }

    public String toString() {
        return "(" + this.user + ", " + this.key + ") created at: " + this.lastUpdated;
    }

    public String getUser() { return this.user; }
    public String getKey()  { return this.key;  }
}
public class test {
    private static DataStore db;

    public static void main(String[] args) {
        db = new DataStore();
        Login loginManager = new Login(db);
        Authorize authManager = new Authorize(db);

        String authState = loginManager.login("hayden", "1234");
        System.out.println("Logged in with auth key: " + authState);
        authState = loginManager.login("josh", "4321");
        System.out.println("Logged in with auth key: " + authState);
        System.out.println(loginManager.logout(authState));

        db.addKey("Brad","test4");
        System.out.println(db.getKeys());
        System.out.println(authManager.getStuff());
    }
}
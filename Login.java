
public class Login
{   
    private DataStore db;
    public Login(DataStore db)  { this.db = db; }

    // identity/login
    public String login(String username, String password)
    {
        String accounts[][] = {
            {"hayden", "1234"}, 
            {"josh", "4321"}
        };

        String account_password;
        if ((account_password = filter(username, accounts)) != null) {   //if not null
            if (account_password.equals(password)) {
                //Successful login

                String session_key = "INVALID";
                boolean keygenSuccess = false;
                int numRetries = -1;
                
                while (!keygenSuccess && numRetries < 5) {
                    numRetries++;
                    session_key = keygen(accounts);
                    keygenSuccess = db.addKey(username, session_key);
                }

                if (numRetries == 5) return "INVALID - 500 ERR";
                return session_key;
            }
        }

        return "INVALID";
    }

    // identity/logout
    public boolean logout(String key) {
        return db.removeKey(key);
    }

    
    
    private String filter(String searchTerm, String[][] input) {
        for (int i = 0; i < input.length; i++) {
            if (searchTerm.equals(input[i][0])){
                return input[i][1];
            }
        }
        return null;
    }

    private String keygen(String[][] accounts) {
        return "TeStK";
    }

    

    //private void rmvSessionKey(String key) {
        
    //}
}
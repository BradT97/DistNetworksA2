public class DataStore implements java.io.Serializable {
    private String[][] session_keys, currencies;
    private static final long serialVersionUID = 1;

    public DataStore() {
        session_keys = new String[0][2];
        currencies = new String[][] {
            {"AUD", "USD", "0.7"},
            {"AUD", "NZD", "1.09"},
            {"AUD", "GBP", "0.55"}
        };
    }

    public DataStore(String[][] session_keys, String[][] currencies) {
        this.session_keys = session_keys;
        this.currencies = currencies;
    }

    //      !!  Mutators  !!
    public boolean addKey(String username, String keyId) {
        try {
            // Verifies that the key is not already added.
            if (searchKey(keyId) != null) return false;
            

            // Increments the size of the session_keys array. (ideally could be on a seperate thread, or a promise?)
            session_keys = sizeArray(session_keys, 2, 1);
            
            // Adds the new session key.
            session_keys[session_keys.length - 1][0] = username; 
            session_keys[session_keys.length - 1][1] = keyId; 
            
            return true;
        }
        catch (Exception e) {
            // A dual read/write error will be thrown if 2 concurrent threads call this method.
            System.out.println("error adding key:");
            e.printStackTrace();
            return false;
        }
    }

    public boolean removeKey(String keyId) {

        System.out.println(session_keys.length);
        for (int i = 0; i < session_keys.length; i++) {
            if (session_keys[i][1].equals(keyId)) {
                session_keys[i][0] = session_keys[session_keys.length - 1][0];
                session_keys[i][1] = session_keys[session_keys.length - 1][1];
                session_keys = sizeArray(session_keys, 2, -1);
                return true;
            }
        }
        return false;/*
        } */ 
    }

    public String[] searchKey(String keyId) {
        for (int i = 0; i < session_keys.length; i++) {
            if (session_keys[i][1].equals(keyId)) return session_keys[i];
        }

        return null;
    }


    public String getKeys() {
        return toString(session_keys);
    }


    public boolean addCurrency(String from, String to) {
        try {
            // Expand the known currencies array.
            currencies = sizeArray(currencies, 3, 1);

            // Set the values of the new currency
            currencies[currencies.length - 1][0] = from;
            currencies[currencies.length - 1][1] = to;

            return true;
        }
        catch (Exception e) {
            // A dual read/write error will be thrown if 2 concurrent threads call this method.
            return false;
        }
    }

    private String[][] sizeArray(String[][] input, int size, int lengthAdjust) { 
        String[][] output = new String[input.length + lengthAdjust][2];


        // Copy input array to output array.
        for (int x = 0; x < ((input.length > output.length) ? output.length: input.length); x++) {
            for (int y = 0; y < size; y++) {
                output[x][y] = input[x][y];
            }
        }

        return output;
    }

    public String toString(String[][] input){
        String output = "";
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[i].length; j++) {
                output += input[i][j] + ((j == input.length) ? "\n": ", ");
            }
        }

        return output + "FIN\n";
    }
}


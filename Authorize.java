public class Authorize 
{
    private DataStore db;
    public Authorize(DataStore db) { this.db = db; }

    public String getStuff() {
        String[] k;
        if ((k = db.searchKey("TeStK")) != null)
            return k[0] + ", " + k[1];
        else return "No keys in DataStore";
    }
}
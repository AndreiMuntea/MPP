import Database.DatabaseManager;
import Helper.ConfigLoader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by andrei on 2017-03-06.
 */
public class Main {

    public static void main(String[] args) throws Exception
    {
        ConfigLoader config = new ConfigLoader();
        DatabaseManager db = new DatabaseManager();
        db.setDatabaseConfigLoader(config);

        String sql;
        List<String> sqlArgs;


        // DELETE
        sql = "DELETE FROM testtable WHERE idTestTable = ?";
        sqlArgs = new ArrayList<>(); sqlArgs.add("2");
        db.executeQuerry(sql, sqlArgs);

        // INSERT
        sql = "INSERT INTO testtable(idTestTable, name) VALUES(? , ?)";
        sqlArgs = new ArrayList<>(); sqlArgs.add("2"); sqlArgs.add("ana");
        db.executeQuerry(sql, sqlArgs);

        // UPDATE
        sql = "UPDATE testtable SET name = ? WHERE idTestTable = ?";
        sqlArgs = new ArrayList<>(); sqlArgs.add("anuta"); sqlArgs.add("2");
        db.executeQuerry(sql, sqlArgs);

        // SELECT
        sql = "SELECT * FROM testtable";
        sqlArgs = new ArrayList<>();
        db.executeQuerry(sql, sqlArgs);


    }
}

package Database;

import Helper.ConfigLoader;

import java.sql.*;
import java.util.List;

/**
 * Created by andrei on 2017-03-06.
 */
public class DatabaseManager {
    private Connection connection;

    private ConfigLoader databaseConfigLoader;

    public DatabaseManager(){

    }

    public void setDatabaseConfigLoader(ConfigLoader databaseConfigLoader) throws Exception
    {
        this.databaseConfigLoader = databaseConfigLoader;
        Class.forName(this.databaseConfigLoader.getJDBCDriver());
        connection = DriverManager.getConnection(
                databaseConfigLoader.getDBURL(),
                databaseConfigLoader.getUser(),
                databaseConfigLoader.getPassword());
    }

    public void executeQuerry(String sqlQuery, List<String> sqlArgs) throws Exception
    {
        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

        for(int i  = 0; i < sqlArgs.size(); ++i){
            preparedStatement.setString(i+1, sqlArgs.get(i));
        }

        preparedStatement.execute();

        ResultSet resultSet = preparedStatement.getResultSet();
        processResults(resultSet);

        if (resultSet != null) resultSet.close();
        preparedStatement.close();
    }

    private void processResults(ResultSet results) throws Exception
    {
        if (results == null) return;

        while(results.next())
        {
            int id = results.getInt("idTestTable");
            String name = results.getString("name");
            System.out.println("ID = " + id + " name = " + name);
        }
    }

    @Override
    protected void finalize() throws Throwable {
        connection.close();
        super.finalize();
    }



}

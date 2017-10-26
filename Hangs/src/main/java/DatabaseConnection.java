/*
Maneekan Yanvisit  5810405258
 */

import java.sql.*;

public class DatabaseConnection {
    private Statement statement;

    public DatabaseConnection() {
        try {
            Class.forName("org.sqlite.JDBC");
            String dbURL = "jdbc:sqlite:Vocabuary.db";
            Connection conn = DriverManager.getConnection(dbURL);
            if (conn != null) {
                DatabaseMetaData dm = (DatabaseMetaData) conn.getMetaData();
                statement = conn.createStatement();
            }
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public String getWord(String type){
        String sql = "Select Word from Vocab where Type = '"+type+"' order by RANDOM()";
        String vocab = "";
        try{
            ResultSet resultSet = statement.executeQuery(sql);
            vocab = resultSet.getString(1);
            System.out.println(vocab);
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return vocab;
    }

    public Statement getStatement(){
        return statement;
    }
}

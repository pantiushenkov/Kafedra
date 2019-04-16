package lib.java.Utils;

import lib.java.DAO.DaoFactory;

public class Config {
    public static String dbName = "starostiuk_db";
    public static String url = "jdbc:mysql://localhost:3306/" + dbName;
    public static String user = "root";
    public static String pass= "bob";
    public static String driver = "com.mysql.jdbc.Driver";

    private String factoryClassName = DaoFactory.class.getName();

    private static class Holder{
         private static Config INSTANCE = new Config();
    }
    public static String getTable(String tableName){
        return " " + dbName + "." + tableName + " ";
    }

    public static Config getInstance(){
        return Holder.INSTANCE;
    }

    public String getFactoryClassName() {
        return factoryClassName;
    }
}

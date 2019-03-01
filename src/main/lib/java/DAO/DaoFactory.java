package lib.java.DAO;

import lib.java.DAO.Entities.*;
import lib.java.Entities.*;
import lib.java.Utils.Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class DaoFactory implements IDaoFactory<Connection> {

    private Map<Class, DaoCreator> creators;

    public Connection getContext() throws PersistException {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(Config.url, Config.user, Config.pass);
        } catch (SQLException e) {
            throw new PersistException(e);
        }
        return connection;
    }


    public GenericDao getDao(Connection connection, Class dtoClass) {
        DaoCreator creator = creators.get(dtoClass);
        return creator.create(connection);
    }

    public DaoFactory() {
        try {
            Class.forName(Config.driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        creators = new HashMap<>();

        creators.put(TeacherEntity.class, (DaoCreator<Connection>) connection -> new TeacherDao(connection));
        creators.put(DepartmentEntity.class, (DaoCreator<Connection>) connection -> new DepartmentDao(connection));
        creators.put(ScientificWorkEntity.class, (DaoCreator<Connection>) connection -> new ScientificWorkDao(connection));
        creators.put(ManagementEntity.class, (DaoCreator<Connection>) connection -> new ManagementDao(connection));
    }
}

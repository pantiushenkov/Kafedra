package lib.java.Services;

import lib.java.DAO.DaoFactory;
import lib.java.DAO.GenericDao;
import lib.java.DAO.IDaoFactory;
import lib.java.DAO.PersistException;

import java.sql.Connection;

public class Service<T> {

    IDaoFactory<Connection> factory;
    Connection connection;
    private GenericDao dao;

    public Service() {
    }

    public Service(Class<T> cclass) {
        factory = new DaoFactory();
        try {
            connection = factory.getContext();
            dao = factory.getDao(connection, cclass);
        } catch (PersistException e) {
            e.printStackTrace();
        }
    }

    public GenericDao getDao() {
        return dao;
    }
}

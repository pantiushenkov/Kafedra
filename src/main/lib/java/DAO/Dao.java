package lib.java.DAO;


import lib.java.Utils.Config;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public abstract class Dao<T extends Identified<PK>, PK extends String> extends AbstractDao<T, PK> {

    private Connection connection;

    public Dao(Connection connection) {
        this.connection = connection;
    }

    public String getCreateQuery(String[] params, String table) {
        String paramsQuery = buildParams(params, "", ",");
        String[] q = new String[params.length];
        Arrays.fill(q, "?");
        return "INSERT INTO" + Config.getTable(table) + "(" + paramsQuery + ") VALUES (" + buildParams(q, "", ",") + ");";
    }


    public String getDeleteQuery(String key) {
        return "DELETE FROM" + getDbName() + " WHERE " + key + "=?;";
    }

    public String getDeleteQuery(String key1, String key2) {
        return "DELETE FROM" + getDbName() + " WHERE " + key1 + "=? " + key2 + "=?;";
    }

    public String getSelectQuery() {
        return "SELECT * FROM" + getDbName();
    }

    public String getUpdateQuery(String key, String[] params, String table) {
        String paramsQuery = buildParams(params, "= ?", ",");
        return "UPDATE" + Config.getTable(table) + "SET " + paramsQuery +
                "WHERE " + key + "= ?;";
    }

    public String getUpdateQuery(String key, String key2, String[] params) {
        String paramsQuery = buildParams(params, "= ?", ",");
        return "UPDATE" + getDbName() + "SET " + paramsQuery +
                "WHERE " + key + "= ? AND " + key2 + "= ?;";
    }

    public String getSearchQuery(String[] params) {
        return getSearchQuery(params, "");
    }

    public String getSearchQuery(String[] params, String join) {
        String paramsQuery = buildParams(params, "like ?", "AND");
        return "SELECT * FROM" + getDbName() + join + " WHERE " + paramsQuery;
    }

    protected void prepareStatementForSearch(PreparedStatement statement, Object[] objects) throws PersistException, SQLException {
        try {
            for (int i = 0; i < objects.length; i++) {
                statement.setString(i + 1, likeQuery(objects[i]));
            }
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    private String likeQuery(Object object) {
        return object == null || object.equals("") ? "%" : object + "%";
    }

    private String buildParams(String[] params, String equals, String joined) {
        String paramsQuery = "";
        for (int i = 0; i < params.length; i++) {
            String param = params[i];
            paramsQuery += param + " " + equals + " " + (i + 1 == params.length ? " " : " " + joined + " ");
        }
        return paramsQuery;
    }

    @Override
    public void delete(T object) throws PersistException {
        String sqls = getDeleteQuery();
        for (String sql : sqls.split(";")) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                System.out.println(statement);
                statement.setObject(1, object.getKey());
                statement.executeUpdate();
            } catch (Exception e) {
                throw new PersistException(e);
            }
        }
    }

    @Override
    public T persist(T object) throws PersistException {
        String sqls = getCreateQuery();
        for (String sql : sqls.split(";")) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                prepareStatementForInsert(statement, object);
                System.out.println(statement);
                statement.executeUpdate();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                throw new PersistException(e);
            }
        }
        return object;
    }

    @Override
    public T getByPK(String key) throws PersistException {
        List<T> list;
        String sql = getSelectQuery();
        sql += " WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, key);
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
        } catch (Exception e) {
            throw new PersistException(e);
        }
        if (list == null || list.size() == 0) {
            throw new PersistException("Record with PK = " + key + " not found.");
        }
        if (list.size() > 1) {
            throw new PersistException("Received more than one record.");
        }
        return list.iterator().next();
    }

    @Override
    public void update(T object) throws PersistException {
        String sqls = getUpdateQuery();
        for (String sql : sqls.split(";")) {
            System.out.println(sql);
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                prepareStatementForUpdate(statement, object);
                System.out.println(statement);
                statement.executeUpdate();
            } catch (Exception e) {
                throw new PersistException(e);
            }
        }
    }

    @Override
    public List<T> getAll() throws PersistException {
        List<T> list;
        String sql = getSelectQuery();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
        } catch (Exception e) {
            throw new PersistException(e);
        }
        return list;
    }

    @Override
    public List<T> search(T object) throws PersistException {
        List<T> list;
        String sql = getSearchQuery();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            prepareStatementForSearch(statement, object);
            System.out.println(statement);
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
        } catch (Exception e) {
            throw new PersistException(e);
        }
        return list;
    }
}

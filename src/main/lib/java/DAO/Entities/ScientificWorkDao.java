package lib.java.DAO.Entities;

import lib.java.DAO.Dao;
import lib.java.DAO.PersistException;
import lib.java.Entities.ScientificWorkEntity;
import lib.java.Utils.Config;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.LinkedList;
import java.util.List;

public class ScientificWorkDao extends Dao<ScientificWorkEntity, String> {

    public ScientificWorkDao(Connection connection) {
        super(connection);
    }

    private String tableName = "science_themes";

    private String key = "id";
    private String department = "cathedra_id";
    private String name = tableName + ".name";
    private String customer = "customer";
    private String start = "start_date";
    private String end = "end_date";

    private String[] params = new String[]{department, name, customer, start, end};
    private String[] allParams = new String[]{key, department, name, customer, start, end};
    private String innerJoin = "INNER JOIN cathedras c ON science_themes.cathedra_id = c.id";

    @Override
    public String getDbName() {
        return Config.getTable(tableName);
    }

    @Override
    public String getSelectQuery() {
        return super.getSelectQuery() + innerJoin;
    }

    @Override
    public String getCreateQuery() {
        return super.getCreateQuery(allParams, tableName);
    }

    @Override
    public String getUpdateQuery() {
        return super.getUpdateQuery(key, params, tableName);
    }

    @Override
    public String getDeleteQuery() {
        return super.getDeleteQuery(key);
    }

    @Override
    public String getSearchQuery() {
        return super.getSearchQuery(params, innerJoin);
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, ScientificWorkEntity object) throws PersistException {
        try {
            statement.setString(1, object.getKey());
            prepareStatement(statement, object, 2);
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    private void prepareStatement(PreparedStatement statement, ScientificWorkEntity object, int start) throws PersistException {
        try {
            statement.setString(start++, object.getDepartment());
            statement.setString(start++, object.getName());
            statement.setString(start++, object.getCustomer());
            statement.setDate(start++, object.getStart());
            statement.setDate(start, object.getEnd());
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistException(e);
        }
    }

    @Override
    protected void prepareStatementForSearch(PreparedStatement statement, ScientificWorkEntity object) throws PersistException {
        try {
            super.prepareStatementForSearch(statement, new Object[]{
                            object.getDepartment(), object.getName(), object.getCustomer(), object.getStart(), object.getEnd()
                    }
            );
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, ScientificWorkEntity object) throws PersistException {
        try {
            prepareStatement(statement, object, 1);
            statement.setString(allParams.length, object.getKey());
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    @Override
    protected List<ScientificWorkEntity> parseResultSet(ResultSet rs) {
        LinkedList<ScientificWorkEntity> result = new LinkedList<>();

        try {
            while (rs.next()) {
                ScientificWorkEntity scientificWorkEntity = new ScientificWorkEntity(
                        rs.getString(key),
                        rs.getString(department),
                        rs.getString(name),
                        rs.getString(customer),
                        rs.getDate(start),
                        rs.getDate(end)
                );
                result.add(scientificWorkEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public ScientificWorkEntity create() throws PersistException {
        ScientificWorkEntity scientificWorkEntity = new ScientificWorkEntity();
        return persist(scientificWorkEntity);
    }
}

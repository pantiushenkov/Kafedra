package lib.java.DAO.Entities;

import lib.java.DAO.Dao;
import lib.java.DAO.PersistException;
import lib.java.Entities.ManagementEntity;
import lib.java.Utils.Config;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

public class ManagementDao extends Dao<ManagementEntity, String> {

    public ManagementDao(Connection connection) {
        super(connection);
    }

    private String key = "idTeacher";
    private String key2 = "idScientificWork";
    private String start = "Start";
    private String end = "End";

    private String[] params = new String[]{start, end};
    private String[] allParams = new String[]{key, key2, start, end};
    private String tableName = "Management";

    @Override
    public String getDbName() {
        return Config.getTable(tableName);
    }

    @Override
    public String getCreateQuery() {
        return super.getCreateQuery(allParams, tableName);
    }

    @Override
    public String getUpdateQuery() {
        return super.getUpdateQuery(key, key2, params);
    }

    @Override
    public String getDeleteQuery() {
        return super.getDeleteQuery(key, key2);
    }

    @Override
    public String getSearchQuery() {
        return super.getSearchQuery(allParams);
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, ManagementEntity object) throws PersistException {
        try {
            statement.setString(1, object.getKey());
            statement.setString(2, object.getKey2());
            prepareStatement(statement, object, 3);
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    private void prepareStatement(PreparedStatement statement, ManagementEntity object, int start) throws PersistException {
        try {
            statement.setDate(start++, object.getStart());
            statement.setDate(start++, object.getEnd());
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistException(e);
        }
    }

    @Override
    protected void prepareStatementForSearch(PreparedStatement statement, ManagementEntity object) throws PersistException {
        try {
            super.prepareStatementForSearch(statement, new Object[]{
                            object.getKey(),
                            object.getKey2(),
                            object.getStart(),
                            object.getEnd(),
                    }
            );
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, ManagementEntity object) throws PersistException {
        try {
            prepareStatement(statement, object, 1);
            statement.setString(3, object.getKey());
            statement.setString(4, object.getKey2());
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    @Override
    protected List<ManagementEntity> parseResultSet(ResultSet rs) {
        LinkedList<ManagementEntity> result = new LinkedList<>();

        try {
            while (rs.next()) {
                ManagementEntity managementEntity = new ManagementEntity(
                        rs.getString(key),
                        rs.getString(key2),
                        rs.getDate(start),
                        rs.getDate(end)
                );
                result.add(managementEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public ManagementEntity create() throws PersistException {
        ManagementEntity managementEntity = new ManagementEntity();
        return persist(managementEntity);
    }
}

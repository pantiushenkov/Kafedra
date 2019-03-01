package lib.java.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;


public abstract class AbstractDao<T extends Identified<PK>, PK extends Integer> implements GenericDao<T, PK> {

    public abstract String getDbName();

    public abstract String getCreateQuery();

    public abstract String getUpdateQuery();

    public abstract String getDeleteQuery();

    public abstract String getSearchQuery();

    protected abstract List<T> parseResultSet(ResultSet rs) throws PersistException;

    protected abstract void prepareStatementForInsert(PreparedStatement statement, T object) throws PersistException;

    protected abstract void prepareStatementForSearch(PreparedStatement statement, T object) throws PersistException;

    protected abstract void prepareStatementForUpdate(PreparedStatement statement, T object) throws PersistException;
}

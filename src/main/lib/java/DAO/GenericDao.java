package lib.java.DAO;


import java.io.Serializable;
import java.util.List;

public interface GenericDao<T extends Identified<PK>, PK extends Serializable> {

    T create() throws PersistException;

    void delete(T object) throws PersistException;

    List<T> getAll() throws PersistException;

    T getByPK(PK key) throws PersistException;

    T persist(T object) throws PersistException;

    List<T> search(T object) throws PersistException;

    void update(T object) throws PersistException;

}

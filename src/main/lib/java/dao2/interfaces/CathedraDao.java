package lib.java.dao2.interfaces;

import lib.java.model.Cathedra;

public interface CathedraDao extends BaseDao<Cathedra> {
    Cathedra getByName(String name);
}

package lib.java.dao2.interfaces;

import lib.java.model.Scientist;

public interface ScientistDao extends BaseDao<Scientist> {
    Scientist getByName(String name);
}

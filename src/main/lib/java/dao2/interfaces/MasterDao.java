package lib.java.dao2.interfaces;

import lib.java.model.Cathedra;
import lib.java.model.Master;

import java.util.List;

public interface MasterDao extends BaseDao<Master> {
    public List<Master> getAllByCathedra(Cathedra cathedra);
}

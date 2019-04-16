package lib.java.dao2.interfaces;

import lib.java.model.Cathedra;
import lib.java.model.Postgraduate;

import java.util.List;

public interface PostgraduateDao extends BaseDao<Postgraduate> {
    public List<Postgraduate> getAllByCathedra(Cathedra cathedra);
}

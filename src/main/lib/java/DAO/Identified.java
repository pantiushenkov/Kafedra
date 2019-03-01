package lib.java.DAO;

import java.io.Serializable;

public interface Identified<PK extends Serializable> {

    PK getKey();

    Object[] getRow();
}

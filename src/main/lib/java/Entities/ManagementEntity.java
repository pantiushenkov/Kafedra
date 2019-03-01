package lib.java.Entities;

import lib.java.DAO.Identified;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ManagementEntity implements Identified<Integer> {
    private Integer key;
    private Integer key2;
    private Date start;
    private Date end;

    public Object[] getRow() {
        return new Object[]{key, key2, start, end};
    }
}


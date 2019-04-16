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
public class ManagementEntity implements Identified<String> {
    private String key;
    private String key2;
    private Date start;
    private Date end;

    public Object[] getRow() {
        return new Object[]{key, key2, start, end};
    }
}


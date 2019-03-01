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
public class ScientificWorkEntity implements Identified<Integer> {
    private Integer key;
    private Integer department;
    private String name;
    private String manager;
    private String customer;
    private Date start;
    private Date end;

    public Object[] getRow() {
        return new Object[]{key, department, name, manager, customer, start, end};
    }
}


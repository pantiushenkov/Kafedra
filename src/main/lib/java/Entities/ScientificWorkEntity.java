package lib.java.Entities;

import lib.java.DAO.Identified;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ScientificWorkEntity implements Identified<String> {
    private String key;
    private String department;
    private String name;
    private String customer;
    private Date start;
    private Date end;

    public ScientificWorkEntity(
            String department,
            String name,
            String customer,
            Date start,
            Date end) {
        this.key = UUID.randomUUID().toString();
        this.department = department;
        this.name = name;
        this.customer = customer;
        this.start = start;
        this.end = end;
    }

    public Object[] getRow() {
        return new Object[]{department, name, customer, start, end};
    }
}


package lib.java.Entities;

import lib.java.DAO.Identified;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentEntity implements Identified<Integer> {
    private Integer key;
    private String name;
    private String phone;

    public Object[] getRow() {
        return new Object[]{key, name, phone};
    }
}


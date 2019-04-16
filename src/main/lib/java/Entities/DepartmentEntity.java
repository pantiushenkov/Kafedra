package lib.java.Entities;

import lib.java.DAO.Identified;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentEntity implements Identified<String> {
    private String key;
    private String name;
    private String phone;

    public DepartmentEntity(String name, String phone) {
        this.key = UUID.randomUUID().toString();
        this.name = name;
        this.phone = phone;
    }

    public Object[] getRow() {
        return new Object[]{name, phone};
    }
}


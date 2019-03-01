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
public class TeacherEntity implements Identified<Integer> {
    private Integer key;
    private Integer department;
    private String position;
    private String title;
    private String surname;
    private Date startDate;
    private String phone;
    private String sex;

    public Object[] getRow() {
        return new Object[]{key, department, position, title, surname, startDate, phone, sex};
    }
}


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
public class TeacherEntity implements Identified<String> {
    private String key;
    private String department;
    private String position;
    private String title;
    private Date startDate;
    private String surname;
    private String phone;
    private String sex;

    public TeacherEntity(
            String department,
            String position,
            String title,
            Date startDate,
            String surname,
            String phone,
            String sex) {

        this.key = UUID.randomUUID().toString();
        this.department = department;
        this.position = position;
        this.title = title;
        this.surname = surname;
        this.startDate = startDate;
        this.phone = phone;
        this.sex = sex;
    }

    public Object[] getRow() {
        return new Object[]{department, position, title, startDate, surname, phone, sex};
    }
}


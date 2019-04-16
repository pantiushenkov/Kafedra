package lib.java.UI.presenter.petlya;

import lib.java.model.Postgraduate;

public class Aspirant {
    String name;
    String sex;
    String num;
    String dep;
    String prof;
    String date1;
    String date2;
    String theme;
    String date3;

    public Aspirant(String name,
                    String sex,
                    String num,
                    String dep,
                    String prof,
                    String date1,
                    String date2,
                    String theme,
                    String date3) {
        this.date1 = date1;
        this.date2 = date2;
        this.date3 = date3;
        this.name = name;
        this.sex = sex;
        this.prof = prof;
        this.num = num;
        this.theme = theme;
        this.dep = dep;
    }

    public Aspirant(Postgraduate byId, String secondName, String name) {
		this.name = byId.getSecondName();
		this.sex = byId.getGender();
		this.num = byId.getPhoneNumber();
		this.date1 = byId.getStartDate().toString();
		this.date2 = byId.getEndDate().toString();
		this.date3 = byId.getThesisProtectionDate().toString();
		this.theme = byId.getThesisTheme();
		this.prof = secondName;
		this.dep = name;
    }
}

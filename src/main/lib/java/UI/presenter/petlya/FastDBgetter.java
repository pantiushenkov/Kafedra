package lib.java.UI.presenter.petlya;

import lib.java.dao2.impl.CathedraDaoImpl;
import lib.java.dao2.impl.PostgraduateDao;
import lib.java.dao2.impl.TeacherDaoImpl;
import lib.java.dao2.impl.WorksAndJobsDaoImpl;
import lib.java.dao2.interfaces.CathedraDao;
import lib.java.dao2.interfaces.TeacherDao;
import lib.java.dao2.interfaces.WorksAndJobsDao;
import lib.java.model.Cathedra;
import lib.java.model.Postgraduate;
import lib.java.model.ScientificWork;
import lib.java.model.Teacher;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FastDBgetter {
    ArrayList<DBdata> myArray;
    CathedraDao cathedraDao = new CathedraDaoImpl();
    TeacherDao teacherDao = new TeacherDaoImpl();
    PostgraduateDao postgraduateDao = new PostgraduateDao();
    WorksAndJobsDao worksAndJobsDao = new WorksAndJobsDaoImpl();

    public FastDBgetter() {

    }

    public ArrayList<DBdata> getDeps() {
        ArrayList<DBdata> array = new ArrayList<DBdata>();
        List<Cathedra> all = cathedraDao.getAll();
        for (Cathedra cat:
             all) {
            array.add(new DBdata(cat.getId(), cat.getName()));
        }
        myArray = array;
        return array;
    }

    public ArrayList<DBdata> getProfs() {
        ArrayList<DBdata> array = new ArrayList<DBdata>();
        List<Teacher> all = teacherDao.getAll();
        for (Teacher cat:
                all) {
            array.add(new DBdata(cat.getScientistId(), cat.getSecondName()));
        }

        myArray = array;
        return array;
    }

    public ArrayList<DBdata> getAspsByProf(int num) {
        String id = myArray.get(num).id;
        ArrayList<DBdata> array = new ArrayList<DBdata>();
        List<Postgraduate> postgraduatesByTeacher = postgraduateDao.getPostgraduatesByTeacher(id);
        for (Postgraduate post:
             postgraduatesByTeacher) {
            array.add(new DBdata(post.getScientistId(), post.getSecondName()));
        }
        myArray = array;
        return array;
    }

    public ArrayList<DBdata> getAspsByDeps(int num) {
        String id = myArray.get(num).id;
        ArrayList<DBdata> array = new ArrayList<DBdata>();
        List<Postgraduate> postgraduatesByTeacher = postgraduateDao.getPostgraduatesByCathedra(id);
        for (Postgraduate post:
                postgraduatesByTeacher) {
            array.add(new DBdata(post.getScientistId(), post.getSecondName()));
        }
        myArray = array;
        return array;
    }

    public ArrayList<DBdata> getProfsW() {
        ArrayList<DBdata> array = new ArrayList<DBdata>();
        for (Teacher teacher : teacherDao.getAll()) {
            int count = 0;
            for (Postgraduate postgraduate : postgraduateDao.getPostgraduatesByTeacher(teacher.getScientistId())) {
               count+=worksAndJobsDao.getScientificWorksByAuthorId(postgraduate.getScientistId()).size();
            }
            array.add(new DBdata( ""+count,teacher.getSecondName()));
        }
        myArray = array;
        return array;
    }


    public Aspirant getAsp(int num) {
        String id = myArray.get(num + 1).id;
        Postgraduate byId = postgraduateDao.getById(id);
        return new Aspirant(byId, teacherDao.getById(byId.getChiefId()).getSecondName(), cathedraDao.getById(byId.getCathedraId()).getName());
    }


    public ArrayList<DBdata> getWorks(int num) {
        String id = myArray.get(num + 1).id;
        ArrayList<DBdata> array = new ArrayList<DBdata>();
        array.add(new DBdata(myArray.get(num + 1).id, myArray.get(num + 1).name));
        List<ScientificWork> scientificWorksByAuthorId = worksAndJobsDao.getScientificWorksByAuthorId(id);
        for (ScientificWork post:
                scientificWorksByAuthorId) {
            array.add(new DBdata(post.getName(), ""+post.getYearOfJob()));
        }
        return array;
    }

}

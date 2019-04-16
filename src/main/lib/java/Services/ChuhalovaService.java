package lib.java.Services;

import lib.java.dao2.impl.MasterDaoImpl;
import lib.java.dao2.impl.PostgraduateDaoImpl;
import lib.java.dao2.interfaces.*;
import lib.java.model.*;

import java.util.List;

public class ChuhalovaService {

    private ScienceThemeDao scienceThemeDao;
    private CathedraDao cathedraDao;
    private PostgraduateDao postgraduateDao;
    private MasterDao masterDao;
    private TeacherDao teacherDao;

    public ChuhalovaService() {
    }

    public ChuhalovaService(ScienceThemeDao scienceThemeDao,
                            CathedraDao cathedraDao,
                            PostgraduateDao postgraduateDao,
                            MasterDao masterDao,
                            TeacherDao teacherDao) {
        this.cathedraDao = cathedraDao;
        this.teacherDao = teacherDao;
        this.scienceThemeDao = scienceThemeDao;
        this.postgraduateDao = postgraduateDao;
        this.masterDao = masterDao;
    }

    public List<ScienceTheme> getScienceThemes() {
        return scienceThemeDao.getAll();
    }

    public List<ScienceTheme> getScienceThemesByCathedra(Cathedra cathedra) {
        return scienceThemeDao.getAllByCathedra(cathedra);
    }

    public List<Cathedra> getCathedras() {
        return cathedraDao.getAll();
    }

    public void updateScienceTheme(ScienceTheme scienceTheme) {
        scienceThemeDao.update(scienceTheme);
    }

    public void updateMaster(Master master) {
        masterDao.update(master);
    }

    public void editTeacher(Teacher teacher) {
        teacherDao.update(teacher);
    }

    public void updatePostgraduate(Postgraduate postgraduate) {
        postgraduateDao.update(postgraduate);
    }

    public void createScienceTheme(ScienceTheme scienceTheme) {
        scienceThemeDao.add(scienceTheme);
    }

    public void createMaster(Master master) {
        masterDao.add(master);
    }

    public void createPostgraduate(Postgraduate postgraduate) {
        postgraduateDao.add(postgraduate);
    }

    public void createTeacher(Teacher teacher) {
        teacherDao.add(teacher);
    }

    public void deleteScienceTheme(String id) {
        scienceThemeDao.delete(id);
    }

    public void deleteMaster(String id) {
        masterDao.delete(id);
    }

    public void deleteTeacher(String id) {
        teacherDao.delete(id);
    }

    public void deletePostgraduate(String id) {
        postgraduateDao.delete(id);
    }

    public List<Postgraduate> getAllPostgraduates() {
        return postgraduateDao.getAll();
    }

    public List<Postgraduate> getAllPostgraduatesByCathedra(Cathedra cathedra) {
        return postgraduateDao.getAllByCathedra(cathedra);
    }

    public List<Master> getMasters() {
        return masterDao.getAll();
    }

    public List<Master> getMastersByCathedra(Cathedra cathedra) {
        return masterDao.getAllByCathedra(cathedra);
    }

    public List<Teacher> getTeachers() {
        return teacherDao.getAll();
    }

    public List<Teacher> getTeachersByCathedra(Cathedra cathedra) {
        return teacherDao.getAllByCathedra(cathedra);
    }
}

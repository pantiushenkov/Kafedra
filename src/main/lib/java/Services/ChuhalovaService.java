package lib.java.Services;

import lib.java.dao2.impl.PostgraduateDao;
import lib.java.dao2.interfaces.BaseDao;
import lib.java.dao2.interfaces.CathedraDao;
import lib.java.dao2.interfaces.ScienceThemeDao;
import lib.java.dao2.interfaces.TeacherDao;
import lib.java.model.*;

import java.util.List;

public class ChuhalovaService {

    private ScienceThemeDao scienceThemeDao;
    private CathedraDao cathedraDao;
    private PostgraduateDao postgraduateDao;
    private BaseDao<Master> masterDao;
    private TeacherDao teacherDao;

    public ChuhalovaService() {
    }

    public ChuhalovaService(ScienceThemeDao scienceThemeDao,
                            CathedraDao cathedraDao,
                            PostgraduateDao postgraduateDao,
                            BaseDao<Master> masterDao,
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

    public List<Cathedra> getCathedras() {
        return cathedraDao.getAll();
    }

    public void updateScienceTheme(ScienceTheme scienceTheme) {
        scienceThemeDao.update(scienceTheme);
    }

    public void createScienceTheme(ScienceTheme scienceTheme) {
        scienceThemeDao.add(scienceTheme);
    }

    public void deleteScienceTheme(String id) {
        scienceThemeDao.delete(id);
    }

    public List<Postgraduate> getAllPostgraduates() {
        return postgraduateDao.getAll();
    }

    public List<Master> getMasters() {
        return masterDao.getAll();
    }

    public List<Teacher> getTeachers() {
        return teacherDao.getAll();
    }
}

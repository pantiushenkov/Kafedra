package lib.java.Utils;


import lib.java.Services.ChuhalovaService;
import lib.java.Services.MasterService;
import lib.java.dao2.impl.*;
import lib.java.dao2.interfaces.*;

public class ServiceFactory {

    private static MasterService masterService;
    private static ChuhalovaService chuhalovaService;
    private static MasterDao masterDao = new MasterDaoImpl();
    private static CathedraDao cathedraDao = new CathedraDaoImpl();
    private static ScienceThemeDao scienceThemeDao = new ScienceThemeDaoImpl();
    private static TeacherDao teacherDao = new TeacherDaoImpl();
    private static WorksAndJobsDao worksAndJobsDao = new WorksAndJobsDaoImpl();
    private static ScientistDao scientistBaseDao = new ScientistDaoImpl();
    private static PostgraduateDao postgraduateDao = new PostgraduateDaoImpl();

    private ServiceFactory() {
    }

    public static MasterService getMasterService() {
        if (masterService == null) {
            masterService = new MasterService(masterDao,
                    cathedraDao,
                    scienceThemeDao,
                    teacherDao,
                    worksAndJobsDao,
                    scientistBaseDao);
        }
        return masterService;
    }

    public static ChuhalovaService getChuhalovaService() {
        if (chuhalovaService == null) {
            chuhalovaService = new ChuhalovaService(scienceThemeDao,
                    cathedraDao,
                    postgraduateDao,
                    masterDao,
                    teacherDao);
        }
        return chuhalovaService;
    }

}

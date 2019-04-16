package lib.java.Utils;


import lib.java.dao2.impl.*;
import lib.java.dao2.interfaces.*;
import lib.java.model.Master;

public class ServiceFactory {

//    private static MasterService masterService;
//    private static ChuhalovaService chuhalovaService;
    private static BaseDao<Master> masterDao = new MasterDao();
    private static CathedraDao cathedraDao = new CathedraDaoImpl();
    private static ScienceThemeDao scienceThemeDao = new ScienceThemeDaoImpl();
    private static TeacherDao teacherDao = new TeacherDaoImpl();
    private static WorksAndJobsDao worksAndJobsDao = new WorksAndJobsDaoImpl();
    private static ScientistDao scientistBaseDao = new ScientistDaoImpl();
    private static PostgraduateDao postgraduateDao = new PostgraduateDao();

    private ServiceFactory() {
    }

//    public static MasterService getMasterService() {
//        if (masterService == null) {
//            masterService = new MasterService(masterDao,
//                    cathedraDao,
//                    scienceThemeDao,
//                    teacherDao,
//                    worksAndJobsDao,
//                    scientistBaseDao);
//        }
//        return masterService;
//    }
//
//    public static ChuhalovaService getChuhalovaService() {
//        if (chuhalovaService == null) {
//            chuhalovaService = new ChuhalovaService(scienceThemeDao,
//                    cathedraDao,
//                    postgraduateDao,
//                    masterDao,
//                    teacherDao);
//        }
//        return chuhalovaService;
//    }

}

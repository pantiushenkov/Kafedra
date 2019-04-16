package lib.java.dao2.interfaces;

import lib.java.model.ScienceTheme;

import java.util.List;

public interface ScienceThemeDao extends BaseDao<ScienceTheme> {
    ScienceTheme getByName(String name);

    List<ScienceTheme> getThemesOfWork(String workId);

    String getChiefId(ScienceTheme scienceTheme);
}

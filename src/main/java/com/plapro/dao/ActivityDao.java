package com.plapro.dao;

import java.util.List;

import com.plapro.beans.Activity;
import com.plapro.beans.Chart;

public interface ActivityDao {
	void addActivity(Activity a);
	List<Activity> showActivityProject(int idProject);
	Chart getChartActivity(int idProject);
	String getDataPoints(Chart c);
}
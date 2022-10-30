package com.plapro.dao;

import java.util.List;

import com.plapro.beans.Task;

public interface TaskDao {
	void addTask(Task t);
	int countTaskProject(int idProject);
	int countFinishTaskProject(int idProject);
	int countUnfinishTaskProject(int idProject);
	Task getTaskById(int id);
	int getLastId();
	int countUnstartedTaskProject(int idProject);
	String getDataPoints(int idProject);
	List<Integer> getFinishedTask(int idProject);
	List<Integer> getTaskInProgress(int idProject);
	List<Integer> getTaskToDo(int idProject);
	void editTask(Task t);
	int countTasksToDoLate(int idProject);
	int countTasksInProgressLate(int idProject);
	int countTasksCompletedLate(int idProject);
	List<Integer> listUpcommingEndTask(int idProject);
	List<Integer> listLateTasks(int idProject);
	int getProjectProgress(int idProject);
}

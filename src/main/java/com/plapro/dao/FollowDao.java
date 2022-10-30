package com.plapro.dao;

import java.util.List;


public interface FollowDao {
	int nbFollowers(String id);
	int nbFollowing(String id);
	List<Integer> listIdFollowers(String id);
	List<Integer> listIdFollows(String id);
	void follow(String idUser, String idToFollow);
	void unfollow(String idUser, String idToUnfollow);
	boolean isFollowing(String idUser, String idToMatch);
}

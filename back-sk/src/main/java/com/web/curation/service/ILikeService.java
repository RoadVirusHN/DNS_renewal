package com.web.curation.service;

public interface ILikeService {
	public int insertLike(int b_index, int like_u_index);
	public int infoLike(int b_index);
	public int deleteLike(int b_index, int like_u_index);
	public int doILike(int b_index, int like_u_index);
}

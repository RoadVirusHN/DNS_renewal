package com.web.curation.dto;

public class LikesDto {

	private int b_index;
	private int like_u_index;
	private int likes;
	private boolean do_i_like;
	
	public LikesDto() {
		super();
	}

	public LikesDto(int b_index, int like_u_index) {
		super();
		this.b_index = b_index;
		this.like_u_index = like_u_index;
	}
	
	public LikesDto(int likes, boolean do_i_like) {
		super();
		this.likes = likes;
		this.do_i_like = do_i_like;
	}

	public int getB_index() {
		return b_index;
	}

	public void setB_index(int b_index) {
		this.b_index = b_index;
	}

	public int getLike_u_index() {
		return like_u_index;
	}

	public void setLike_u_index(int like_u_index) {
		this.like_u_index = like_u_index;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public boolean isDo_i_like() {
		return do_i_like;
	}

	public void setDo_i_like(boolean do_i_like) {
		this.do_i_like = do_i_like;
	}

	@Override
	public String toString() {
		return "LikesDto [b_index=" + b_index + ", like_u_index=" + like_u_index + ", likes=" + likes + ", do_i_like="
				+ do_i_like + "]";
	}
	
}

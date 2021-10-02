package com.facebook.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="likes")
public class Like {
	
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(unique= true)
private int likeId;
private int postId;
private int countLike;
private int likeUserId;
private String likeUserName;
private String likeUserPic;


@ManyToOne
private Post post;
	
	
	
	
	
	
	public Post getPost() {
	return post;
}
public void setPost(Post post) {
	this.post = post;
}
	public int getLikeId() {
		return likeId;
	}
	public int getCountLike() {
		return countLike;
	}
	public void setCountLike(int countLike) {
		this.countLike = countLike;
	}
	public void setLikeId(int likeId) {
		this.likeId = likeId;
	}
	public int getPostId() {
		return postId;
	}
	public void setPostId(int postId) {
		this.postId = postId;
	}
	public int getLikeUserId() {
		return likeUserId;
	}
	public void setLikeUserId(int likeUserId) {
		this.likeUserId = likeUserId;
	}
	public String getLikeUserName() {
		return likeUserName;
	}
	public void setLikeUserName(String likeUserName) {
		this.likeUserName = likeUserName;
	}
	public String getLikeUserPic() {
		return likeUserPic;
	}
	public void setLikeUserPic(String likeUserPic) {
		this.likeUserPic = likeUserPic;
	}
	
	
	
	
	
	
}

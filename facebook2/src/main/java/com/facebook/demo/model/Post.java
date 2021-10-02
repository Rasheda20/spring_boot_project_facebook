package com.facebook.demo.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

//import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name="Post")
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int postId;
	@Column(length = 1000)
	private String postDescreption;
	private String postImgUrl;
	private int postLike;
	//private CreationTimestamp postTime;
	private Timestamp postTime;
	private String userName;
	private String userImageUrl;
	
	@ManyToOne
	private User user;
	
	
	@OneToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY, mappedBy="post")
	private List<Comment> comments =new ArrayList<>();
	
	@OneToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY, mappedBy="post")
	private List<Like> like =new ArrayList<>();
	
	
	
	 
	
	
	
	
	
	
	
	
	
	
	
	
	
	public List<Like> getLike() {
		return like;
	}
	public void setLike(List<Like> like) {
		this.like = like;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserImageUrl() {
		return userImageUrl;
	}
	
	
	
	
	
	public void setUserImageUrl(String userImageUrl) {
		this.userImageUrl = userImageUrl;
	}
	public int getPostId() {
		return postId;
	}
	public void setPostId(int postId) {
		this.postId = postId;
	}
	public String getPostDescreption() {
		return postDescreption;
	}
	public void setPostDescreption(String postDescreption) {
		this.postDescreption = postDescreption;
	}
	public String getPostImgUrl() {
		return postImgUrl;
	}
	public void setPostImgUrl(String postImgUrl) {
		this.postImgUrl = postImgUrl;
	}
	public int getPostLike() {
		return postLike;
	}
	public void setPostLike(int postLike) {
		this.postLike = postLike;
	}
	public Timestamp getPostTime() {
		return postTime;
	}
	public void setPostTime(Timestamp postTime) {
		this.postTime = postTime;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<Comment> getComments() {
		return comments;
	}
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	
	
	
	
	
	
	
	
//	@Override
//	public String toString() {
//		return "Post [postId=" + postId + ", postDescreption=" + postDescreption + ", postImgUrl=" + postImgUrl
//				+ ", postLike=" + postLike + ", postTime=" + postTime + ", user=" + user + "]";
//	}
	
	
	
	
}

package com.facebook.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Comment")
public class Comment {
	
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(unique= true)	
private int commentId;
private String commentDescription;
private int postId;
private String commentUser;

@ManyToOne
private Post post;

public int getCommentId() {
	return commentId;
}

public void setCommentId(int commentId) {
	this.commentId = commentId;
}

public String getCommentDescription() {
	return commentDescription;
}

public void setCommentDescription(String commentDescription) {
	this.commentDescription = commentDescription;
}

public int getPostId() {
	return postId;
}

public void setPostId(int postId) {
	this.postId = postId;
}

public String getCommentUser() {
	return commentUser;
}

public void setCommentUser(String commentUser) {
	this.commentUser = commentUser;
}

public Post getPost() {
	return post;
}

public void setPost(Post post) {
	this.post = post;
}


	
}

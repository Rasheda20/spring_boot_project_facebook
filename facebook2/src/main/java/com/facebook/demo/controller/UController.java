package com.facebook.demo.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.facebook.demo.model.Comment;
import com.facebook.demo.model.Like;
import com.facebook.demo.model.LikeCount;
import com.facebook.demo.model.Post;
import com.facebook.demo.model.User;
import com.facebook.demo.repo.AllCommentRepository;
import com.facebook.demo.repo.AllLikeRepository;
import com.facebook.demo.repo.AllPostRepository;
import com.facebook.demo.repo.AllUserRepository;
import com.facebook.demo.repo.CommentRepository;
import com.facebook.demo.repo.PostCommentRepository;
import com.facebook.demo.repo.PostRepository;
import com.facebook.demo.repo.UserRepository;
import com.facebook.demo.service.LikeCheckService;

@Controller
@RequestMapping("/user")
public class UController {

@Autowired
private UserRepository userRepository;

@Autowired
private PostRepository postRepository;

@Autowired
private AllPostRepository allPostRepository;

@Autowired
private AllUserRepository allUserRepository;

@Autowired
private PostCommentRepository postCommentRepository;


@Autowired
private CommentRepository commentRepository;

@Autowired
private AllCommentRepository allCommentRepository;


@Autowired
private AllLikeRepository allLikeRepository;

@Autowired
private LikeCheckService likeCheckService;



//method for using common data to response
@ModelAttribute
public void addCommonData(Model model, Principal principal) {
	String userName = principal.getName();
	System.out.println(userName);
	
	User user = userRepository.getUserByUserName(userName);
	System.out.println(user); 
	model.addAttribute("user", user);
	
}

//open home page
//@RequestMapping("/index")	
//public String home(Model model, Principal principal ) {
//	return "home";
//}

//open profile page
@GetMapping("/profile")	
public String home(Model model) {
	//model.addAttribute("post", new Post());
	return "profile";	
	
}


@GetMapping("/profile/post")	
public String post(Model model) {
	model.addAttribute("post", new Post());
	return "postForm";	
	
}

@PostMapping("/profile/post/processPost")
public String processPost(@ModelAttribute Post post, @RequestParam("postImg") MultipartFile file, Principal principal) {
	try {
	String name= principal.getName();
	
	User user =this.userRepository.getUserByUserName(name);
	
	//processing and uploading file
	if(file.isEmpty()) {
		System.out.println("file is empty");
		
	}else {
		post.setPostImgUrl(file.getOriginalFilename());
		File saveFile= new ClassPathResource("static/images").getFile();
		Path path =Paths.get(saveFile.getAbsolutePath()+File.separator+file.getOriginalFilename());
		Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
		System.out.println("Image is uploaded");
	}
	post.setUserName(user.getUserName());
	post.setUserImageUrl(user.getUserImageUrl());
	user.getPosts().add(post);
	post.setUser(user);
	
	
	this.userRepository.save(user);
	System.out.println(post);
	}catch(Exception e) {
	System.out.println("error " +e.getMessage());	
	e.printStackTrace();
	}
	return "postForm";
}

//public String openPostFrom() {
//	return "postFrom";
//}


//show user post in profile handler
@GetMapping("/show-post")
public String showPost(Model m,Principal principal) {
//	String userName= principal.getName();
//	User user = this.userRepository.getUserByUserName(userName);
//	user.getPosts();
	String userName = principal.getName();
	User user =this.userRepository.getUserByUserName(userName);
	List<Post>post=this.postRepository.findPostByUser(user.getUserId());
	m.addAttribute("post", post);
	
	
	List<User>user1=this.allUserRepository.findAll();
	//User user1 =this.allUserRepository.findAll();
	m.addAttribute("user1", user1);
	
	return "profile";
}

//@GetMapping("/show-post")
//public String allUser(Model m) {
//	User user1=(User) this.allUserRepository.findAll();
//	//User user1 =this.allUserRepository.findAll();
//	m.addAttribute("user1", user1);
//	
//	return "profile";
//	
//}

//delete post handler
@GetMapping("/delete/{postId}")
public String deletePost(@PathVariable("postId") Integer postId ) {
	
//Optional<Post>postOptional = this.postRepository.findById(postId);
//Post post=	postOptional.get();
Post post=this.postRepository.findById(postId).get();
post.setUser(null);
this.postRepository.delete(post);

return "redirect:/user/show-post";
}


//update post
@PostMapping("/update-post/{postId}")
public String updateForm() {
	
	return "updateForm";
	
}


//upload user details
@GetMapping("/profile/upload")	
public String userUpdate(Model model) {
	model.addAttribute("user", new User());
	return "profileUploadForm";	
	
}

@PostMapping("/profile/upload/processupload")
public String processPost(@ModelAttribute("user") User user, Model model, @RequestParam("img") MultipartFile file) {
	
	user.setAbout(user.getAbout());
	try {
	
	
	//processing and uploading file
	if(file.isEmpty()) {
		System.out.println("file is empty");
		
	}else {
		user.setUserImageUrl(file.getOriginalFilename());
		
		File saveFile= new ClassPathResource("static/images").getFile();
		Path path =Paths.get(saveFile.getAbsolutePath()+File.separator+file.getOriginalFilename());
		Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
		System.out.println("Image is uploaded");
	}
	
//	user.getPosts().add(post);
//	post.setUser(user);
	
	
	this.userRepository.save(user);
	
	}catch(Exception e) {
	System.out.println("error " +e.getMessage());	
	e.printStackTrace();
	}
	return "profileUploadForm";
}



//show all post in homepage
@GetMapping("/index")	
public String home(Model model, Principal principal, HttpServletRequest request ) {
	
	List<Post> post=this.allPostRepository.findAll();
	model.addAttribute("post", post);
	model.addAttribute("comment", new Comment());
	model.addAttribute("like", new Like());
	
	int a = 0;
	
	
	List<Comment>comment1=this.allCommentRepository.findAll();
	//User user1 =this.allUserRepository.findAll();
	model.addAttribute("comment1", comment1);
	
	List<Like>like=this.allLikeRepository.findAll();
	//User user1 =this.allUserRepository.findAll();

	List<LikeCount> lc = new ArrayList<LikeCount>();
	for (int i = 0; i < post.size(); i++) {
		int id = post.get(i).getPostId();
		int count = 0;
		for (int j = 0; j < like.size(); j++) {
			if(post.get(i).getPostId() == like.get(j).getPostId()) {
				count += 1;
			}
		}
		LikeCount l = new LikeCount();
		l.setId(id);
		l.setCount(count);
		lc.add(l);
	
	}
	
	
	model.addAttribute("like1", like);
	model.addAttribute("lc", lc);
	return "home";
}



@PostMapping("/index/post/processComment")
public String processComment(HttpServletRequest request, @ModelAttribute Comment comment, Principal principal, Model m) {
	
	System.out.println(".........................    "+request.getParameter("postId"));
	
	
	String pid=request.getParameter("postId");
	
	int pid1 = Integer.parseInt(pid);
	Post post1=this.postCommentRepository.getPostByPostId(pid1); 
	String name=principal.getName();
	User user =this.userRepository.getUserByUserName(name);
	comment.setCommentUser(user.getUserName());
	post1.getComments().add(comment);
	//comment.setPost(post1);
	this.postCommentRepository.save(post1);
	
	return "redirect:/user/index";

}

@PostMapping("/index/post/processLike")
public String processLike(HttpServletRequest request, @ModelAttribute Like like, Principal principal, Model m) {
	
	System.out.println("............from like   "+request.getParameter("postId"));
	
	
	
	
	
	String pid=request.getParameter("postId");
	int pid1 = Integer.parseInt(pid);
	Post post1=this.postCommentRepository.getPostByPostId(pid1);
	String name=principal.getName();
	User user =this.userRepository.getUserByUserName(name);
	like.setLikeUserName(user.getUserName());
	like.setLikeUserId(user.getUserId());
	int count= 1;
	like.setCountLike(count);
	post1.getLike().add(like);
	
	
	Like ll=likeCheckService.like(like.getPostId(), like.getLikeUserId());
	if(Objects.isNull(ll)){
		
		this.postCommentRepository.save(post1);
		System.out.println("aaaaaaaa");
	}
	
	
	
	return "redirect:/user/index";
}


@GetMapping("chatStart")	
public String chat(Model model) {
	//model.addAttribute("post", new Post());
	return "chatStart";	
	
}


@RequestMapping("/chat1")	
public String index(Model model) {
	
	return "index";	
	
}





//@GetMapping("/show-post")
//public String showPost(Model m,Principal principal) {
////	String userName= principal.getName();
////	User user = this.userRepository.getUserByUserName(userName);
////	user.getPosts();
//	String userName = principal.getName();
//	User user =this.userRepository.getUserByUserName(userName);
//	List<Post>post=this.postRepository.findPostByUser(user.getUserId());
//	m.addAttribute("post", post);
//	
//	
//	List<User>user1=this.allUserRepository.findAll();
//	//User user1 =this.allUserRepository.findAll();
//	m.addAttribute("user1", user1);
//	
//	return "profile";
//}


//@GetMapping("/index")	
//public String home(Model model, Principal principal ) {
//	
//	List<User>post=this.allPostRepository.findAll();
//	model.addAttribute("post", post);
//	return "home";
//}


//@GetMapping("/index/post/comment")	
//public String comment(Model model) {
//	model.addAttribute("comment", new Comment());
//	return "redirect:/user/index";	
//	
//}


}

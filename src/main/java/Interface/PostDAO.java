package Interface;

import java.util.List;

import bean.Post;

public interface PostDAO {

	boolean addPostImage(Post post);
	
	boolean deletePost(int id);
	
	public boolean updatePost(Post post);
	
	public Post findPost(int id);
	
	public List<Post> findallPost();
	
}

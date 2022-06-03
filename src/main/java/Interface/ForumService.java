package Interface;

import java.util.List;

import bean.ForumBean;

public interface ForumService {

	List<ForumBean> queryName();
	
	int save(ForumBean forumBean);

	List<ForumBean> getAllForums();

	int deleteForum(int vgeid);

	int updateForum(ForumBean forumBean);
	
	List<ForumBean> queryone(String vgename);
}

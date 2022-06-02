package Interface;
import java.util.List;

import bean.ForumBean;

public interface ForumDAO {


	int save(ForumBean forumBean);

	List<ForumBean> getAllForums();

	int deleteForum(int vgeid);

	int updateForum(ForumBean forumBean);

}
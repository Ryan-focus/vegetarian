package service;

import java.util.List;

import bean.ForumBean;

public interface ForumService {

		int save(ForumBean forumBean);

		List<ForumBean> getAllMembers();

		//MemberBean getMember(int pk);

		int deleteMember(int pk);

		int updateMember(ForumBean forumBean);
}

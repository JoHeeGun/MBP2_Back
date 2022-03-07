package kr.com.jo.repository;


import java.util.List;

import kr.com.jo.domain.BoardVO;
import kr.com.jo.domain.UserVO;

public interface UserMapper {
	
	public int loginUser(UserVO vo) throws Exception;
	
	public int checkUser(UserVO vo) throws Exception;
	
	public UserVO getUser(UserVO vo) throws Exception;
	
	public int signupUser(UserVO vo) throws Exception;
	
	public int updateUser(UserVO vo) throws Exception;
	
	public int deleteUser(UserVO vo) throws Exception;
	
	public List<UserVO> selectUserList(UserVO vo) throws Exception;
		
	public int selectUserListCount(UserVO vo) throws Exception;
	


}

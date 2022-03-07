package kr.com.jo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.com.jo.domain.UserVO;
import kr.com.jo.repository.UserMapper;

@Service
public class UserService {
	
	@Autowired
	UserMapper userMapper;

	
	public int loginUser(UserVO vo) throws Exception{
		return userMapper.loginUser(vo);
	}
	
	public int checkUser(UserVO vo) throws Exception{
		return userMapper.checkUser(vo);
	}
	
	public UserVO getUser(UserVO vo) throws Exception{
		return userMapper.getUser(vo);
	}
	
	public int signupUser(UserVO vo) throws Exception{
		return userMapper.signupUser(vo);
	}
	
	public int updateUser(UserVO vo) throws Exception{
		return userMapper.updateUser(vo);
	}
	
	public int deleteUser(UserVO vo) throws Exception{
		return userMapper.deleteUser(vo);
	}

	public List<UserVO> selectUserList(UserVO vo) throws Exception {
		return userMapper.selectUserList(vo);
	}

	public int selectUserListCount(UserVO vo) throws Exception {
		return userMapper.selectUserListCount(vo);
	}
	
}


package kr.com.jo.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.com.jo.domain.NoticeVO;
import kr.com.jo.domain.UserVO;
import kr.com.jo.service.UserService;

@CrossOrigin
@Controller
@EnableAutoConfiguration
@RequestMapping("/user")
public class UserController {
	

	  @Autowired
	  private UserService userService;
	  
	  @ResponseBody
	  @GetMapping("/hello")
		public String hello() {
			return "안녕하십니까 usercontroller입니다!";
		}
	  
	  
	  //로그인
	  @ResponseBody
	  @PostMapping(value = "/login")
	  public HashMap<String, Object> loginUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
	    String id = request.getParameter("id");
	    String password = request.getParameter("password");
	    		
		UserVO vo = new UserVO();
		vo.setId(id);
		vo.setPassword(password);
		
		
		UserVO data = userService.getUser(vo);
		int result = userService.loginUser(vo);
		int status = 0;
		
		if(result == 1) {
			status = 200;
		}else {
			status = 400;
		}
		
	    HashMap<String, Object> map = new HashMap<String, Object>();
	    map.put("status", status);
	    map.put("data", data);

	    return map;
	  }
	  
	  //아이디 중복체크
	  @ResponseBody
	  @GetMapping(value = "/checkUser")
	  public int checkUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
	    String id = request.getParameter("id");
	    
	    		
		UserVO vo = new UserVO();
		vo.setId(id);
				
		
		int result = userService.checkUser(vo);
		
		int status = 0;
		
		if(result == 1) {
			status = 400;
		}else {
			status = 200;
		}

	    return status;
	  }
	  
	  
	  //회원가입
	  @ResponseBody
	  @PostMapping(value = "/signup")
	  public String signupUser(HttpServletRequest request, HttpServletResponse response) throws Exception{
	    String id = request.getParameter("id");
	    String password = request.getParameter("password");
	    String email = request.getParameter("email");
	    
	    UserVO vo = new UserVO();
	    vo.setId(id);
	    vo.setPassword(password);
	    vo.setEmail(email);
	    
	    int result = userService.signupUser(vo);
	       
	    String data = "";
	    
	    if(result>0) {
	    	data = vo.getId();
	    } 
	    return data;
	  }
	  
	  //회원 수정
	  @ResponseBody
	  @PostMapping(value = "/update")
	  public int updateUser(HttpServletRequest request, HttpServletResponse response) throws Exception{
	    String id = request.getParameter("id");
	    String password = request.getParameter("password");
	    String email = request.getParameter("email");

	    UserVO vo = new UserVO();
	    vo.setId(id);
	    vo.setPassword(password);
	    vo.setEmail(email);

	    int result = userService.updateUser(vo);
		int status = 0;
		
		if(result == 1) {
			status = 200;
		}else {
			status = 400;
		}
		
		return status;
	  }
	  
	  //회원 탈퇴
	  @ResponseBody
	  @PostMapping(value = "/delete")
	  public int deleteUser(HttpServletRequest request, HttpServletResponse response) throws Exception{
	    String id = request.getParameter("id");


	    UserVO vo = new UserVO();
	    vo.setId(id);


	    int result = userService.deleteUser(vo);
	    
		int status = 0;
		
		if(result == 1) {
			status = 200;
		}else {
			status = 400;
		}
		
		return status;
	  }	    
	  
	  //회원 목록
	  @ResponseBody
	  @GetMapping(value="/list")
	  public HashMap<String, Object> selectUserList(HttpServletRequest request, HttpServletResponse response) throws Exception {
	    String schVal = request.getParameter("schVal");
	    int rows = Integer.parseInt(request.getParameter("rows"));
	    int page = Integer.parseInt(request.getParameter("page"));


	    UserVO vo = new UserVO();
	    vo.setSchVal(schVal);
	    vo.setStartNo(((page * rows) - rows) + 1);
	    vo.setEndNo(page * rows);

	    List<UserVO> result = userService.selectUserList(vo);

	    int total = 0;

	    if(result.size() > 0) {
	      total = userService.selectUserListCount(vo);
	      
	    }

	    HashMap<String, Object> map = new HashMap<String, Object>();
	    map.put("data", result);
	    map.put("total", total);

	    return map;
	  }
	  
	  
	  //회원 정보
	  @ResponseBody
	  @GetMapping(value = "/detail")
	  public UserVO selectNoticeDetail(HttpServletRequest request, HttpServletResponse response) throws Exception {
	    String id = request.getParameter("id");

	    UserVO vo = new UserVO();
	    vo.setId(id);



	    UserVO result = userService.getUser(vo);

	    return result;
	  }
	  
}

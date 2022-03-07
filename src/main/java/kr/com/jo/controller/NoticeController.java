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

import kr.com.jo.service.NoticeService;

@CrossOrigin
@Controller
@EnableAutoConfiguration
@RequestMapping("/notice")
public class NoticeController {
	
	  @Autowired
	  private NoticeService noticeService;
	  

	 @ResponseBody
	 @GetMapping("/test")
		public String hello() {
			return "안녕하십니까 noticecontroller 테스트입니다!";
		}
	 
	  //공지사항 목록 조회
	  @ResponseBody
	  @GetMapping(value="/list")
	  public HashMap<String, Object> selectNoticeList(HttpServletRequest request, HttpServletResponse response) throws Exception {
	    String schVal = request.getParameter("schVal");
	    int rows = Integer.parseInt(request.getParameter("rows"));
	    int page = Integer.parseInt(request.getParameter("page"));


	    NoticeVO vo = new NoticeVO();
	    vo.setSchVal(schVal);
	    vo.setStartNo(((page * rows) - rows) + 1);
	    vo.setEndNo(page * rows);

	    List<NoticeVO> result = noticeService.selectNoticeList(vo);

	    int total = 0;

	    if(result.size() > 0) {
	      total = noticeService.selectNoticeListCount(vo);
	    }

	    HashMap<String, Object> map = new HashMap<String, Object>();
	    map.put("data", result);
	    map.put("total", total);

	    return map;
	  }
	  
	  
	  //공지사항 상세 조회
	  @ResponseBody
	  @GetMapping(value = "/detail")
	  public NoticeVO selectNoticeDetail(HttpServletRequest request, HttpServletResponse response) throws Exception {
	    String notice_num = request.getParameter("notice_num");

	    NoticeVO vo = new NoticeVO();
	    vo.setNotice_num(Integer.parseInt(notice_num));

	    // 조회수 증가
	    noticeService.increaseViewCount(vo);

	    NoticeVO result = noticeService.selectNoticeDetail(vo);

	    return result;
	  }
	  
	  // 공지사항 작성 
	  @ResponseBody
	  @PostMapping(value = "/insert")
	  public int insertNotice(HttpServletRequest request, HttpServletResponse response) throws Exception{
	    String title = request.getParameter("title");
	    String id = request.getParameter("id");
	    String content = request.getParameter("content");
	    
  		     
	    
	    NoticeVO vo = new NoticeVO();
	    vo.setTitle(title);
	    vo.setContent(content);
	    vo.setId(id);

	    int result = 0; 
	    
	    int rows = noticeService.insertNotice(vo);

	    if(rows > 0) {
	      result = vo.getNotice_num();
	    }

	    return result;
	  }
	  
	  //공지사항 수정
	  @ResponseBody
	  @PostMapping(value = "/update")
	  public int updateNotice(HttpServletRequest request, HttpServletResponse response) throws Exception{
	    String notice_num = request.getParameter("notice_num");
	    String title = request.getParameter("title");
	    String content = request.getParameter("content");

	    NoticeVO vo = new NoticeVO();
	    vo.setNotice_num(Integer.parseInt(notice_num));
	    vo.setTitle(title);
	    vo.setContent(content);

	    int rows = noticeService.updateNotice(vo);

	    return rows;
	  }
	  
	  
	  //공지사항 삭제
	  @ResponseBody
	  @PostMapping(value = "/delete")
	  public int deleteNotice(HttpServletRequest request, HttpServletResponse response) throws Exception{
	    String notice_num = request.getParameter("notice_num");

	    NoticeVO vo = new NoticeVO();
	    vo.setNotice_num(Integer.parseInt(notice_num));


	    int rows = noticeService.deleteNotice(vo);

	    return rows;
	  }
}

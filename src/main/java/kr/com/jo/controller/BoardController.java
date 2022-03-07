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

import kr.com.jo.domain.BoardVO;
import kr.com.jo.domain.ReplyVO;
import kr.com.jo.service.BoardService;

@CrossOrigin
@Controller
@EnableAutoConfiguration
@RequestMapping("/board")
public class BoardController {
	
	  @Autowired
	  private BoardService boardService;
	  
	  
	  @ResponseBody
	  @GetMapping("/hello")
		public String hello() {
			return "안녕하십니까 테스트입니다!";
		}
	  
	  //게시글 조회
	  @ResponseBody
	  @GetMapping(value="/list")
	  public HashMap<String, Object> selectBoardList(HttpServletRequest request, HttpServletResponse response) throws Exception {
	    String schType = request.getParameter("schType");
	    String schVal = request.getParameter("schVal");
	    int rows = Integer.parseInt(request.getParameter("rows"));
	    int page = Integer.parseInt(request.getParameter("page"));


	    BoardVO vo = new BoardVO();
	    vo.setSchType(schType);
	    vo.setSchVal(schVal);
	    vo.setStartNo(((page * rows) - rows) + 1);
	    vo.setEndNo(page * rows);

	    List<BoardVO> result = boardService.selectBoardList(vo);

	    int total = 0;

	    if(result.size() > 0) {
	      total = boardService.selectBoardListCount(vo);
	    }

	    HashMap<String, Object> map = new HashMap<String, Object>();
	    map.put("data", result);
	    map.put("total", total);

	    return map;
	  }
	  
	  //게시글 상세 조회
	  @ResponseBody
	  @GetMapping(value = "/detail")
	  public BoardVO selectBoardDetail(HttpServletRequest request, HttpServletResponse response) throws Exception {
	    String board_num = request.getParameter("board_num");

	    BoardVO vo = new BoardVO();
	    vo.setBoard_num(Integer.parseInt(board_num));

	    // 조회수 증가
	    boardService.increaseViewCount(vo);

	    BoardVO result = boardService.selectBoardDetail(vo);

	    return result;
	  }
	  
	  //게시글 작성
	  @ResponseBody
	  @PostMapping(value = "/insert")
	  public int insertBoard(HttpServletRequest request, HttpServletResponse response) throws Exception{
	    String title = request.getParameter("title");
	    String content = request.getParameter("content");
	    String id = request.getParameter("id");
	    
	    BoardVO vo = new BoardVO();
	    vo.setTitle(title);
	    vo.setContent(content);
	    vo.setId(id);

	    int result = 0; // 신규 게시글 번호
	    
	    int rows = boardService.insertBoard(vo);

	    if(rows > 0) {
	      result = vo.getBoard_num();
	    }

	    return result;
	  }
	  
	  //게시글 수정
	  @ResponseBody
	  @PostMapping(value = "/update")
	  public int updateBoard(HttpServletRequest request, HttpServletResponse response) throws Exception{
	    String board_num = request.getParameter("board_num");
	    String title = request.getParameter("title");
	    String content = request.getParameter("content");

	    BoardVO vo = new BoardVO();
	    vo.setBoard_num(Integer.parseInt(board_num));
	    vo.setTitle(title);
	    vo.setContent(content);

	    int rows = boardService.updateBoard(vo);

	    return rows;
	  }
	  
	  //게시글 삭제
	  @ResponseBody
	  @PostMapping(value = "/delete")
	  public int deleteBoard(HttpServletRequest request, HttpServletResponse response) throws Exception{
	    String board_num = request.getParameter("board_num");

	    BoardVO vo = new BoardVO();
	    vo.setBoard_num(Integer.parseInt(board_num));

	    // 댓글 유무 확인 후 같이 삭제
	    ReplyVO replyVO = new ReplyVO();
	    replyVO.setBoard_num(Integer.parseInt(board_num));

	    List<ReplyVO> result = boardService.selectReplyList(replyVO);

	    if(result.size() > 0) {
	      boardService.deleteReply(replyVO);
	    }

	    int rows = boardService.deleteBoard(vo);

	    return rows;
	  }
	  
	  //댓글 조회
	  @ResponseBody
	  @GetMapping(value = "/reply/list")
	  public HashMap<String, Object> selectReplyList(HttpServletRequest request, HttpServletResponse response) throws Exception{
	    String board_num = request.getParameter("board_num");

	    ReplyVO vo = new ReplyVO();
	    vo.setBoard_num(Integer.parseInt(board_num));

	    List<ReplyVO> result = boardService.selectReplyList(vo);
	    
	    int total = 0;

	    if(result.size() > 0) {
	      total = boardService.selectReplyListCount(vo);
	    }
	    
	    HashMap<String, Object> map = new HashMap<String, Object>();
	    map.put("data", result);
	    map.put("total", total);

	    return map;
	    
	  }
	  
	  //댓글 작성
	  @ResponseBody
	  @PostMapping(value = "/reply/insert")
	  public int insertReply(HttpServletRequest request, HttpServletResponse response) throws Exception{
	    String board_num = request.getParameter("board_num");
	    String content = request.getParameter("content");
	    String id = request.getParameter("id");

	    ReplyVO vo = new ReplyVO();
	    vo.setBoard_num(Integer.parseInt(board_num));
	    vo.setId(id);
	    vo.setContent(content);

	    int rows = boardService.insertReply(vo);

	    return rows;
	  }
	  
	  //댓글 수정
	  @ResponseBody
	  @PostMapping(value = "/reply/update")
	  public int updateReply(HttpServletRequest request, HttpServletResponse response) throws Exception{
	    String reply_num = request.getParameter("reply_num");
	    String board_num = request.getParameter("board_num");
	    String content = request.getParameter("content");

	    ReplyVO vo = new ReplyVO();
	    vo.setReply_num(Integer.parseInt(reply_num));
	    vo.setBoard_num(Integer.parseInt(board_num));
	    vo.setContent(content);

	    int rows = boardService.updateReply(vo);

	    return rows;
	  }
	  
	  
	  
	  //댓글 삭제
	  @ResponseBody
	  @PostMapping(value = "/reply/delete")
	  public int deleteReply(HttpServletRequest request, HttpServletResponse response) throws Exception{
	    String reply_num = request.getParameter("reply_num");
	    String board_num = request.getParameter("board_num");

	    ReplyVO vo = new ReplyVO();
	    vo.setReply_num(Integer.parseInt(reply_num));
	    vo.setBoard_num(Integer.parseInt(board_num));

	    int rows = boardService.deleteReply(vo);

	    return rows;
	  }
	  
	  
	  
	  //유저 게시글 목록 조회
	  @ResponseBody
	  @GetMapping(value="/mylist")
	  public HashMap<String, Object> selectmyBoardList(HttpServletRequest request, HttpServletResponse response) throws Exception {
	    String schVal = request.getParameter("schVal");
	    String id = request.getParameter("id");
	    int rows = Integer.parseInt(request.getParameter("rows"));
	    int page = Integer.parseInt(request.getParameter("page"));


	    BoardVO vo = new BoardVO();
	    vo.setId(id);
	    vo.setSchVal(schVal);
	    vo.setStartNo(((page * rows) - rows) + 1);
	    vo.setEndNo(page * rows);

	    List<BoardVO> result = boardService.selectmyBoardList(vo);

	    int total = 0;

	    if(result.size() > 0) {
	      total = boardService.selectmyBoardListCount(vo);
	    }

	    HashMap<String, Object> map = new HashMap<String, Object>();
	    map.put("data", result);
	    map.put("total", total);

	    return map;
	  }
}

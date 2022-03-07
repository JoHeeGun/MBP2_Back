package kr.com.jo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.com.jo.domain.BoardVO;
import kr.com.jo.domain.ReplyVO;
import kr.com.jo.repository.BoardMapper;



@Service
public class BoardService {
	

	@Autowired
	BoardMapper boardMapper;
	
	public List<BoardVO> selectBoardList(BoardVO vo) throws Exception {
		return boardMapper.selectBoardList(vo);
	}

	public int selectBoardListCount(BoardVO vo) throws Exception {
		return boardMapper.selectBoardListCount(vo);
	}
	public BoardVO selectBoardDetail(BoardVO vo) throws Exception{
		return boardMapper.selectBoardDetail(vo);
	}
	public void increaseViewCount(BoardVO vo) throws Exception {
		boardMapper.increaseViewCount(vo);
	}
	
	public int updateBoard(BoardVO vo) throws Exception{
		return boardMapper.updateBoard(vo);
	}
	
	public int insertBoard(BoardVO vo) throws Exception {
		return boardMapper.insertBoard(vo);
	}
	
	public int deleteBoard(BoardVO vo) throws Exception {
		return boardMapper.deleteBoard(vo);
	}
	
	public List<ReplyVO> selectReplyList(ReplyVO vo) throws Exception {
		return boardMapper.selectReplyList(vo);
	}
	
	public int selectReplyListCount(ReplyVO vo) throws Exception {
		return boardMapper.selectReplyListCount(vo);
	}
	
	public int insertReply(ReplyVO vo) throws Exception {
		return boardMapper.insertReply(vo);
	}
	
	public int updateReply(ReplyVO vo) throws Exception {
		return boardMapper.updateReply(vo);
	}
	
	public int deleteReply(ReplyVO vo) throws Exception {
		return boardMapper.deleteReply(vo);
	}
	
	public List<BoardVO> selectmyBoardList(BoardVO vo) throws Exception {
		return boardMapper.selectmyBoardList(vo);
	}
	
	public int selectmyBoardListCount(BoardVO vo) throws Exception {
		return boardMapper.selectmyBoardListCount(vo);
	}
	

}

package kr.com.jo.repository;

import java.util.List;

import kr.com.jo.domain.BoardVO;
import kr.com.jo.domain.NoticeVO;



public interface NoticeMapper {
  
	  public List<NoticeVO> selectNoticeList(NoticeVO vo) throws Exception;
	  
	  public int selectNoticeListCount(NoticeVO vo) throws Exception;
	  
	  public NoticeVO selectNoticeDetail(NoticeVO vo) throws Exception;
	  
	  public void increaseViewCount(NoticeVO vo) throws Exception;
	  
	  public int insertNotice(NoticeVO vo) throws Exception;
	  
	  public int updateNotice(NoticeVO vo) throws Exception;
	  
	  public int deleteNotice(NoticeVO vo) throws Exception;
	  
}

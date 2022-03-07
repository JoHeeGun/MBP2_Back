package kr.com.jo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.com.jo.domain.BoardVO;
import kr.com.jo.domain.NoticeVO;
import kr.com.jo.repository.NoticeMapper;



@Service
public class NoticeService {
	

	@Autowired
	NoticeMapper noticeMapper;
	
	public List<NoticeVO> selectNoticeList(NoticeVO vo) throws Exception {
		return noticeMapper.selectNoticeList(vo);
	}	
	
	public int selectNoticeListCount(NoticeVO vo) throws Exception {
		return noticeMapper.selectNoticeListCount(vo);
	}
	
	public NoticeVO selectNoticeDetail(NoticeVO vo) throws Exception{
		return noticeMapper.selectNoticeDetail(vo);
	}
	
	public void increaseViewCount(NoticeVO vo) throws Exception {
		noticeMapper.increaseViewCount(vo);
	}
	
	public int insertNotice(NoticeVO vo) throws Exception {
		return noticeMapper.insertNotice(vo);
	}
	
	public int updateNotice(NoticeVO vo) throws Exception{
		return noticeMapper.updateNotice(vo);
	}
	
	public int deleteNotice(NoticeVO vo) throws Exception {
		return noticeMapper.deleteNotice(vo);
	}
}

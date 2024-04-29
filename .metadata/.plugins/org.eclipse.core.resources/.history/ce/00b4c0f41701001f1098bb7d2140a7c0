package com.smhrd.db;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import com.smhrd.model.BoardVO;
import com.smhrd.model.SearchCriteria;

// mybatis f/w 사용할 때 interface 형식을 사용

public interface BoardMapper {

	// 전체 게시글을 조회해서 결과값을 반환해주는 메소드
	public List<BoardVO> boardList();
		
	// 게시글 등록 메소드
	public void boardRegister(BoardVO vo);
	
	
	// 게시글 한개 조회 메소드
	@Select("SELECT * FROM BOARD WHERE IDX = #{idx}")
	public BoardVO boardContent(int idx);
	
	// 게시글 삭제 베소드
	@Delete("delete from board where idx = #{idx}")
	public void delete(int idx);
	
	// 게시글 수정 메소드
	public void edit(BoardVO vo);
	
	// 게시글 검색 메소드
	public List<BoardVO> search(SearchCriteria criteria);

	// 자동완성 메소드
	public List<BoardVO> autocomplete(SearchCriteria criteria);
	
}


package bbs;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import memo.MemoDTO;

public class BbsTest {

	public static void main(String[] args) {
		BbsDAO dao = new BbsDAO();
		
		//create(dao);
		//read(dao);
		//update(dao);
		//delete(dao);
		//list(dao);

	}

	private static void list(BbsDAO dao) {
		Map map = new HashMap();
		map.put("col", "wname");
		map.put("word", "홍");
		map.put("sno", 1);
		map.put("eno", 10);
		List < BbsDTO > list = dao.list(map);
		
		for ( int i = 0 ; i < list.size() ; i++) {
		      BbsDTO dto = list.get(i);
		      p(dto);
		      p("===========================");
		      
		    }
	}

	private static void delete(BbsDAO dao) {
		if(dao.delete(2)) {
			p("삭제 성공");
		} else {
			p("삭제 실패");
		}
		
	}

	private static void update(BbsDAO dao) {
		BbsDTO dto = dao.read(1);
		dto.setWname("이름테스트");//varchar2(20 byte)
		dto.setTitle("제목 수정 테스트");
		dto.setContent("내용 수정 테스트");
		
		
		if(dao.update(dto)) {
			p("수정 성공");
		} else {
			p("수정 실패");
		}
		
	}

	private static void read(BbsDAO dao) {
		BbsDTO dto = dao.read(3);
		p(dto);
		
	}

	private static void p(BbsDTO dto) {
		System.out.println("글 일련 번호: " + dto.getBbsno());
		System.out.println("글쓴이: " + dto.getWname());
		System.out.println("제목: " + dto.getTitle());
	    System.out.println("글 내용: " + dto.getContent());
	    System.out.println("비밀 번호: " + dto.getPasswd());
	    System.out.println("조회수: " + dto.getViewcnt());
	    System.out.println("등록 날짜: " + dto.getWdate());
	    System.out.println("부모글 번호: " + dto.getGrpno());
	    System.out.println("답변 여부: " + dto.getIndent());
	    System.out.println("답변 순서: " + dto.getAnsnum());
	    
	}

	private static void create(BbsDAO dao) {
		BbsDTO dto = new BbsDTO();
		dto.setWname("홍길동");
		dto.setTitle("제목");
		dto.setContent("내용");
		dto.setPasswd("1234");
		if(dao.create(dto)) {
			p("성공");
		} else {
			p("실패");
		}
		
	}

	private static void p(String string) {
		System.out.println(string);
		
	}

}

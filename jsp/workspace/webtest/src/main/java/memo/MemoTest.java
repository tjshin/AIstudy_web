package memo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemoTest {

	public static void main(String[] args) {
		MemoDAO dao = new MemoDAO();
		
//		create(dao);
//		read(dao);
//		update(dao);
//		delete(dao);
		list(dao);

	}

	private static void list(MemoDAO dao) {
		Map map = new HashMap();
		map.put("col", "wname");
		map.put("word", "홍");
		map.put("sno", 1);
		map.put("eno", 10);
		List < MemoDTO > list = dao.list(map);
		
		for ( int i = 0 ; i < list.size() ; i++) {
		      MemoDTO dto = list.get(i);
		      p(dto);
		      p("===========================");
		      
		    }
	}

	private static void delete(MemoDAO dao) {
		if(dao.delete(2)) {
			p("삭제 성공");
		} else {
			p("삭제 실패");
		}
		
	}

	private static void update(MemoDAO dao) {
		MemoDTO dto = dao.read(1);
		dto.setWname("이름테스트");
		dto.setTitle("제목 수정 테스트");
		dto.setContent("내용 수정 테스트");
		
		
		if(dao.update(dto)) {
			p("수정 성공");
		} else {
			p("수정 실패");
		}
		
	}

	private static void read(MemoDAO dao) {
		MemoDTO dto = dao.read(10);
		p(dto);
		
		
	}

	private static void create(MemoDAO dao) {
		MemoDTO dto = new MemoDTO();
		dto.setWname("홍길동");
		dto.setTitle("테스트");
		dto.setContent("내용 테스트");
		dto.setPasswd("1234");
		
		if(dao.create(dto)) {
			p("성공");
		} else {
			p("실패");
		}
		
	}

	private static void p(MemoDTO dto) {
		p("번호 : " + dto.getMemono());
		p("이름 : " + dto.getWname());
		p("제목 : " + dto.getTitle());
		p("내용 : " + dto.getContent());
		p("비밀 번호 : " + dto.getPasswd());
		p("조회수 : " + dto.getViewcnt());
		p("등록일 : " + dto.getWdate());
		
	}
	
	private static void p(String string) {
		System.out.println(string);
		
	}

}

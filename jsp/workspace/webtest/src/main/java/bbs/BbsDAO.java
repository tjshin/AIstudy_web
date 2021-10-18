package bbs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import utility.DBClose;
import utility.Open;

public class BbsDAO {
	//답변 생성
	public boolean createReply(BbsDTO dto) {
		boolean flag = false;
		Connection con = Open.getConnection();
		PreparedStatement pstmt = null;
		StringBuffer sql = new StringBuffer();
		sql.append(" insert into bbs(bbsno, wname, title, content, passwd, wdate, grpno, ");
		sql.append(" indent, ansnum) ");
		sql.append(" values((select nvl(max(bbsno),0)+1 as bbsno from bbs), ");
		sql.append(" ?, ?, ?, ?, sysdate, ?, ?, ?) ");
		
		try {
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, dto.getWname());
			pstmt.setString(2, dto.getTitle());
			pstmt.setString(3, dto.getContent());
			pstmt.setString(4, dto.getPasswd());
			pstmt.setInt(5, dto.getGrpno()); // 부모의 grpno
			pstmt.setInt(6, dto.getIndent() + 1); // 부모에 대해서 +1
			pstmt.setInt(7, dto.getAnsnum() + 1); // 부모에 대해서 +1
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt>0) flag = true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBClose.close(pstmt, con);
		}
		
		return flag;
	}
	
	//답변에서 ansnum 증가
	public void upAnsnum(Map map) {
		Connection con = Open.getConnection();
		PreparedStatement pstmt = null;
		int ansnum = (Integer)map.get("ansnum");
		int grpno = (Integer)map.get("grpno");
		
		StringBuffer sql = new StringBuffer();
		
		sql.append(" update bbs  ");
		sql.append(" set ansnum = ansnum + 1  ");
		sql.append(" where grpno = ? and ansnum > ? ");
		
		try {
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, grpno);
			pstmt.setInt(2, ansnum);
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBClose.close(pstmt, con);
		}
		
	}
	
	//답변 보기
	public BbsDTO readReply(int bbsno) {
		BbsDTO dto = null;
		Connection con = Open.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		sql.append(" select bbsno, title, grpno, indent, ansnum ");
		sql.append(" from bbs ");
		sql.append(" where bbsno = ? ");
		
		try {
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, bbsno);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto = new BbsDTO();
				dto.setBbsno(rs.getInt("bbsno"));
				dto.setTitle(rs.getString("title"));
				dto.setGrpno(rs.getInt("grpno"));
				dto.setIndent(rs.getInt("indent"));
				dto.setAnsnum(rs.getInt("ansnum"));
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBClose.close(rs, pstmt, con);
		}
		return dto;
	}
	
	//페이징 컴퍼넌트
	public int total(String col, String word) {
		int total = 0;
		Connection con = Open.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		
		sql.append(" select count(*) from bbs ");
		
		if(word.trim().length() > 0 && col.equals("title_content")) {
			sql.append(" where title like '%'||?||'%' ");
			sql.append(" or content like '%'||?||'%' ");
		} else if (word.trim().length() > 0) {
			sql.append(" where " + col + " like '%'||?||'%' ");
		}
		
		try {
			pstmt = con.prepareStatement(sql.toString());
			
			if(word.trim().length() > 0 && col.equals("title_content")) {
				pstmt.setString(1, word);
				pstmt.setString(2, word);
			} else if (word.trim().length() > 0) {
				pstmt.setString(1, word);
			}
			
			rs = pstmt.executeQuery();
			
			rs.next();
			total = rs.getInt(1);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBClose.close(rs, pstmt, con);
		}
		
		return total;
	}
	
	//목록
	public List < BbsDTO > list(Map map) {
		List < BbsDTO > list = new ArrayList< BbsDTO >();
		Connection con = Open.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String col = (String)map.get("col");//wname, title, content, title_content
		String word = (String)map.get("word");//col이 total 이면 word == 0
		int sno = (Integer)map.get("sno"); //페이징 시작번호
		int eno = (Integer)map.get("eno"); //페이징 끝번호
		
		StringBuffer sql = new StringBuffer();
		
		sql.append(" select bbsno, wname, title, viewcnt, ");
		sql.append(" to_char(wdate, 'yyyy-mm-dd') wdate, ");
		sql.append(" grpno, indent, ansnum, r ");
		sql.append(" from( "); //2중 서브쿼리
		sql.append(" select bbsno, wname, title, viewcnt, wdate, grpno, indent, ansnum, rownum r ");
		sql.append(" from( "); //3중 서브쿼리
		sql.append(" select bbsno, wname, title, viewcnt, wdate, grpno, indent, ansnum ");
		sql.append(" from bbs ");
		//검색 기능 추가 if문
		if(word.trim().length() > 0 && col.equals("title_content")) {
			sql.append(" where title like '%'||?||'%' ");
			sql.append(" or content like '%'||?||'%' ");
		} else if (word.trim().length() > 0) {
			sql.append(" where " + col + " like '%'||?||'%' ");
		}
		
		sql.append(" order by grpno desc, ansnum ");
		sql.append(" ) "); //3중 서브쿼리
		sql.append(" ) "); //2중 서브쿼리
		sql.append(" where r >= ? and r <= ? ");		
		
		try {
			pstmt = con.prepareStatement(sql.toString());
			
			//페이징 인덱스 오류 방지를 위한 변수 처리
			int i = 0;
			
			//검색 기능 추가 if문
			if(word.trim().length() > 0 && col.equals("title_content")) {
				pstmt.setString(++i, word); //i=1
				pstmt.setString(++i, word); //i=2
			} else if (word.trim().length() > 0) {
				pstmt.setString(++i, word); //i=1
			}
			
			pstmt.setInt(++i, sno);
			pstmt.setInt(++i, eno);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BbsDTO dto = new BbsDTO();
				dto.setBbsno(rs.getInt("bbsno"));
				dto.setWname(rs.getString("wname"));
				dto.setTitle(rs.getString("title"));
				dto.setGrpno(rs.getInt("grpno"));
				dto.setIndent(rs.getInt("indent"));
				dto.setAnsnum(rs.getInt("ansnum"));
				dto.setViewcnt(rs.getInt("viewcnt"));
				dto.setWdate(rs.getString("wdate"));
				
				list.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBClose.close(rs, pstmt, con);
		}
		
		return list;
	}
	
	//비번 검증
	public boolean passCheck(Map map) {
		boolean flag = false;
		Connection con = Open.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		
		sql.append(" select count(bbsno) as cnt ");
		sql.append(" from bbs ");
		sql.append(" where bbsno = ? ");
		sql.append(" and passwd = ? ");
		
		int bbsno = (Integer)map.get("bbsno");
		String passwd = (String)map.get("passwd");
		
		try {
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, bbsno);
			pstmt.setString(2, passwd);
			
			rs = pstmt.executeQuery();
			
			rs.next();
			
			int cnt = rs.getInt("cnt");
			
			if(cnt>0) flag = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBClose.close(rs, pstmt, con);
		}
		
		return flag;
	}
	
	//조회수 증가
	public void upViewcnt(int bbsno) {
		Connection con = Open.getConnection();
		PreparedStatement pstmt = null;
		StringBuffer sql = new StringBuffer();
		
		sql.append(" update bbs ");
		sql.append(" set viewcnt = viewcnt + 1 ");
		sql.append(" where bbsno = ? ");
		
		try {
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1,bbsno);
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBClose.close(pstmt, con);
		}
	}
	
	//create
	public boolean create(BbsDTO dto) {
		boolean flag = false;
		Connection con = Open.getConnection();
		PreparedStatement pstmt = null;
		StringBuffer sql = new StringBuffer();
		sql.append(" insert into bbs(bbsno, wname, title, content, passwd, wdate, grpno) ");
		sql.append(" values((select nvl(max(bbsno),0)+1 as bbsno from bbs), ");
		sql.append(" ?, ?, ?, ?, sysdate, ");
		//grpno가 증가하게 만드는 sql
		sql.append(" (select nvl(max(grpno), 0) + 1 as grpno from bbs) ");
		sql.append(" ) ");//values 쿼리문이 끝나는 부분
		
		try {
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, dto.getWname());
			pstmt.setString(2, dto.getTitle());
			pstmt.setString(3, dto.getContent());
			pstmt.setString(4, dto.getPasswd());
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt>0) flag = true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBClose.close(pstmt, con);
		}
		
		return flag;
	}
	
	//read
	public BbsDTO read(int bbsno) {
		BbsDTO dto = null;
		Connection con = Open.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		sql.append(" select bbsno, wname, title, content, viewcnt, ");
		sql.append(" to_char(wdate, 'yyyy-mm-dd') wdate ");
		sql.append(" from bbs ");
		sql.append(" where bbsno = ? ");
		
		try {
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, bbsno);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto = new BbsDTO();
				dto.setBbsno(rs.getInt("bbsno"));
				dto.setWname(rs.getString("wname"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setViewcnt(rs.getInt("viewcnt"));
				dto.setWdate(rs.getString("wdate"));
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBClose.close(rs, pstmt, con);
		}
		return dto;
		
	}
	
	//update
	public boolean update(BbsDTO dto) {
		boolean flag = false;
		Connection con = Open.getConnection();
		PreparedStatement pstmt = null;
		StringBuffer sql = new StringBuffer();
		
		sql.append(" update bbs ");
		sql.append(" set wname = ?, ");
		sql.append("     title = ?, ");
		sql.append("     content = ? ");
		sql.append(" where bbsno = ? ");
		
		try {
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, dto.getWname());
			pstmt.setString(2, dto.getTitle());
			pstmt.setString(3, dto.getContent());
			pstmt.setInt(4, dto.getBbsno());
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt>0) flag = true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBClose.close(pstmt, con);
		}
		return flag;
	}
	
	//delete
	public boolean delete(int bbsno) {
		boolean flag = false;
		Connection con = Open.getConnection();
		PreparedStatement pstmt = null;
		StringBuffer sql = new StringBuffer();
		
		sql.append(" delete from bbs where bbsno = ? ");
		
		try {
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, bbsno);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt>0) flag = true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBClose.close(pstmt, con);
		}
		return flag;
	}
}



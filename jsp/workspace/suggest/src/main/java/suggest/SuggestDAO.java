package suggest;
 
import java.sql.*;
import utility.*;
import java.util.*;
 
public class SuggestDAO {
    public int getCount(String keyword) {
        int count = 0;
        Connection con = Open.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        StringBuffer sql = new StringBuffer();
        
        sql.append(" SELECT COUNT(sqlquery)");
        sql.append(" FROM suggest");
        sql.append(" WHERE sqlquery LIKE '"+keyword+"%'");
         
 
        try {
            pstmt = con.prepareStatement(sql.toString());
            rs = pstmt.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1); // 수량 산출
            }
        } catch (Exception e) {
         e.printStackTrace();
        } finally {
            DBClose.close(rs, pstmt, con);
        }
        return count;
    }
 
    public List<String> getSqlquery(String keyword) {
        List<String> list = new ArrayList<String>();
        Connection con = Open.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        StringBuffer sql = new StringBuffer();
 
        sql.append(" SELECT sqlquery");
        sql.append(" FROM suggest");
        
        sql.append(" WHERE sqlquery LIKE '"+keyword+"%'");
              
        sql.append(" ORDER BY sqlquery ASC");
 
        try {
            pstmt = con.prepareStatement(sql.toString());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                String str = rs.getString(1);
                list.add(str);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBClose.close(rs, pstmt, con);
        }
        return list;
    }
}
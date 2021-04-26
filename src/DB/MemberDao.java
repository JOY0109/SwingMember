package DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import DB.DBConn;
import DB.SqlParameter;

public class MemberDao {
	
	public MemberDao() {}
	
	//회원정보 입력 
	public void insertMember(SqlParameter sqlP) {
		Connection conn=null;
		PreparedStatement pst = null;
		String sql="";
		conn=DBConn.getConnDB();
		
		try {
			sql ="INSERT INTO MEMBERS (USERID  ,USERPW,USERNAME,PHONE ,GENDER,BIRTH,COMMETS)"; 
			sql+="VALUES             (?,?,?,?,?,?,?)";
			
			pst=conn.prepareStatement(sql);
			pst.setString(1, sqlP.getUserid());
			pst.setString(2, sqlP.getUserpw());
			pst.setString(3, sqlP.getUsername());
			pst.setString(4, sqlP.getPhone());
			pst.setString(5, sqlP.getGender());
			pst.setString(6, sqlP.getBirth());
			pst.setString(7, sqlP.getCommets());
			

			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(pst!=null)pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	//회원정보 수정 
	public boolean updateMem(SqlParameter sp) {
		boolean ok=true;
		Connection conn=null;
		PreparedStatement pst = null;
		String sql="";
		conn=DBConn.getConnDB();
		
		try {
			
			sql ="UPDATE MEMBERS";
			sql+=" SET USERPW=?, USERNAME=?,PHONE =?,GENDER=?,BIRTH=?";
			sql+=" WHERE USERID=?";
			
			pst=conn.prepareStatement(sql);
			pst.setString(1, sp.getUserpw());
			pst.setString(2, sp.getUsername());
			pst.setString(3, sp.getPhone());
			pst.setString(4, sp.getGender());
			pst.setString(5, sp.getBirth());
			pst.setString(6, sp.getUserid());
			
			
			
			pst.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			try {
				if(pst!=null)pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return ok;
		
	}
	
	//회원탈퇴 
	public void deletMem(SqlParameter sp) {
		Connection conn=null;
		PreparedStatement pst=null;
		String sql="";
		conn=DBConn.getConnDB();
		
		try {
			sql ="DELETE FROM MEMBERS";
			sql+=" WHERE USERID=?AND USERPW=?";
			
			pst=conn.prepareStatement(sql);
			pst.setString(1, sp.getUserid());
			pst.setString(2, sp.getUserpw());
			pst.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(pst!=null)pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	
	//회원 목록 불러오기 
	public Vector getMemberList() {
		Vector v= new Vector();
		Connection conn=null;
		PreparedStatement pst=null;
		ResultSet rs=null; //커서 이동 
		String sql="";
		
	
		try {
			conn=DBConn.getConnDB();
			sql="SELECT*FROM MEMBERS";
			pst=conn.prepareStatement(sql);
			rs=pst.executeQuery();
			while (rs.next()) {
				Vector member =  new Vector();
				member.add(rs.getString("USERID"));
				member.add(rs.getString("USERNAME"));
				member.add(rs.getString("PHONE"));
				member.add(rs.getString("GENDER"));
				member.add(rs.getString("BIRTH"));
				member.add(rs.getString("COMMETS"));
				v.add(member);
				
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(rs!=null)rs.close();
				if(pst!=null)pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		return v;
	}

	
	
	//회원 정보 찾기 **************************************** 수정 요망 
	public void surchMember(SqlParameter sp) {
		Connection conn=null;
		PreparedStatement pst = null;
		String sql="";
		conn=DBConn.getConnDB();
		ResultSet rs=null;
		
		try {
			sql ="SELECT USERID,USERNAME,PHONE,GENDER,BIRTH,commets ";
			sql+=" FROM MEMBERS";
    		sql+=" WHERE USERNAME =?";
//			sql+=" WHERE USERNAME LIKE '%?%";
//			sql+=" WHERE USERNAME LIKE '%"+"?"+"%";
//			sql+=" OR USERID LIKE '%?%' ";  //이렇게 하면 에러 남 다른 방법 찾아 볼것 
//			sql+=" OR PHONE LIKE '%?%' ";
			
			pst=conn.prepareStatement(sql);
			pst.setString(1, sp.getUsername());
//			pst.setString(2, sp.getUserid());
//			pst.setString(3, sp.getPhone());
			
			pst.executeUpdate();
			
			rs=pst.executeQuery();
			while (rs.next()) {
				String id=rs.getString("USERID");
				String name=rs.getString("USERNAME");
				String gn=rs.getString("GENDER");
				
				String format="%s %s %s";
				String msg=String.format(format, id,name,gn);
				System.out.println(msg);
				
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	
	
	//코멘트 수정 
	public boolean updateComents(SqlParameter sp) {
		boolean ok=true;
		Connection conn=null;
		PreparedStatement pst = null;
		String sql="";
		conn=DBConn.getConnDB();
		
		try {
			
			sql ="UPDATE MEMBERS";
			sql+=" SET PHONE =?,GENDER=?,BIRTH=?,COMMETS=?";
			sql+=" WHERE USERID=?";
			
			pst=conn.prepareStatement(sql);
			pst.setString(1, sp.getPhone());
			pst.setString(2, sp.getGender());
			pst.setString(3, sp.getBirth());
			pst.setString(4, sp.getCommets());
			pst.setString(5, sp.getUserid());
			
			pst.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			try {
				if(pst!=null)pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return ok;
	}

	

	
}














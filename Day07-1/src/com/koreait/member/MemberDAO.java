package com.koreait.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.koreait.db.Dbconn;

public class MemberDAO {
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	String sql = "";
	
	public int join(MemberDTO member) {	// join method
		try {
			sql += "insert into tb_member(mem_userid, mem_userpw, mem_name, mem_hp, mem_email, mem_hobby, mem_ssn1,";
			sql += " mem_ssn2, mem_zipcode, mem_address1, mem_address2, mem_address3) values"; 
			sql += " (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			conn = Dbconn.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getUserid());
			pstmt.setString(2, member.getUserpw());
			pstmt.setString(3, member.getUsername());
			pstmt.setString(4, member.getHp());
			pstmt.setString(5, member.getEmail());
			String hobbystr = "";
			for(String hobby : member.getHobby()){
				hobbystr = hobbystr + hobby + " ";
			}
			pstmt.setString(6, hobbystr);
			pstmt.setString(7, member.getSsn1());
			pstmt.setString(8, member.getSsn2());
			pstmt.setString(9, member.getZipcode());
			pstmt.setString(10, member.getAddress1());
			pstmt.setString(11, member.getAddress2());
			pstmt.setString(12, member.getAddress3());
			if(pstmt.executeUpdate() != 0) {
				// 정상 일 때
				return 1;
			}
		}catch(Exception e) {
			 e.printStackTrace();
		}
		// 정상이 아닐 때
		return 0;
	}
	
	public MemberDTO login(MemberDTO member) {	// login method
		sql = "select mem_idx, mem_userid, mem_name from tb_member where mem_userid=? and mem_userpw=?";
		try {
			conn = Dbconn.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getUserid());
			pstmt.setString(2, member.getUserpw());
			rs = pstmt.executeQuery();
			if(rs.next()) {	// rs(db)에 data가 있으면
				member.setIdx(rs.getInt("mem_idx"));
				member.setUserid(rs.getString("mem_userid"));
				member.setUsername(rs.getString("mem_name"));
				return member;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public MemberDTO info(MemberDTO member) {	// info method
		sql = "select * from tb_member where mem_idx=?";
		try {
			conn = Dbconn.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,  member.getIdx());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				member.setUsername(rs.getString("mem_name"));
				member.setEmail(rs.getString("mem_email"));
				member.setHp(rs.getString("mem_hp"));
				String hobby[] = rs.getString("mem_hobby").split(" ");
				member.setHobby(hobby);
				member.setSsn1(rs.getString("mem_ssn1"));
				member.setSsn2(rs.getString("mem_ssn2"));
				member.setZipcode(rs.getString("mem_zipcode"));
				member.setAddress1(rs.getString("mem_address1"));
				member.setAddress2(rs.getString("mem_address2"));
				member.setAddress3(rs.getString("mem_address3"));
				return member;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean pwCheck(MemberDTO member) {	// pwCheck method
		sql = "select mem_idx from tb_member where mem_idx=? and mem_userpw=?";
		try {
			conn = Dbconn.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, member.getIdx());
			pstmt.setString(2, member.getUserpw());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public int edit(MemberDTO member) {
		sql = "update tb_member set mem_name=?, mem_hp=?, mem_email=?, mem_hobby=?, mem_ssn1=?, mem_ssn2=?, mem_zipcode=?, mem_address1=?, mem_address2=?, mem_address3=? where mem_userid=?";
		try {
			conn = Dbconn.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getUsername());
			pstmt.setString(2, member.getHp());
			pstmt.setString(3, member.getEmail());
			String hobbystr = "";
			for(String hobby : member.getHobby()) {
				hobbystr = hobbystr + hobby + " ";
			}
			pstmt.setString(4, hobbystr);
			pstmt.setString(5, member.getSsn1());
			pstmt.setString(6, member.getSsn2());
			pstmt.setString(7, member.getZipcode());
			pstmt.setString(8, member.getAddress1());
			pstmt.setString(9, member.getAddress2());
			pstmt.setString(10, member.getAddress3());
			pstmt.setString(11, member.getUserid());
			if(pstmt.executeUpdate() >= 1) {
				return 1;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
}

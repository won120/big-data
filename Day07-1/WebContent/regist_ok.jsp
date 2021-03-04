<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<%
	request.setCharacterEncoding("UTF-8");	// 요청 정보의 인코딩을 설정
%>
<jsp:useBean class="com.koreait.member.MemberDTO" id="member"/>	<!-- 데이터를 담을 수 있는 DTO -->
<jsp:useBean class="com.koreait.member.MemberDAO" id="dao"/>	<!-- 데이터를 처리하는 DAO? -->
<jsp:setProperty property="*" name="member"/>
<%
	if(dao.join(member) == 1){
%>
	<script>
		alert('회원가입 완료되었습니다.');
		location.href='login.jsp';
	</script>
<%
	}else{
%>
	<script>
		alert('회원가입 실패되었습니다.');
		history.back();
	</script>
<%
	}
%>
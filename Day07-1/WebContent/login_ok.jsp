<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
%>
<jsp:useBean id="member" class="com.koreait.member.MemberDTO"/>	<!-- 2개의 객체를 만듦 1 -->
<jsp:useBean id="dao" class="com.koreait.member.MemberDAO"/>	<!-- 2개의 객체를 만듦 2 -->
<jsp:setProperty property="*" name="member"/> 		<!-- name : 객체이름 -->
<%
	if(dao.login(member) != null){
		session.setAttribute("userid", member.getUserid());
		session.setAttribute("username", member.getUsername());
		session.setAttribute("useridx", member.getIdx());
%>
	<script>
		alert('로그인 되었습니다');
		location.href='login.jsp';
	</script>
<%
	}else{
%>
	<script>
		alert('아이디 또는 비밀번호를 확인하세요');
		history.back();
	</script>
<%
	}
%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<style type="text/css">
	#logiWrap{width: 800px; border: 1px solid #ccc;}
	#loginJoin #loginLogin{float: left; width: 50%;}
</style>
</head>
<body>
<div id="logiWrap">
	<div id="loginHeader">�α���</div>
	<div id="loginJoin">
		�ű԰�
		���� �¶��� ���� �̿��� ���� ȸ�������� ���ֽñ� �ٶ��ϴ�.
		<ul>
			<li>��ǰ ��� ��Ȳ �� ��ǰ ���� Ȯ��</li>
			<li>������ ���� ������ ��ǰ ��õ</li>
			<li>���� ��ǰ ����</li>
		</ul>
		<input type="button" value="�ű� ȸ�� ����" src="join.jsp">
	</div>
	<div id="loginLogin">
		<form method="post" action="login.do">
			���̵� <input type="text" name="id"><br>
			��й�ȣ <input type="text" name="pwd"><br>
			<input type="checkbox" name="idCheck"><br>
			<input type="checkbox" name="pwdCheck"><br>
			<input type="submit" value="�α���">
		</form>
	</div>
</div>
</body>
</html>
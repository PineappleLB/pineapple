<%@ page contentType="text/html; charset=utf-8"%>
<html>
<head>
	<meta charset="utf-8"/>
</head>
<body>

	<div id="app">
		<form action="user/login" method="post">
			
			
		</form>
	</div>
	<form action="user/login" method="post">
		<input name="name" type="text"/>
		<input name="pass" type="password"/>
		<br/>
		<input type="submit" value="提交">
	</form>
	<br/>
	<form action="user/register" method="post">
		<input name="username" type="text"/>
		<input name="password" type="password"/>
		<input name="pcode" type="text"/>
		<!-- <img  src="user/imageCode?num=1"> -->
		<input name="vcode" type="text"/>
		<br/>
		<input type="submit" value="提交">
	</form>
</body>
</html>

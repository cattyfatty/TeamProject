<%@ page contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>

<html>
	<head>
		<meta charset="UTF-8">
		<title>뿅뿅마트 : 로그인</title>
		<style type="text/css">
			* {
				margin: 0;
				padding: 0;
			}
			
			body {
				font-family: "돋움";
				font-size: 12px;
				color: white;
			}
			
			#header {
				width: 768px;
				height: 105px;
				position: relative;
				padding: 62px 0 0;
				box-sizing: border-box;
				margin: 0 auto;
			}
			
			div {
				display: block;
			}
			
			#container {
				width: 768px;
				padding-bottom: 30px;
			}
			
			#footer {
				position: relative;
				width: 768px;
				margin: 0 auto;
				text-align: center;
				padding-bottom: 38px;
			}
			
			#content {
				width: 460px;
				margin: 0 auto;
			}
		</style>
		<script src="resources/js/jquery-1.11.3.min.js" ></script>
		<script>
			$(function(){
		
	
	
	
	
			})
		</script>
	</head>
	<body>
		<div id="wrap">
			<div id="header">
				<h1>**[축]마트3조마트[축]**</h1>
			</div>
			<div id="container">
				<!-- content -->
				<div id="content">
					<form id="loginform" name="loginform" action="login" method="post">
						<div class="input_row" id="id_area">
							<span class="input_box"> 
							<input type="text" id="id" name="id"  placeholder="아이디" maxlength="41">
							</span>
						</div>
						<div class="input_row" id="pw_area">
							<span class="input_box"> 
							<input type="password" id="pw" name="password"  placeholder="비밀번호" maxlength="16" >
							</span>
						</div>
						<!-- 버튼 -->
						<span class="btn_join"> 
						<input type="button" id="joinbtn" onclick="location.href='joinForm'" value="회원가입"/>
						</span> 
						<span class="btn_login"> 
						<input type="submit"  id="loginbtn" value="로그인"/>
						</span>
					</form>
				</div>
			</div>
			<hr />
			<!--footer -->
			<div id="footer">3조 마트입니다~~~@_@</div>
		</div>
	</body>
</html>
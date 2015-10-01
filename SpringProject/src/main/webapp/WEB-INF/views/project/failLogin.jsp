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
		
	</head>
	<body>
		<div id="wrap">
			<div id="header">
				<h1>**[축]마트3조마트[축]**</h1>
			</div>
			<div id="container">
				<!-- content -->
				<div id="content">
						<div class="input_row" id="id_area">
							<p>로그인에 실패 하셨습니다. 아이디와 패스워드를 확인해 주세요!!</p>
						</div>
					
						<!-- 버튼 -->
						<span class="btn_join"> 
						<input type="button"  onclick="location.href='loginForm'" value="로그인 화면으로"/>
						</span> 
						
				</div>
			</div>
			<hr />
			<!--footer -->
			<div id="footer">3조 마트입니다~~~@_@</div>
		</div>
	</body>
</html>
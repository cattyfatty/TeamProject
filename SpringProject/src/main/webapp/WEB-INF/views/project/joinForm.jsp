<%@ page contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>회원가입</title>
		<style type="text/css">
			body{
				font-size: small;		
			}
			
			input {
				font-size: 12px;
			}
		
		</style>
	</head>
	
	
	<body>
	
		<h4>회원가입</h4>                          
		<form method="post" action="write">
			<table>
				<tr>
					<td>아이디</td>
					<td><input type="text" name="mid"/></td>
				</tr>
				<tr>
					<td>비밀번호</td>
					<td><input type="password" name="mpass"/></td>
				</tr>
				<tr>
					<td>이름</td>
				
					<td><input type="text" name="mname"/></td>
				</tr>
				<tr>
					<td>주소</td>
					<td><input type="text" name="maddress"/></td>
				</tr>
				<tr>
					<td>연락처(전화)</td>
					<td><input type="file" name="mtel"/></td>
				</tr>
				<tr>
					<td>이메일</td>
					<td><input type="email" name="memail"/></td>
				</tr>
								
				<tr>
					<td colspan="2" style="text-align: center;">
						<br/>
						<input type="submit" value="회원가입"/>
						<input type="reset" value="다시작성"/>					
					</td>
				</tr>
			</table>
		</form>
	
	</body>
</html>
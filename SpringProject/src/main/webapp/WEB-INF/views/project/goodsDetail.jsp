<%@ page contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<style type="text/css">
			body {
				font-family: "돋움";
				font-size: 12px;
				color: white;
			}
			span {
				display: inline-block;
				margin: 2px 10px;
			}
			
			span.title {
				margin: 2px 10px;
				border: 1px solid darkgray;
				background: #ef1919;
				width: 70px;
				text-align: center;
			}
			
			pre {
				margin: 10px;
				border: 1px solid darkgray;
				padding: 10px;
				width: 90%;
				height: 150px;
				font-size: 12px;
				overflow-y: scroll;
			}
			
			#part1 {display: flex;}
			#part1_1 {flex:1}
			#part1_2 {width: 120px; text-align: center; }
			#part1_2 img {display: block; padding: 10px; width: 100px; height: 100px;}
			
			#buttonGroup {
				margin: 10px;
				text-align: center;
			}
			
			#buttonGroup a {
				display:inline-block;
				width: 70px;
				line-height: 30px;
				text-decoration: none;
				font-size: small;
				color: white;
				border: 1px solid darkgray;
				background-color: gray;
				font-weight: bold;
			}
			
			#buttonGroup a:hover {
				color: black;
				background-color: lightgray;
			}

		</style>
	</head>
	
	<body>
		<h4>게시물 보기</h4>
		<div id="part1">
			<div id="part1_1">
				<span class="title">상품번호:</span> 
				<span class="content">${board.no }</span> <br/>
				<span class="title">상품명:</span> 
				<span class="content">${board.title }</span> <br/>
				<span class="title">가격:</span> 
				<span class="content">${board.writer }</span> <br/>
				<span class="title">상품종류:</span> 
				<span class="content">${board.date }</span> <br/>
				<span class="title">칼로리:</span> 
				<span class="content">${board.hitcount }</span> <br/>
				<span class="title">size:</span> 
				<span class="content">${board.hitcount }</span> <br/>
				<span class="title">사은품:</span>
				<span class="content">${board.originalFileName }</span> <br/>
			</div>
		</div>
		<div id="buttonGroup">
			<a href="goodsList?pageNo=${pageNo}">목록</a>
		</div>		
	</body>
</html>
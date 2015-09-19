<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<style type="text/css">
			body {
				color: white;
			}
			table {
				width: 100%;
				border-collapse: collapse;
				font-size: small;
			}
			table, th, td {
				border: 1px solid white;
				text-align: center;
			}
			th {
				background-color: #fe1919;
			}
			
			a { color: white; text-decoration: none; }
			a:hover {color: orange;}
			
						
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
			
			#pager {
				margin-top: 5px;
				text-align: center;
				font-size: small;
			}
			
			#pager a {
				text-decoration: none;
				color: white;
			}
			
			#pager a:hover {
				color: orange;
			}
			
			#pager a.pageNo {
				margin-left: 5px;
				margin-right: 5px;
			}
			
			#pager a.pageNo.selected {
				color: aqua;
			}
		</style>
		<script src="../resources/js/jquery-1.11.3.min.js"></script>
		<script>
			$(function() {
				var parentMenu = window.parent.$("#boardMenu");
				var orderSystemMenu = parentMenu.$("#orderSystem");
				orderSystemMenu.html("<a href='project/goodsList' target='iframe'>주문 시스템</a>");
				orderSystemMenu.after("<ul><li>로그아웃</li><li>카트보기</li></ul>");
				
			});
		</script>
		
	</head>
	
	<body>
		<h4>게시물 목록</h4>
		
		<table>
			<tr>
				<th style="width:50px">상품번호</th>
				<th>상품명</th>
				<th style="width:60px">가격</th>
				<th style="width:80px">종류</th>
				<th style="width:60px">칼로리</th>
			</tr>
			
			<c:forEach var="goods" items="${goodslist}">
				<tr>
					<td>${goods.no}</td>
					<td><a href="goodsDetail?goodsNo=${goods.no}">${goods.name}</a></td>
					<td>${goods.price}</td>
					<td>${goods.kind}</td>
					<td>${goods.calory}</td>
				</tr>
			</c:forEach>
			
		</table>
		
		<div id="pager">
			<a href="goodsList?pageNo=1">[처음]</a>
			
			<c:if test="${groupNo > 1 }">
				<a href="goodsList?pageNo=${startPageNo - pagesPerGroup }">[이전]</a>
			</c:if>
			<c:forEach var="i" begin="${startPageNo }" end="${endPageNo }">
			
				<a class='pageNo <c:if test="${pageNo == i }">selected</c:if>' href="goodsList?pageNo=${i}">${i}</a>
			</c:forEach>
			
			<c:if test="${groupNo < totalGroupNo }">
				<a href="goodsList?pageNo=${endPageNo + 1}">[다음]</a>
			</c:if>			

			<a href="goodsList?pageNo=${totalPageNo}">[맨끝]</a>
		</div>
		
		
	</body>
</html>









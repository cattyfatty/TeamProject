<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<style type="text/css">
			body {color: white; font-weight: 300;}
			a {color: white; text-decoration: none;}
			table{
				width:100%;
				border-collapse: collapse;
				font-size: small;
			}
			table, th, td{
				border: 1px solid white;
				text-align: center;
				
			}
			
			th{
				background-color: #fe1919;
			}
			
			#buttonGroup{
				margin: 10px;
				text-align: center;
				border-radius: 2px;
				background-color: gray;
				width: 150px; height: auto;
				vertical-align: middle;
				padding: 5px 0;
				position: relative;
				left: 50%; transform: translateX(-50%);
			}
			#buttonGroup:hover {background-color: #bc1919;}
			#buttonGroup a {display: inline-block;}
			
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
			#pager a.pageNo.selected {color: aqua;}
		</style>
	</head>
	
	<body>
		<h4>게시물 목록</h4>
		
		<table>
			<tr>
				<th style="width:50px">상품번호</th>
				<th style="width:60px">상품이름</th>
				<th style="width:80px">상품가격</th>
				<th style="width:60px">상품수량</th>
				<th style="width:60px">상품종류</th>
			</tr>
			<c:forEach var="product" items="${list}">
				<tr>
					<td>${product.no }</td>
					<td><a href="detail?productNo=${product.no }">${product.name}</a></td>
					<td>${product.price} </td>
					<td>${product.amount}</td>
					<td>${product.kind}</td>
				</tr>
			</c:forEach>
		</table>
		
		<div id="pager">
			<a href="list?pageNo=1">[처음]</a>
			
			<c:if test="${groupNo > 1 }">
				<a href="list?pageNo=${startPageNo - pagesPerGroup }">[이전]</a>
			</c:if>
			<c:forEach var="i" begin="${startPageNo }" end="${endPageNo }">
				<a class='pageNo <c:if test="${pageNo == i }">selected</c:if>' href="list?pageNo=${i}">${i}</a>
			</c:forEach>
			
			<c:if test="${groupNo < totalGroupNo }">
				<a href="list?pageNo=${endPageNo + 1}">[다음]</a>
			</c:if>			


			<a href="list?pageNo=${totalPageNo}">[맨끝]</a>
		</div>
		
		<div id="buttonGroup">
			<a href="writeForm">
				상품등록
			</a>
		</div>
	</body>
</html>








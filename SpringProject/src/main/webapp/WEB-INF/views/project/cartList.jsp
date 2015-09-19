<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	
	<title>Insert title here</title>
	<style type="text/css">
		body{
			background-color: black;
			color: white;
			
		
		}
		h1{
			text-align: center;
		}
		table{
			width: 100%;
		
		}
		table, th, td{
			border-collapse: collapse;
			border-style: double;
			text-align: center;
		}
		th{
			padding:10px;
			background-color: #B1B1B1;
			font-size:120%;
			
			
		}
		#buttonGroup{
			margin-top: 20px;
			text-align: center;
		}
		#buttonGroup a{
			text-decoration: none;
			display: inline-block;
			padding:7px;
			line-height: 30px;
			font-size: 110%;
			color:white;
			background-color: #8D8D8D;
			border: 1px solid white;
			
			
		}
		#buttonGroup a:hover{
			color:black;
			background-color:white;
		}
		#pager {
			margin-top:5px;
			text-align: center;
		}
		
		#pager a {
			text-decoration: none;
			color:white;
		}
		
		#pager a:hover {
			color: darkgray;
			
		}
		
		#pager a.pageNo {
			margin-left: 5px;
			margin-right: 5px;
		}
		
		#pager a.pageNo.selected {
			color: darkgary;
		}
		
		
	
	</style>

	</head>
	<body>
		<h2>장바구니 목록</h2>
	
		<table>
			<tr>
				<th style="width: 50px">상품번호</th>
				<th>상품명</th>
				<th style="width: 40px">수량</th>
				<th style="width: 60px">가격</th>
				<th style="width: 60px">총가격</th>
				<th style="width: 80px">날짜</th>
			</tr>
	
			<c:forEach var="cart" items="${cartlist}">
				<tr>
					<td>${goods.no}</td>
					<td>${cart.name}</td>
					<td>${cart.amount}</td>
					<td>${cart.price}</td>
					<td>${cart.amount * cart.price}</td>
					<td>${cart.date}</td>
				</tr>
			</c:forEach>
		</table>
	
		<div id="pager">
			<a href="list?pageNo=1">[처음]</a>
	
			<c:if test="${groupNo>1}">
				<a href="list?pageNo=${startPageNo-pagesPerGroup}">[이전]</a>
			</c:if>
	
			<c:forEach var="i" begin="${startPageNo}" end="${endPageNo}">
				<a class='pageNo <c:if test="${pageNo==i}">selected</c:if>'
					href="list?pageNo=${i}">${i}</a>
			</c:forEach>
	
			<c:if test="${groupNo<totalGroupNo}">
				<a href="list?pageNo=${endPageNo+1}">[다음]</a>
			</c:if>
	
			<a href="list?pageNo=${totalPageNo}">[맨끝]</a>
		</div>
		
		
		
		<div id="buttonGroup">
		
			<a href="deleteCart">장바구니 비우기</a>
			<a href="ordersList">주문하기</a>
		
	
	
	</body>
</html>
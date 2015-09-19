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
	</head>
	
	<body>
		<h4>주문상세정보</h4>
		
		주분번호: <span>${orderItem.orderNo}</span>
		
		<table>
			<tr>
				<th style="width:50px">상품 번호</th>
				<th style="width:60px">상품명</th>
				<th style="width:60px">수량</th>
				<th style="width:60px">가격</th>
			</tr>
			
			<c:forEach var="orderItem" items="${orderDetail}">
				<tr>
					<td>${orderItem.orderNo}</td>
					<td><a href="orderDetail?memberid=${orderItem.memberid}">${orderItem.memberid}</a></td>
					<td>${orderItem.orderPrice}</td>
				
				</tr>
			</c:forEach>
			
		</table>
		
		<div id="pager">
			<a href="orderDetail?pageNo=1">[처음]</a>
			
			<c:if test="${groupNo > 1 }">
				<a href="orderDetail?pageNo=${startPageNo - pagesPerGroup }">[이전]</a>
			</c:if>
			<c:forEach var="i" begin="${startPageNo }" end="${endPageNo }">
			
				<a class='pageNo <c:if test="${pageNo == i }">selected</c:if>' href="orderDetail?pageNo=${i}">${i}</a>
			</c:forEach>
			
			<c:if test="${groupNo < totalGroupNo }">
				<a href="orderDetail?pageNo=${endPageNo + 1}">[다음]</a>
			</c:if>			

			<a href="orderDetail?pageNo=${totalPageNo}">[맨끝]</a>
		</div>
		
		
	</body>
</html>









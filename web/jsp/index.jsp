<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="./include/headers.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="panel panel-default">
		<div class="panel-body">
			<spring:eval expression="@recorderDAO.list()" var="recordList" />
			<c:choose>
				<c:when test="${ recordList != null and recordList.size() > 0}">
							<audio controls> <source
				src="http://localhost:8080/PathToMp3/1.mp3" type="audio/mpeg" /> </audio>
				</c:when>
				<c:otherwise>
					Empty
				</c:otherwise>
			</c:choose>

		</div>
	</div>

</body>
</html>
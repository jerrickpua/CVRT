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
					<div class="panel-group" id="accordion">
						<c:forEach items="${recordList}" var="record" varStatus="status">
							<div class="panel panel-default">
								<div class="panel-heading">
									<span class="strong"><a data-toggle="collapse"
										data-parent="#accordion" href="#collapse_${status.index}"><c:out
												value="${record.startDate.toString( 'MM-dd-yyyy hh:mm:ssa' )} TO ${record.endDate.toString( 'MM-dd-yyyy hh:mm:ssa' )}" /><span
											class="caret"></span> </a> </span>
								</div>
								<div id="collapse_${status.index}"
									class="panel-collapse collapse">
									<div class="panel-body">
										<audio controls> <source
											src="http://localhost:
											8080/audio/${record.name}.mp3"
											type="audio/mpeg" /> </audio>
										<c:choose>
											<c:when test="${record.location != null }">
												<a class="btn btn-info" target="_blank"
													href="http://maps.google.com/?q=${record.location.latitude},${record.location.longitude}">MAP</a>
											</c:when>
											<c:otherwise>

											</c:otherwise>
										</c:choose>

									</div>
								</div>
							</div>

						</c:forEach>
					</div>


				</c:when>
				<c:otherwise>
					Empty
				</c:otherwise>
			</c:choose>

		</div>
	</div>

</body>
<script type="text/javascript">
	$('#accordion').on('shown.bs.collapse', function() {

		var panel = $(this).find('.in');

		$('html, body').animate({
			scrollTop : panel.offset().top
		}, 500);

	});
</script>
</html>
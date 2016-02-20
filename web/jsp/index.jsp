<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html lang="en">
<spring:eval expression="@recorderDAO.list()" var="recordList" />
<head>
<%@ include file="./include/headers.jsp"%>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>PCVRT</title>


</head>

<body>

	<div id="wrapper">

		<!-- Navigation -->
		<nav class="navbar navbar-default navbar-static-top" role="navigation"
			style="margin-bottom: 0">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-collapse">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="index.html">Portable Cockpit Voice
					Recorder and Transponder</a>
			</div>
			<!-- /.navbar-header -->

			<!-- /.navbar-top-links -->

			<div class="navbar-default sidebar" role="navigation">
				<div class="sidebar-nav navbar-collapse">
					<ul class="nav" id="side-menu">
						<li><a href="/"><i class="fa fa-dashboard fa-fw"></i>
								Dashboard</a></li>
					</ul>
				</div>
				<!-- /.sidebar-collapse -->
			</div>
			<!-- /.navbar-static-side -->
		</nav>

		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">Audio record admin</h1>
				</div>
				<!-- /.col-lg-12 -->
			</div>
			<!-- /.row -->
			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default">
						<div class="panel-heading">Audio records</div>
						<!-- .panel-heading -->
						<div class="panel-body">
							<div class="panel-group" id="accordion" style="overflow: scroll;height: 350px;">
							<c:choose>
								<c:when test="${recordList != null and recordList.size() > 0 }">
									<c:forEach items="${recordList}" var="record" varStatus="status">
										<div class="panel panel-default">
											<div class="panel-heading">
												<h4 class="panel-title">
													<a data-toggle="collapse" data-parent="#accordion"
														href="#collapse_${status.index }"><c:out
															value="${record.startDate.toString( 'MM-dd-yyyy hh:mm:ssa' )} TO ${record.endDate.toString( 'MM-dd-yyyy hh:mm:ssa' )}" /></a>
												</h4>
											</div>
											<div id="collapse_${status.index }"
												class="panel-collapse collapse">
												<div class="panel-body">
													<audio controls>
														<source src="/audio/${record.name}.mp3" type="audio/mpeg" />
													</audio>
													<c:if test="${record.location != null }">
														<a class="btn btn-info" target="_blank"
															href="http://maps.google.com/?q=${record.location.latitude},${record.location.longitude}">MAP</a>
													</c:if>
												</div>
											</div>
										</div>
									</c:forEach>
								</c:when>
								<c:otherwise>
									No audio record yet...
								</c:otherwise>
							</c:choose>

							</div>
						</div>
					</div>
					<!-- .panel-body -->
				</div>
				<!-- /.panel -->
			</div>
			<!-- /.col-lg-12 -->
		</div>
		<!-- /.row -->
	</div>
	<!-- /#page-wrapper -->

	</div>
	<!-- /#wrapper -->



</body>
<script type="text/javascript">
	$('#accordion').click(function() {

		$('audio').each(function() {
			this.pause(); // Stop playing
			this.currentTime = 0; // Reset time
		});
	});
</script>
</html>

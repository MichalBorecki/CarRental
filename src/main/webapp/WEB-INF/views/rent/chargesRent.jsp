<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@	taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/views/jspf/header.jspf"%>
<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/morris.js/0.5.1/morris.css">
</head>
<body>

	<%@ include file="/WEB-INF/views/jspf/navbar.jspf"%>

	<div class="container">

		<div class="form-row justify-content-center">

			<div class="form-group col-md-12 ">

				<c:choose>
					<c:when test="${ empty( rents ) }">
						<div class="container-box-child">
							<h5>Brak wypożyczeń</h5>
						</div>
					</c:when>
					<c:otherwise>
						<div class="container-box-child">
							<h5>Wypożyczenia:</h5>
						</div>
						<table class="table text-center table-hover table-bordered user-info-table">
							<thead>
								<tr>
									<th>L.p.</th>
									<th>Początek</th>
									<th>Koniec</th>
									<th>Czas</th>
									<th>0,50 gr/min</th>
									<th>Dystans</th>
									<th>0,80 gr/km</th>
								</tr>
							</thead>
							<c:set var="totalTime" value="0" />
							<c:set var="totalDistace" value="0" />
							<c:forEach items="${rents }" var="rent" varStatus="loopStatus">
								<c:set var="totalTime" value="${totalTime + rent.rentTime}" />
								<c:set var="totalDistace" value="${totalDistace + rent.distance}" />
								<tr>
									<td>${loopStatus.count}</td>
									<td>${rent.start }</td>
									<td>${rent.end }</td>
									<td>${rent.rentTime}&nbsp;min</td>
									<td>${rent.rentTime * 0.5}&nbsp;zł</td>
									<td>${rent.distance}&nbsp;km</td>
									<td>${rent.distance * 0.8}&nbsp;zł</td>
								</tr>
							</c:forEach>
							<tr class="table-info font-weight-bold">
								<td  style="text-align: left" colspan="3">Suma:</td>
								<td>${totalTime}&nbsp;min</td>
								<td>${totalTime*0.5}&nbsp;zł</td>
								<td>${totalDistace}&nbsp;km</td>
								<td>${totalDistace * 0.8}&nbsp;zł</td>
							</tr>
							<tr class="table-info font-weight-bold">
								<td style="text-align: left" colspan="8">Razem do zapłaty:&nbsp;${(totalTime*0.5)+(totalDistace*0.80)}&nbsp;zł</td>
							</tr>
						</table>
					</c:otherwise>
				</c:choose>


				<c:choose>
					<c:when test="${ empty( rents ) }"></c:when>
					<c:otherwise>
						<div class="form-group col-md-12 ">
							<div class="container-box-child">
								<h5>Przejechana odległość podczas kolejnych wypożyczeń:</h5>
							</div>
							<div id="chart-distance"></div>
						</div>

						<div class="form-group col-md-12 ">
							<div class="container-box-child">
								<h5>Czas kolejnych wypożyczeń:</h5>
							</div>
							<div id="chart-time"></div>
						</div>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</div>

	<%@ include file="/WEB-INF/views/jspf/footer.jspf"%>
	<script src="<c:url value ='/resources/js/user-info.js'></c:url>"></script>
	<script src="https://canvasjs.com/assets/script/jquery-1.11.1.min.js"></script>
	<script src="https://canvasjs.com/assets/script/jquery.canvasjs.min.js"></script>
</body>
</html>



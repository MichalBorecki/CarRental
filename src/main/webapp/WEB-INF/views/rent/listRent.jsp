<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@	taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/views/jspf/header.jspf"%>
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

						<table class="table text-center table-sm table-hover table-bordered">
							<thead>
								<tr>
									<th>L.p.</th>
									<th>Data i godzina wypożyczenia</th>
									<th>Czas</th>
									<th>Dystans</th>
									<th>Model</th>
									<th>Numer auta</th>
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
									<td>${rent.rentTime}&nbsp;min</td>
									<td>${rent.distance}&nbsp;km</td>
									<td>${rent.car.model}</td>
									<td>${rent.car.carNumber}</td>
								</tr>
							</c:forEach>
							<tr class="table-info font-weight-bold">
								<td>Suma:</td>
								<td></td>
								<td>${totalTime}&nbsp;min</td>
								<td>${totalDistace}&nbsp;km</td>
								<td></td>
								<td></td>
							</tr>
						</table>
					</c:otherwise>
				</c:choose>
			</div>

		</div>
	</div>

	<%@ include file="/WEB-INF/views/jspf/footer.jspf"%>
</body>
</html>



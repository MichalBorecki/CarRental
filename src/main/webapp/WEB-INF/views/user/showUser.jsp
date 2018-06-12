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

			<div id="info-user" class="form-group col-md-12 ">
				<div class="container-box-child">
					<h5>Informacje o użytkowniku:</h5>
				</div>
				<table class="table table-hover table-bordered text-center align-middle user-info-table">
					<thead>
						<tr>
							<th>Nazwa użytkownika</th>
							<th>Imię i nazwisko</th>
							<th>Status konta</th>
							<th>Akcja</th>
						</tr>
					</thead>
					<tr>
						<form:form action="/CarRental/user/activate" method="POST" modelAttribute="user">
							<td>${user.userName}</td>
							<td>${user.fullName}</td>
							<td><c:choose>
									<c:when test="${user.enabled == false}">
										<form:input path="enabled" hidden="enabled" value="1" />Konto nieaktywne</c:when>
									<c:otherwise>
										<form:input path="enabled" hidden="enabled" value="0" />Konto aktywne</c:otherwise>
								</c:choose></td>
							<td><a class="btn btn-info btn-sm btn-block" href="<c:url value='/message/add/${user.id}'></c:url>">Wyślij
									wiadomość</a> <input class="btn btn-secondary btn-sm btn-block" type="submit" value="Aktywuj/deaktywuj konto" /></td>
						</form:form>
					</tr>
				</table>

			</div>

			<div id="info-rent" class="form-group col-md-12 ">
				<c:choose>
					<c:when test="${ empty( rent ) }">
						<div class="container-box-child">
							<h5>Aktualnie brak wypożyczonego samochodu</h5>
						</div>
					</c:when>
					<c:otherwise>
						<div class="container-box-child">
							<h5>
								<c:out value="${msg}" default="Aktualnie wypożyczone auto:" />
							</h5>
						</div>
						<table class="table text-center table-hover table-bordered user-info-table">
							<form:input path="id" hidden="true" />
							<thead>
								<tr>
									<th>Model</th>
									<th>Numer</th>
									<th>Wypożyczenie od</th>
									<th>Akcja</th>
								</tr>
							</thead>
							<tr class="align-middle">
								<td>${rent.car.model}</td>
								<td>${rent.car.carNumber}</td>
								<td>${rent.start}</td>
								<td class="td-btn"><input class="btn btn-info w-100 h-100" type="submit" name="submit"
									value="Koniec wypożyczenia" /></td>
							</tr>
						</table>
					</c:otherwise>
				</c:choose>
			</div>


			<div id="info-rents" class="form-group col-md-12 ">

				<c:choose>
					<c:when test="${ empty( rents ) }">
						<div class="container-box-child">
							<h5>Brak wypożyczeń</h5>
						</div>
					</c:when>
					<c:otherwise>
						<div class="container-box-child">
							<h5>Historia wypożyczeń</h5>
						</div>
						<table class="table text-center table-hover table-bordered user-info-table">
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
			<div id="info-cost" class="form-group col-md-6 ">

				<c:choose>
					<c:when test="${ empty( rents ) }">
						<div class="container-box-child">
							<h5>Do zapłaty: 0 zł</h5>
						</div>
					</c:when>
					<c:otherwise>
						<div class="container-box-child">
							<h5>Do zapłaty:</h5>
						</div>
						<table class="table text-center table-hover table-bordered user-info-table">
							<thead>

								<tr>
									<td>Całkowity czas:</td>
									<td>${totalTime}min</td>
								</tr>
								<tr class="font-weight-bold">
									<td>Koszt (0.50 gr/min)</td>
									<td>${totalTime*0.5}zł</td>
								</tr>
								<tr>
									<td>Ilość przejechanych km:</td>
									<td>${totalDistace}km</td>
								</tr>
								<tr class="=font-weight-bold">
									<td>Koszt (0.80 gr/km)</td>
									<td>${totalDistace*0.80}zł</td>
								</tr>
								<tr class="table-info font-weight-bold">
									<td><b>Suma:</b></td>
									<td>${(totalTime*0.5)+(totalDistace*0.80)}zł</td>
								</tr>

							</thead>
						</table>
					</c:otherwise>
				</c:choose>
			</div>
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

	<%@ include file="/WEB-INF/views/jspf/footer.jspf"%>
	<script src="<c:url value ='/resources/js/user-info.js'></c:url>"></script>
	<script src="https://canvasjs.com/assets/script/jquery-1.11.1.min.js"></script>
	<script src="https://canvasjs.com/assets/script/jquery.canvasjs.min.js"></script>
</body>
</html>



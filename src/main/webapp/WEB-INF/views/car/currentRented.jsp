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
					<c:when test="${ empty( rent ) }">
						<div class="container-box-child">
							<h5>Nie masz wypożyczonego samochodu</h5>
						</div>
					</c:when>
					<c:otherwise>
					<div class="container-box-child">
						<h5><c:out value="${msg}" default="Aktualnie wypożyczone:" /></h5>
					</div>
						<form:form action="/CarRental/rent/end" method="POST" modelAttribute="rent">
							<table class="table text-center table-sm table-hover table-bordered">
							<form:input path="id" hidden="true"/>
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
									<td class="td-btn"><input class="btn btn-info w-100 h-100" type="submit" name="submit" value="Koniec wypożyczenia" /></td>
								</tr>
							</table>
						</form:form>
					</c:otherwise>
				</c:choose>
			</div>

		</div>
	</div>

	<%@ include file="/WEB-INF/views/jspf/footer.jspf"%>
</body>
</html>



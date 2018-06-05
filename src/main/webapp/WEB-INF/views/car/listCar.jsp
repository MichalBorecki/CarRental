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

				<div class="container-box-child">
					<div>
						<h5>
							<c:out value="${msg}"/>
						</h5>
					</div>
				</div>
				<table class="table table-sm table-hover table-bordered">
					<thead>
						<tr>
							<th>Model</th>
							<th>Numer</th>
							<th>Szczegóły samochodu</th>
							<th>Akcja</th>
						</tr>
					</thead>
					<c:forEach items="${cars }" var="car">
						<tr>
							<td>${car.model}</td>
							<td>${car.carNumber}</td>
							<td>${car.description}</td>
							<td><a class="btn btn-sm btn-info" href="<c:url value='/car/update/${car.id}'></c:url>">Edytuj</a> <a
								class="btn btn-sm btn-info" href="<c:url value='/car/delete/${car.id}'></c:url>">Usuń</a></td>
						</tr>
					</c:forEach>
				</table>
			</div>

		</div>
	</div>

	<%@ include file="/WEB-INF/views/jspf/footer.jspf"%>
</body>
</html>



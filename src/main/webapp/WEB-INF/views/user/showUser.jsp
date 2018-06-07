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

				<table class="table table-hover table-bordered text-center align-middle">
					<thead>
						<tr>
							<th>Nazwa użytkownika</th>
							<th>Imię i nazwisko</th>
							<th>Status konta</th>
							<th>Akcja</th>
						</tr>
					</thead>
						<tr><form:form action="/CarRental/user/activate" method="POST" modelAttribute="user">
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
						</form:form></tr>
				</table>
			</div>

		</div>
	</div>

	<%@ include file="/WEB-INF/views/jspf/footer.jspf"%>
</body>
</html>



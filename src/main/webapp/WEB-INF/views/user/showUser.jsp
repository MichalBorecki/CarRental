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
			
				<table class="table table-hover table-bordered">
					<thead>
						<tr>
							<th>Nazwa użytkonika</th>
							<th>Imię i nazwisko</th>
							<th>Status</th>
							<th>Akcja</th>
						</tr>
					</thead>
					<tr>
						<td>${user.userName}</td>
						<td>${user.fullName}</td>
						<td>${user.enabled}</td>
						<td><a class="btn btn-sm btn-info" href="<c:url value='/message/add/${user.id}'></c:url>">Wyślij wiadomość</a>
						<a class="btn btn-sm btn-info" href="<c:url value='/message/add/${user.id}'></c:url>">Aktywuj/deaktywuj konto</a>
						</td>
					</tr>
				</table>
			</div>

		</div>
	</div>

	<%@ include file="/WEB-INF/views/jspf/footer.jspf"%>
</body>
</html>



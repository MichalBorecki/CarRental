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

			<div class="form-group col-md-10 col-lg-8 ">

				<div class="container-box-child"><c:out  value="${msg }" default="Wyszukaj użytkownika"></c:out>
					
					<form class="input-group w-100" action="<c:url value='/user/findterm'></c:url>">
						<input type="text" class="form-control" name="term" placeholder="Szukaj..." required="true">
						<div class="input-group-append">
							<button class="btn btn-outline-secondary" type="submit">
								<i class="fas fa-search"></i>
							</button>
						</div>
					</form>
				</div>
			</div>

		</div>
	</div>

	<%@ include file="/WEB-INF/views/jspf/footer.jspf"%>
</body>
</html>



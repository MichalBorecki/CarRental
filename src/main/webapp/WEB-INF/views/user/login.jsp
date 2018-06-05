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

			<div class="form-group col-md-6 ">
				<p class='error'>${message}</p>
				<div class="container-box-child">

					<form:form method="post" modelAttribute="loginData">

						<div class="form-row">
							<div class="form-group col-md-12">
								<label for="email">E-mail</label>
								<form:input class="form-control is-valid" path="email" id="email" type="email" name="email" required="true"/>
								<form:errors path="email" cssClass="valid-feedback" />
							</div>
						</div>
						<div class="form-row">
							<div class="form-group col-md-12">
								<label for="email">Hasło</label>
								<form:password class="form-control is-valid" path="password" id="password"  name="password" required="true" />
								<form:errors path="password" cssClass="valid-feedback" />
							</div>
						</div>
						<div class="row justify-content-center">
							<div class="col-sm-12">
								<input class="btn btn-secondary btn-block" type="submit" value="Zaloguj" />
							</div>
						</div>

						<form:errors></form:errors>
					</form:form>


				</div>
			</div>
		</div>
	</div>
	<%@ include file="/WEB-INF/views/jspf/footer.jspf"%>
</body>
</html>
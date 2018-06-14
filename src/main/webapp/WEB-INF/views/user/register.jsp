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

			<div class="form-group col-md-6">

				<div class="container-box-child">
					<form:form method="post" modelAttribute="user">

						<div class="form-row">
							<div class="form-group col-md-12">
								<label for="email">Nazwa użytkownika</label>
								<form:input path="userName" id="userName" type="text" class="form-control is-valid" name="userName"
									required="true" />
								<form:errors path="userName" cssClass="valid-feedback" />
							</div>
						</div>

						<div class="form-row">
							<div class="form-group col-md-12">
								<label for="email">Imię</label>
								<form:input path="firstName" id="firstName" type="text" class="form-control is-valid" name="firstName"
									required="true" />
								<form:errors path="firstName" cssClass="valid-feedback" />
							</div>
						</div>

						<div class="form-row">
							<div class="form-group col-md-12">
								<label for="email">Nazwisko</label>
								<form:input path="lastName" id="lastName" type="text" class="form-control is-valid" name="lastName"
									required="true" />
								<form:errors path="lastName" cssClass="valid-feedback" />
							</div>
						</div>

						<div class="form-row">
							<div class="form-group col-md-12">
								<label for="email">E-mail</label>
								<form:input path="email" id="emailReg" type="email" class="form-control is-valid" name="email" required="true" />
								<form:errors path="email" cssClass="valid-feedback" />
							</div>
						</div>

						<div class="form-row">
							<div class="form-group col-md-12">
								<label for="email">Hasło</label>
								<div class="input-group">
									<form:password path="password" id="passwordReg" class="form-control is-valid" name="password"
										placeholder="Hasło musi mieć od 8 do 20 liter" required="true" data-toggle="password" />
									<div class="input-group-append">
										<span class="input-group-text"><i id="showPass" class="fa fa-eye"></i></span>
									</div>
								</div>
								<form:errors path="password" cssClass="valid-feedback" />

							</div>
						</div>

						<div class="row justify-content-center">
							<div class="col-sm-12">
								<input class="btn btn-secondary btn-block" type="submit" value="Sign in" />
							</div>
						</div>
					</form:form>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="/WEB-INF/views/jspf/footer.jspf"%>
	<script src="<c:url value ='/resources/js/password-form.js'></c:url>"></script>
</body>
</html>
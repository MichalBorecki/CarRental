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

			<div class="form-group col-md-8">

				<div class="container-box-child">

					<div>
						<h5><c:out value="${msg}" default="Dodaj auto" /></h5>
						<hr>
					</div>

					<form:form action="/CarRental/car/add" method="POST" modelAttribute="car">

						<div class="form-row">

							<div class="form-group col-md-12">
								<label class="label" for="model">Model</label>
								<form:input class="form-control is-valid" path="model" id="model" name="model" required="true" />
								<form:errors path="model" cssClass="valid-feedback" />
							</div>
							<div class="form-group col-md-6">
								<label class="label" for="carNumber">Numer samochodu</label>
								<form:input class="form-control is-valid" path="carNumber" id="carNumber" type="number" name="carNumber"
									required="true" placeholder="km" />
								<form:errors path="carNumber" cssClass="valid-feedback" />
							</div>
							<div class="form-group col-md-6">
								<label class="label" for="mileage">Przebieg</label>
								<form:input class="form-control is-valid" path="mileage" id="mileage" name="mileage" type="number" step="0.1"
									required="true" placeholder="km"/>
								<form:errors path="mileage" cssClass="valid-feedback" />
							</div>
							<div class="form-group col-md-12">
								<label class="label" for="description">Opis</label>
								<form:textarea class="form-control is-valid" path="description" rows="5" id="description" type="text"
									name="description" required="true" />
								<form:errors path="description" cssClass="valid-feedback" />
							</div>
							<div class="form-group col-md-12">Określ położenie początkowe auta:</div>
							<div class="form-group col-md-6">
								<label class="label" for="lat">Szerokość geograficzna</label>
								<form:input class="form-control is-valid" path="lat" id="lat" name="lat" type="number" step="0.0000001"
									required="true" />
								<form:errors path="lat" cssClass="valid-feedback" />
							</div>
							<div class="form-group col-md-6">
								<label class="label" for="lng">Długość geograficzna</label>
								<form:input class="form-control is-valid" path="lng" id="lng" name="lng" type="number" step="0.0000001"
									required="true" />
								<form:errors path="lng" cssClass="valid-feedback" />
							</div>
						</div>

						<div class="row justify-content-center">
							<div class="col-sm-12">
								<input class="btn btn-secondary btn-sm btn-block" type="submit" value="Dodaj auto" />
							</div>
						</div>
					</form:form>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="/WEB-INF/views/jspf/footer.jspf"%>
</body>
</html>
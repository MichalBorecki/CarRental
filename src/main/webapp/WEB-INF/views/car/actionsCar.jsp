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

		<div class="form-row justify-content-center ">


			<div class="button-single col-sm-12 col-md-10 col-lg-8">
				<a class="btn btn-info w-100" href="<c:url value='/'></c:url>">Wróć do mapy</a>


				<div class="container-box-child form-group col-md-12">

					<div class=" car-info">
						<p>
							Model:&nbsp;<b>${rent.car.model}</b>
						</p>
						<p>
							Numer:&nbsp;<b>${rent.car.carNumber}</b>
						</p>
						<p>
							Przebieg:&nbsp;<b>${rent.car.mileage}&nbsp;km</b>
						</p>
						<p>Szczegóły samochodu:
						<ul>
							<b>${rent.car.description}</b>
						</ul>
						</p>
					</div>

					<form:form action="/CarRental/rent/start" method="POST" modelAttribute="rent">
						
						<form:hidden path="user" name="user" value="${rent.user.id}" />
						<form:hidden path="car" name="car" value="${rent.car.id }" />
						<form:hidden path="latStart" name="latStart" value="${rent.car.lat }" />
						<form:hidden path="lngStart" name="lngStart" value="${rent.car.lng }" />

						<input class="btn btn-info w-100" type="submit" name="submit" value="Wypożycz" />
					</form:form>
				</div>
			</div>
		</div>
	</div>

	<%@ include file="/WEB-INF/views/jspf/footer.jspf"%>
</body>
</html>



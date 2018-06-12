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
			<div class="col-md-12 ">

				<div class="container-box-child">
					<h5>Lokalizacja wolnych samochodów</h5>
				</div>

				<div id="map"></div>
			</div>
		</div>
	</div>
	<script src="<c:url value ='/resources/js/map-admin.js'></c:url>"></script>
	<%@ include file="/WEB-INF/views/jspf/footer.jspf"%>
	
<script async defer
	src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCz4rXsfTPzzbKSAkSMcsQtu_myTBLGDs0&callback=initMap">
</script>

</body>
</html>



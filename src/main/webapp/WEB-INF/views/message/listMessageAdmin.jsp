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

			<div class="form-group col-md-12">

				<table class="table table-sm table-hover table-bordered text-center table-message">
					<thead>
						<tr>
							<td class="table-warning col-10 slide-all" colspan="2">Pokaż/ukryj wszystkie</td>
						</tr>
						<tr style="padding: 15px">
							<th class="col-5" style="width: 50%" >CarRental</th>
							<th class="col-5" style="width: 50%" >${sender.fullName}</th>
						</tr>
					</thead>
					<c:forEach items="${messages }" var="message">
						<!-- Set color (red for urgent message) and font style (bold for unread message) -->
						<tr class="message-tr  
							<c:choose>
									<c:when test="${message.urgent == false}">table-info</c:when>
									<c:when test="${message.urgent == true}">table-danger</c:when>
									<c:when test="${(message.ifRead == false) and (message.receiver.id == sessionScope.user.id)}"> font-weight-bold</c:when>
							</c:choose>" data-id="${message.id }"  data-user="${message.receiver.id }">
							<!-- Messages from this user - where receiver is Administration -->
							<td class="border-dark"><c:choose>
									<c:when test="${message.receiver.id == sender.id}">${message.created}</c:when>
							</c:choose></td>
							<!-- Messages from Administration to this user - where receiver is sessionScope user-->
							<td class="border-dark"><c:choose>
									<c:when test="${message.receiver.id == 1}">${message.created}</c:when>
							</c:choose></td>
						</tr>
						<tr class="
							<c:choose>
									<c:when test="${message.urgent == false}">table-info</c:when>
									<c:when test="${message.urgent == true}">table-danger</c:when>
									<c:when test="${(message.ifRead == false) and (message.receiver.id == sessionScope.user.id)}"> font-weight-bold</c:when>
							</c:choose>"><td style="padding: 2px">
     						 <div class="slide-row" style="display: none;"><c:choose><c:when test="${message.receiver.id == sender.id}">${message.messageText}</c:when></c:choose></div></td>
     						 
     						 <td style="padding: 2px">
     						 <div class="slide-row" style="display: none;"><c:choose><c:when test="${message.receiver.id == 1}">${message.messageText}</c:when></c:choose></div></td></tr>
					</c:forEach>
				</table>
			</div>

		</div>
	</div>

	<%@ include file="/WEB-INF/views/jspf/footer.jspf"%>
	<script src="<c:url value ='/resources/js/message.js'></c:url>"></script>
</body>
</html>



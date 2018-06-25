<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@    taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <%@ include file="/WEB-INF/views/jspf/header.jspf" %>
</head>
<body>

<%@ include file="/WEB-INF/views/jspf/navbar.jspf" %>

<div class="container">

    <div class="form-row justify-content-center">

        <div class="form-group col-md-12 ">

            <table class="table table-hover table-bordered text-center align-middle">
                <thead>
                <tr>
                    <th>E-mail</th>
                    <th>Imię i nazwisko</th>
                    <th>Status konta</th>
                    <th>Akcja</th>
                </tr>
                </thead>
                <c:forEach items="${users}" var="user">
                    <tr><form:form action="/user/activate/${user.id}" method="GET" modelAttribute="user">
                        <td>${user.email}</td>
                        <td>${user.fullName}</td>
                        <td>
                            <c:if test="${user.active == 0}">Konto nieaktywne</c:if>
                            <c:if test="${user.active == 1}">Konto aktywne</c:if>
                        </td>
                        <td><a class="btn btn-info btn-sm btn-block"
                               href="<c:url value='/user/info/${user.id}'></c:url>">Szczegóły użytkownika</a>
                            <a class="btn btn-info btn-sm btn-block"
                               href="<c:url value='/message/add/${user.id}'></c:url>">Wyślij
                                wiadomość</a>
                            <a class="btn btn-info btn-sm btn-block"
                               href="<c:url value='/message/adminlist/${user.id}'></c:url>">Pokaż listę wiadomości</a>
                            <input class="btn btn-secondary btn-sm btn-block" type="submit"
                                   value="Aktywuj/deaktywuj konto"/></td>
                    </form:form></tr>
                </c:forEach>
            </table>
        </div>

    </div>
</div>

<%@ include file="/WEB-INF/views/jspf/footer.jspf" %>
</body>
</html>



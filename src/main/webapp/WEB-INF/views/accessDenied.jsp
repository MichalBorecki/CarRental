<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <%@ include file="/WEB-INF/views/jspf/header.jspf" %>
</head>
<body>

<%@ include file="/WEB-INF/views/jspf/navbar.jspf" %>

<div class="container">
    <div class="row">
        <div class="col-md-12">
            <div class="error-template">
                <h1>
                    Oops!</h1>
                <h2 text="${StatusType}"></h2>
                <h2 text="${ErrorType}"></h2>
                <h2 text="${TraceType}"></h2>
                <h2 text="${MessageType}"></h2>
                <h2 text="${TimeStamp}"></h2>
                <div class="error-details">
                    Błąd, przepraszamy!
                </div>
                <div class="error-actions">
                    <a href="/login" class="btn btn-primary btn-lg"><span
                            class="fas fa-home"></span>Logowanie </a>
                </div>
            </div>
        </div>
    </div>
</div>

<%@ include file="/WEB-INF/views/jspf/footer.jspf" %>
</body>
</html>
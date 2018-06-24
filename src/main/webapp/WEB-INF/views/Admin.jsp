<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@    taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <%@ include file="/WEB-INF/views/jspf/header.jspf" %>
</head>
<body>

<%@ include file="/WEB-INF/views/jspf/navbar.jspf" %>

<div class="container"><br/>
    <div class="alert alert-success">
        <p>its working as expected</p>
        <div text="${userName}" id="scheduler"></div>
        <div text="${adminMessage}"></div>
    </div>
    <a href="/logout" class="btn btn-info" role="button">Logout</a>
</div>

<%@ include file="/WEB-INF/views/jspf/footer.jspf" %>
</body>
</html>
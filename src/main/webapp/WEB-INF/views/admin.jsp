<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <%@ include file="/WEB-INF/views/jspf/header.jspf" %>
</head>
<body>

<%@ include file="/WEB-INF/views/jspf/navbar.jspf" %>

<div class="container"><br/>
    <div class="alert alert-success">
        <div id="scheduler">${userName}</div>
        <div>${adminMessage}</div>
    </div>
</div>

<%@ include file="/WEB-INF/views/jspf/footer.jspf" %>
</body>
</html>
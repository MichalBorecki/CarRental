<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@    taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <%@ include file="/WEB-INF/views/jspf/header.jspf" %>
    <script src="/webjars/jquery/3.2.1/jquery.min.js"></script>
    <script src='https://www.google.com/recaptcha/api.js'></script>
    <script src="<c:url value ='/resources/js/Valid.js'></c:url>"></script>
</head>
<body>

<%@ include file="/WEB-INF/views/jspf/navbar.jspf" %>

<div class="container">
    <div class="form-row justify-content-center">
        <div class="form-group col-md-6">
            <h5>Formularz rejestracji</h5>
            <form:form action="registration" method="post" modelAttribute="userDto">
            <div class="form-row">
                <div class="form-group col-md-12">
                    <div class="input-group-append">
                        <span class="input-group-text">
                    <i class="fas fa-user"></i></i></span>
                    <form:input class="form-control" path="firstName" type="text" placeholder="Imię" required="true"/></div>
                    <form:errors path="firstName" cssClass="valid-feedback"/>
                </div>

                <div class="form-group col-md-12">
                    <div class="input-group-append">
                        <span class="input-group-text">
                    <i class="fas fa-user"></i></i></span>
                    <form:input class="form-control" path="lastName" type="text" placeholder="Nazwisko"
                                required="true"/></div>
                    <form:errors path="lastName" cssClass="valid-feedback"/>
                </div>

                <div class="form-group col-md-12">
                    <div class="input-group-append">
                        <span class="input-group-text">
                    <i class="fas fa-envelope"></i></i></span>
                    <form:input class="form-control" path="email" placeholder="Adres e-mail" required="true"
                                type="email"/></div>
                    <form:errors path="email" cssClass="valid-feedback"/>
                </div>

                <div class="form-group col-md-12">
                    <div class="input-group-append">
                        <span class="input-group-text">
                    <i class="icon-append fas fa-lock"></i></span>
                    <form:password class="form-control" path="password" id="password" name="password"
                                   placeholder="Hasło" required="true"/></div>
                    <form:errors path="password" cssClass="valid-feedback"/>
                </div>

                <div class="form-group col-md-12">
                    <div class="input-group-append">
                        <span class="input-group-text">
                    <i class="icon-append fas fa-lock"></i></span>
                        <form:password class="form-control" path="matchingPassword" id="matchPassword"
                                       name="matchingPassword"
                                       placeholder="Ponów hasło" required="true"/></div>
                    <form:errors path="matchingPassword" cssClass="valid-feedback"/>
                </div>

                <section>
                    <div class="g-recaptcha" attr="data-sitekey=${captchaService.getReCaptchaSite()}"
                         data-callback="onReCaptchaSuccess" data-expired-callback="onReCaptchaExpired"></div>
                </section>
                <section>
                    <p id="captchaError"></p>
                </section>

                <div style="margin-top:10px" class="form-group col-md-12">
                    <button id="btn-signup" type="submit" class="btn btn-primary w-100">Zarejestruj</button>
                </div>
                </form:form>

            </div>
        </div>
    </div>
</div>

<%@ include file="/WEB-INF/views/jspf/footer.jspf" %>

</body>
</html>
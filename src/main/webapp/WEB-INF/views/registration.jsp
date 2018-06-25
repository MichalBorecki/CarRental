<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <%@ include file="/WEB-INF/views/jspf/header.jspf" %>
    <script src='https://www.google.com/recaptcha/api.js'></script>
    <script src="<c:url value ='/resources/js/Valid.js'></c:url>"></script>
</head>
<body>

<%@ include file="/WEB-INF/views/jspf/navbar.jspf" %>

<div class="container">
    <div class="form-row justify-content-center">
        <div class="form-group col-md-8 col-lg-6 box-container">
            <h5>Formularz rejestracji</h5>
            <form:form action="registration" method="post" modelAttribute="userDto">
            <div class="form-row">
                <div class="form-group col-md-12">
                    <div class="input-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text"><i class="fas fa-user"></i></span>
                        </div>
                        <form:input class="form-control" path="firstName" type="text" placeholder="Imię"
                                    required="true"/>
                        <form:errors path="firstName" class="has-error"/>
                    </div>
                </div>

                <div class="form-group col-md-12">
                    <div class="input-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text"><i class="fas fa-user"></i></span>
                        </div>
                        <form:input class="form-control" path="lastName" type="text" placeholder="Nazwisko"
                                    required="true"/>
                        <form:errors path="lastName" class="has-error" />
                    </div>
                </div>

                <div class="form-group col-md-12">
                    <div class="input-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text"><i class="fas fa-at"></i></span>
                        </div>
                        <form:input class="form-control" path="email" placeholder="Adres e-mail"
                                    required="true"
                                    type="email"/>
                        <form:errors path="email" class="has-error" />
                    </div>
                </div>

                <div class="form-group col-md-12">
                    <div class="input-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text"><i class="fas fa-lock"></i></span>
                        </div>
                        <form:password class="form-control" path="password" id="password" name="password"
                                       placeholder="Hasło" required="true"/>
                        <div class="input-group-append">
                            <span class="input-group-text"><i id="showPass" class="fa fa-eye"></i></span>
                        </div>
                        <form:errors path="password" class="has-error" />
                    </div>
                </div>

                <div class="form-group col-md-12">
                    <div class="input-group">
                        <div class="input-group-prepend">
                        <span class="input-group-text">
                    <i class="fas fa-lock"></i></span></div>
                        <form:password class="form-control" path="matchingPassword" id="matchPassword"
                                       name="matchingPassword"
                                       placeholder="Ponów hasło" required="true"/>
                        <div class="input-group-append">
                            <span class="input-group-text"><i id="showPass2" class="fa fa-eye"></i></span>
                        </div>
                        <form:errors path="matchingPassword" class="has-error" />
                    </div>
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
<script src="<c:url value ='/resources/js/password-form.js'></c:url>"></script>
</body>
</html>
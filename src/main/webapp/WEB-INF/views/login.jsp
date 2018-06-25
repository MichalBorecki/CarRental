<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <%@ include file="/WEB-INF/views/jspf/header.jspf" %>
</head>
<body>

<%@ include file="/WEB-INF/views/jspf/navbar.jspf" %>

<div class="container">
    <div class="form-row justify-content-center">
        <div class="form-group col-md-8 col-lg-6 box-container">
            <c:if test="${sessionScope.errorMsg != null}">
                <div class="alert alert-danger">
                    <a href="#" class="close" data-dismiss="alert"
                       aria-label="close">×</a>
                    <strong>${sessionScope.errorMsg}</strong>
                </div>
            </c:if>

            <h5>Logowanie</h5>
            <div style="display:none" id="login-alert" class="alert alert-danger col-sm-12"></div>

            <form action="/login" method="post" class="sky-form" role="form">
                <div class="form-row">

                    <div class="form-group col-md-12">
                        <input id="login-email" type="email" class="form-control" name="email"
                               placeholder="E-mail" required="true"/>
                        <form:errors path="*" class="has-error"/>
                    </div>

                    <div class="form-group col-md-12">
                        <div class="input-group">
                            <input type="text" style="display:none;"/>
                            <input placeholder="Hasło" id="password" class="form-control"
                                   name="password" type="password" required="true"/>
                            <form:errors path="password" class="has-error"/>
                            <div class="input-group-append">
                                <span id="showPass" class="input-group-text"><i class="fa fa-eye"></i></span>
                            </div>
                        </div>
                        <%--<div style="float:right; font-size: 80%;"><a href="#">Zapomniałeś hasła?</a></div>--%>
                    </div>


                    <div class="checkbox" style="margin-left: 6px">
                        <label>
                            <input id="login-remember" type="checkbox" name="remember-me" value="1"/> Zapamiętaj
                            mnie
                        </label>
                    </div>


                    <div style="margin-top:10px" class="form-group col-md-12">
                        <!-- Button -->
                        <button text="Zaloguj" type="submit" class="btn btn-primary w-100">Zaloguj</button>
                    </div>

                    <div class="form-group col-md-12">
                        <div style="padding-top: 5px; font-size:85%">
                            Nie masz jeszcze konta?
                            <a href="/registration">Zarejestruj się tutaj</a>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

<%@ include file="/WEB-INF/views/jspf/footer.jspf" %>
<script src="<c:url value ='/resources/js/password-form.js'></c:url>"></script>

</body>
</html>
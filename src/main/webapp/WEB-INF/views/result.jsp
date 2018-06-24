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

<div class="container">
    <div class="form-row justify-content-center">
        <div class="form-group col-md-6 ">
            <h5>Logowanie</h5>
            <div style="display:none" id="login-alert" class="alert alert-danger col-sm-12"></div>

            <form action="/login" method="post" class="sky-form" role="form">
                <div class="form-row">

                    <div class="form-group col-md-12">
                        <input path="email" id="login-email" type="email" class="form-control" name="email"
                               placeholder="Email" />
                    </div>

                    <div class="form-group col-md-12">
                        <input type="text" style="display:none;"/>
                        <input path="password" placeholder="Hasło" class="form-control"
                               id="login-password" name="password" type="password" />
                        <div style="float:right; font-size: 80%;"><a href="#">Zapomniałeś hasła?</a></div>
                    </div>


                    <div class="input-group">
                        <div class="checkbox">
                            <label>
                                <input id="login-remember" type="checkbox" name="remember-me" value="1"/> Zapamiętaj mnie
                            </label>
                        </div>
                    </div>

                    <div style="margin-top:10px" class="form-group col-md-12">
                        <!-- Button -->
                        <button text="Zaloguj" type="submit" class="btn btn-primary w-100">Zaloguj</button>
                    </div>

                    <div class="form-group col-md-12">
                        <div class="col-md-12 control">
                            <div style="padding-top:15px; font-size:85%">
                                Nie masz jeszcze konta?
                                <a href="/registration"
                                   onClick="$('#loginbox').hide(); $('#signupbox').show()">
                                    Zarejestruj się tutaj
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

<%@ include file="/WEB-INF/views/jspf/footer.jspf" %>
</body>
</html>
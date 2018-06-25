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

                <h5>Logowanie</h5>
                <div style="display:none" id="login-alert" class="alert alert-danger col-sm-12"></div>

                <form action="/login" method="post" class="sky-form" role="form">
                    <div class="form-row">

                        <div class="form-group col-md-12">
                            <input id="login-email" type="email" class="form-control" name="email"
                                   placeholder="Email" required="true"/>
                            <form:errors path="email" cssClass="valid-feedback"/>
                        </div>

                        <div class="form-group col-md-12">
                            <div class="input-group">
                                <input type="text" style="display:none;"/>
                                <input placeholder="Hasło" id="password" class="form-control"
                                       name="password" type="password" required="true"/>
                                <form:errors path="password" cssClass="valid-feedback"/>
                                <div class="input-group-append">
                                    <span id="showPass" class="input-group-text"><i class="fa fa-eye"></i></span>
                                </div>
                            </div>
                            <div style="float:right; font-size: 80%;"><a href="#">Zapomniałeś hasła?</a></div>
                        </div>



                            <div class="checkbox" style="margin-left: 5px">
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
<script src="<c:url value ='/resources/js/password-form.js'></c:url>"></script>
</body>
</html>
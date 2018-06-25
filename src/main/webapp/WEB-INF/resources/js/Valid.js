$(document).ready(function () {
    $('form').submit(function (event) {
        register(event);
    });

});

function register(event) {
    $(".alert").html("").hide();
    $(".error-list").html("");


    if (typeof grecaptcha !== 'undefined') {
        var resp = grecaptcha.getResponse();
        if (resp.length == 0) {
            event.preventDefault();
            $("#captchaError").show().html("Weryfikacja, czy nie jesteś robotem.");
        }
    }
}

var onReCaptchaSuccess = function (response) {
    $("#captchaError").html("").hide();
};
var onReCaptchaExpired = function (response) {
    $("#captchaError").html("Formularz reCaptcha wygasł. Uzupełnij kolejny").show();
    grecaptcha.reset();
};

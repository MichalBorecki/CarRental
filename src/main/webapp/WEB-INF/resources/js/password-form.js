$( "#showPass" )
  .mouseup(function() {
    $("#passwordReg").attr('type','password');
  })
  .mousedown(function() {
    $("#passwordReg").attr('type','text');
  });
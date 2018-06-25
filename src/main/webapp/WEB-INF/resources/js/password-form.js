$( "#showPass" )
  .mouseup(function() {
    $("#password").attr('type','password');
  })
  .mousedown(function() {
    $("#password").attr('type','text');
  });

$( "#showPass2" )
    .mouseup(function() {
        $("#matchPassword").attr('type','password');
    })
    .mousedown(function() {
        $("#matchPassword").attr('type','text');
    });
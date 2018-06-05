var tableRow = $(".message-tr");

tableRow.on("click", function showMessageText(event) {

	// Find element div which is not epmty
	var element = $(event.currentTarget.nextElementSibling).find(".slide-row:not(:empty)")

		element.slideToggle(400);
});

var buttonShowHide = $(".slide-all");

buttonShowHide.on("click", function showMessageText(event) {
	
	var element = $(".slide-row:not(:empty)");
	
	if (element.css('display') === 'none') {
		$(".slide-row:not(:empty)").slideUp(10);
		$(".slide-row:not(:empty)").slideDown(400);
	} else if (element.css('display') === 'block') {
		$(".slide-row:not(:empty)").slideDown(10)
		$(".slide-row:not(:empty)").slideUp(400);
	}
});

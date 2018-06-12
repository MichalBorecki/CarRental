/*
 * JavaScript for view with messages (user and admin)
 */
var tableRow = $(".message-tr");

/*
 * Show message on click on each <td> with date of each message
 */
tableRow.on("click", function showMessageText(event) {

	// Find element div which is not epmty
	var element = $(event.currentTarget.nextElementSibling).find(".slide-row:not(:empty)")

	element.slideToggle(400);
});

/*
 * Show message on click on top button (show/hide all)
 */
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

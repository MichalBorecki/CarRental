/*
 * JS for admin view of map
 */

var markers = [];
/*
 * Set markers[0] on 0 - after initializaction it will be userMarker
 * -localization of user
 */
markers[0] = 0;
var map;
var pos;

/*
 * Initialize the map
 */
function initMap() {
	map = new google.maps.Map(document.getElementById('map'), {
		zoom : 13,
		center : {
			lat : 51.10905,
			lng : 17.03107
		},
		mapTypeId : google.maps.MapTypeId.ROADMAP
	});

	/*
	 * ------------------- MARKERS FOR CARS -------------------
	 */

	var url = "http://localhost:8080/";
	var htmlType = "GET";

	/*
	 * Call method ajaxCall and get cars data
	 */
	ajaxCall(htmlType).done(
			function(cars) {
				console.log("Free cars loaded");
				console.log(cars);

				setMarkers(map)

				/*
				 * Add markers to the map
				 */
				function setMarkers(map) {

					/*
					 * Shape of marker
					 */

					/*
					 * Create markers for each car
					 */
					cars.forEach(function(car) {
						var marker = new google.maps.Marker({
							position : {
								lat : car.lat,
								lng : car.lng
							},
							map : map,
							icon : {
								path : google.maps.SymbolPath.BACKWARD_CLOSED_ARROW,
								scale : 5
							},
							zIndex : car.carNumber
						});

						var infowindow = new google.maps.InfoWindow();
						var clicked = false;

						/*
						 * Close all opened infowindows and set clicked on false
						 */
						google.maps.event.addListener(map, 'click', function() {
							if (infowindow) {
								infowindow.close();
								clicked = false;
							}
						});

						/*
						 * content of each marker
						 */
						var contentString = '<div class="iw-content">'
								+ '<p><a class="btn btn-info iw-subTitle" href="' + url + 'car/service/'
								+ car.carNumber + '?lat=' + car.lat + '&lng=' + car.lng +'">Wezwij serwisanta:<br><b>'
								+ car.model + '</b><br>nr auta: <b>' + car.carNumber + '</b></a></p></div>';

						/*
						 * Mouseover
						 */
						google.maps.event.addListener(marker, 'mouseover', function() {
							if (!clicked) {
								infowindow.setContent(contentString);
								infowindow.open(map, marker);

							}
						});

						/*
						 * Mouseout
						 */
						google.maps.event.addListener(marker, 'mouseout', function() {
							if (!clicked) {
								infowindow.close();
							}
						});

						/*
						 * On click on marker
						 */
						google.maps.event.addListener(marker, 'click', function() {
							clicked = true;
							infowindow.setContent(contentString);
							infowindow.open(map, marker);

						});

						/*
						 * Click on close button, change var clicked on false
						 */
						google.maps.event.addListener(infowindow, 'closeclick', function() {
							clicked = false;
						})

						/*
						 * Set the look of infowindow content
						 */
						google.maps.event.addListener(infowindow, 'domready', function() {
							var iwOuter = $('.gm-style-iw');
							var iwBackground = iwOuter.prev();
							iwBackground.children(':nth-child(2)').css({
								'background' : '#ffffff'
							});
							var iwmain = iwBackground.children(':nth-child(2)');
							iwBackground.children(':nth-child(4)').css({
								'display' : 'none'
							});
							var iwCloseBtn = iwOuter.next();
						});

					});

				}

			}).fail(function(statusText, e) {
		console.log("Error: " + e);
		console.log(statusText);
	}).always(function() {
		console.log("End of connection");

	});

	/*
	 * AjaxCall for get list of free cars
	 */
	function ajaxCall(htmlType) {
		return $.ajax({
			type : htmlType,
			url : url + "car/allFreeForMap",
			headers : {
				'Accept' : 'application/json',
				'Content-Type' : 'application/json'
			},
			dataType : 'json'
		});

	}
	;
}

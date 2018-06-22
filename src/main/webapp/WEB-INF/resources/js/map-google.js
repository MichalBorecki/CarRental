
var markers = [];
/*
 * Set markers[0] on 0 - after initializaction it will be userMarker -localization of user
 */
markers[0] = 0;
var map;
//var shape = {
//	coords : [ 1, 1, 1, 20, 18, 20, 18, 1 ],
//	type : 'poly'
//};
var pos;
var directionsService;
var directionsDisplay;

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

	directionsService = new google.maps.DirectionsService;
	directionsDisplay = new google.maps.DirectionsRenderer;


	/*
	 * Set trafficLayer on map
	 */
	var trafficLayer = new google.maps.TrafficLayer();
	trafficLayer.setMap(map);

	/*
	 * MARKER/INFO FOR USER
	 */
	var infoUser = new google.maps.InfoWindow;

	if (navigator.geolocation) {
		navigator.geolocation.getCurrentPosition(function(position) {
			pos = {
				lat : position.coords.latitude,
				lng : position.coords.longitude
			};

			var userMarker = new google.maps.Marker({
				position : pos,
				map : map,
			});
			/*
			 * Set markers[0] as userMarker
			 */
			markers[0] = userMarker;

			infoUser.setPosition(pos);
			infoUser.setContent('<div class="iw-content"><p>Twoja lokalizacja</p></div>');

			/*
			 * infoUser Mouseover
			 */
			google.maps.event.addListener(userMarker, 'mouseover', function() {
				infoUser.open(map, userMarker);
			});

			/*
			 * infoUser Mouseout
			 */
			google.maps.event.addListener(userMarker, 'mouseout', function() {
				infoUser.close();
			});

			map.setCenter(pos);
		}, function() {
			handleLocationError(true, infoUser, map.getCenter());
		});
	} else {
		// Browser doesn't support Geolocation
		handleLocationError(false, infoUser, map.getCenter());
	}

	/*
	 * Set info for infoUser when browser doesn't support Geolocation
	 */
	function handleLocationError(browserHasGeolocation, infoUser, pos) {
		infoUser.setPosition(pos);
		infoUser.setContent(browserHasGeolocation ? '<div class="iw-content"><p>Błąd geolokalizacji.</p></div>'
				: '<div class="iw-content"><p>Twoja przeglądarka nie akceptuje geolokalizacji.</p></div>');
		infoUser.open(map);
	}

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
						/*
						 * Add marker to array
						 */
						markers.push(marker);

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
								+ '<p><a class="btn btn-info iw-subTitle" href="' + url + 'car/rent/' + car.carNumber
								+ '">Wypożycz:<br><b>' + car.model + '</b><br>nr auta: <b>' + car.carNumber
								+ '</b></a></p></div>';

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

setTimeout(findClosestCar, 1000);



/*
 * Find nearest marker
 */
function findClosestCar() {
	var distances = [];
	var closest = -1;
	for (i = 1; i < markers.length; i++) {

		var d = google.maps.geometry.spherical.computeDistanceBetween(markers[i].position, markers[0].position);

		distances[i] = d;

		if (closest == -1 || d < distances[closest]) {
			closest = i;
		}
	}
	/*
	 * Set bounce animation to closest marker
	 */
	markers[closest].setAnimation(google.maps.Animation.BOUNCE);
	setTimeout(function() {
		markers[closest].setAnimation(null);
	}, 20000);
	
    var start = markers[0].position;
    var end = markers[closest].position;


	/*
	 * Get route from userLocatin into nearest car
	 */
    directionsService.route({
        origin:start, 
        destination:end,
        travelMode: google.maps.TravelMode.WALKING
      }, function(result, status) {
        var dirDisp = new google.maps.DirectionsRenderer({
          map: map
        })
        dirDisp.setDirections(result)
      });
	
}





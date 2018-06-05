      

      // Initialize the map
      function initMap() {
        var map = new google.maps.Map(document.getElementById('map'), {
          zoom: 13,
          center: {
            lat: 51.10905,
            lng: 17.03107
          }
        });

       
        var url = "http://localhost:8080/CarRental/";  
      var htmlType = "GET";

      ajaxCall(htmlType)
        .done(function(cars) {
          console.log("Free cars loaded");
          console.log(cars);

          setMarkers(map)


          // Add markers to the map
          function setMarkers(map) {
        	
            var image = {
              url:  'resources/images/flag.png',
              size: new google.maps.Size(20, 32),
              origin: new google.maps.Point(0, 0),
              anchor: new google.maps.Point(0, 32)
            };

            var shape = {
              coords: [1, 1, 1, 20, 18, 20, 18, 1],
              type: 'poly'
            };

            var infowindow = new google.maps.InfoWindow();

            cars.forEach(function(car) {
                 var marker = new google.maps.Marker({
                   position: {
                     lat: car.lat,
                     lng: car.lng
                   },
                   map: map,
                   icon: image,
                   shape: shape,
                   zIndex: car.carNumber
                 });

                 google.maps.event.addListener(marker, 'mouseover', (function(marker, car) {
                   return function() {
                     var contentString = '<div>' +
                       '<p><a href="' + url + 'car/' + car.carNumber + '">' +
                       '<h6 style="text-align: center">' + car.model + '</h6></a></p>' +
                       '<p style="text-align: center">Nr auta: <b>' + car.carNumber + '</b></p>' +
                       '</div>';
                     infowindow.setContent(contentString);
                     infowindow.open(map, marker);

                   }
                 })(marker, car));
            });
            
/*             
          for (var i = 0; i < cars.length; i++) {
              var car = cars[i];
              var marker = new google.maps.Marker({
                position: {
                  lat: car[2],
                  lng: car[3]
                },
                map: map,
                icon: image,
                shape: shape,
                zIndex: car[1]
              });

              google.maps.event.addListener(marker, 'mouseover', (function(marker, i) {
                return function() {
                  var contentString = '<div>' +
                    '<p><a href="' + url + 'car/' + cars[i][1] + '">' +
                    '<h6 style="text-align: center">' + cars[i][0] + '</h6></a></p>' +
                    '<p style="text-align: center">Nr auta: <b>' + cars[i][1] + '</b></p>' +
                    '</div>';
                  infowindow.setContent(contentString);
                  infowindow.open(map, marker);

                }
              })(marker, i));

            }
            */
          }

        })
        .fail(function(statusText, e) {
          console.log("Error: " + e);
          console.log(statusText);
        })
        .always(function() {
          console.log("End of connection");
        });

      function ajaxCall(htmlType) {
        return $.ajax({
          type: htmlType,
          url: url + "car/allFreeForMap",
          headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
          },
          dataType: 'json'
        });

      };
      }

      // var cars = [
      //model, carNumber, mileage, description, user, lat, lng
      //   ['Opel Astra', 0001, 51.12125, 17.03117],
      //   ['Opel Astra', 0002, 51.11915, 17.05617],
      //   ['Opel Astra', 0003, 51.11225, 17.03627],
      //   ['Opel Astra', 0004, 51.09215, 17.03417],
      //   ['Opel Astra', 0005, 51.11905, 17.04307]
      // ];


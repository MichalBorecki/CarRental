$(document).ready(function() {
	$("#info-user").delay(500);
	$("#info-user").slideDown(1200);

	$("#info-rent").delay(3600);
	$("#info-rent").slideDown(600);

	$("#info-rents").delay(5000);
	$("#info-rents").slideDown(800);

	$("#info-cost").delay(6300);
	$("#info-cost").slideDown(800);



	var list = [];

	ajaxCall().done(function(data) {

		console.log("Data loaded");

		/*
		 * Chart for distance
		 */
		addData(data)
		function addData(data) {
			
		    var dataPoints = [];
		    var chart = new CanvasJS.Chart("chart-distance",{
		    	animationEnabled: true,
		    	theme: "light2",
		    	axisY: {
		    		title: "Dystans",
		    		titleFontSize: 16,
		    		suffix: " km",
		    	},
		       	axisX: {
		    		title: "Wypożyczenie",
		    		titleFontSize: 16
		    	},
		        data: [{
		            type: "line",
		            color: "#8eb7ba",
		            yValueFormatString: "##0 km",
		            dataPoints : dataPoints,
		        }]
		    });
			
			for (var i = 0; i < data.length; i++) {
				dataPoints.push({
					x: i+1,
					y: parseInt(data[i].distance)
				});
			}

			chart.render();

			$("#info-chart").css("width, 100%");
			
			console.log(dataPoints);
		}
        /*
         * Chart for time
         */
		$("#info-chart").delay(6000);
		addData2(data)
		function addData2(data) {
			
		    var dataPoints2 = [];
		    var chart = new CanvasJS.Chart("chart-time",{
		    	animationEnabled: true,
		    	theme: "light2",
		    	axisY: {
		    		title: "Czas",
		    		titleFontSize: 16,
		    		suffix: " min",
		    	},
		       	axisX: {
		    		title: "Wypożyczenie",
		    		titleFontSize: 16
		    	},
		        data: [{
		            type: "column",
		            color: "#8eb7ba",
		            yValueFormatString: "##0 min",
		            dataPoints : dataPoints2,
		        }]
		    });
			
			for (var i = 0; i < data.length; i++) {
				dataPoints2.push({
					x: i+1,
					y: parseInt(data[i].rentTime)
				});
			}

			chart.render();
		}





	}).fail(function(statusText, e) {
		console.log("Error: " + e);
		console.log(statusText);
	}).always(function() {
		console.log("End of connection");

	});

	function ajaxCall() {
		return $.ajax({
			type : 'GET',
			url : "http://localhost:8080/CarRental/rent/all/userInfoChart/3",
			headers : {
				'Accept' : 'application/json',
				'Content-Type' : 'application/json'
			},
			dataType : 'json'
		});

	}

});
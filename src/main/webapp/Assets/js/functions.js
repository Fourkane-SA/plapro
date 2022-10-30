function newProjectPrintState() {
	var slider = document.getElementById("state");
	var output = document.getElementById("output");
	output.innerHTML = "0";
	slider.oninput = function() {
		output.innerHTML = this.value;
	}
}

function printDateEndTask() {
	var input = document.getElementById("finish");
	var slider = document.getElementById("state");
	slider.onmouseup = function() {
		if (slider.value === "100") {
			input.innerHTML = '<div class="form-group">'
				+ '<label for="finish">Date de fin de tache</label>'
				+ '<input type="date" class="form-control" name="finish" id="finish" required>'
				+ '</div>';
		} else {
			input.innerHTML = "";
		}
	}
}

function printChartDashboard(dt, dt2) {
	window.onload = function() {

		var chart = new CanvasJS.Chart("chartContainer", {
			theme: "light2",
			exportEnabled: true,
			animationEnabled: true,
			title: {
				text: "Taches"
			},
			data: [{
				type: "pie",
				toolTipContent: "<b>{label}</b>: {y}",
				indexLabelFontSize: 16,
				indexLabel: "{label} - {y}",
				dataPoints: dt
			}]
		});
		chart.render();

		var chart2 = new CanvasJS.Chart("chartActivity", {
			title: {
				text: "Activitees"
			},
			data: [
				{
					type: "line",
					dataPoints: dt2
				}
			]
		});
		chart2.render();


	}
}

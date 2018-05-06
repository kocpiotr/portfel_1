
    function drawStatsChart(restService, width, height) {
        google.charts.load('current', {'packages': ['gauge']});
        google.charts.setOnLoadCallback(drawChart);

        function drawChart() {

            var dataArray = [
                ['Label', 'Value']
            ];

            $.ajax({
                type: "GET",
                url: restService,
                dataType: "json",
                async: false,
                success: function (data) {
                    $.each(data, function (n, elem) {
                        dataArray.push([data[n][0], data[n][1]]);
                    });
                },
                error: function (data) {
                    console.log('fucking error: ' + data)
                }
            });

            var data = google.visualization.arrayToDataTable(dataArray);

            var options = {
                width: width, height: height,
                redFrom: 90, redTo: 100,
                yellowFrom: 75, yellowTo: 90,
                greenFrom: 60, greenTo: 75,
                minorTicks: 5
            };

            var chart = new google.visualization.Gauge(document.getElementById('chart_div'));
            chart.draw(data, options);
        }
    }

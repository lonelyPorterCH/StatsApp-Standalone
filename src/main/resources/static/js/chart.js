/*
    See also: https://www.chartjs.org/docs/latest/samples/scales/time-line.html
 */
document.querySelectorAll('.line-chart').forEach(canvas => {
    const dataPoints = JSON.parse(canvas.dataset.points).map(dp => ({
        x: new Date(dp.x),
        y: parseFloat(dp.y)
    }));

    //Data
    const data = {
        datasets: [{
            label: canvas.dataset.yAxisName,
            fill: false,
            tension: 0,
            backgroundColor: "rgb(95 139 227 / 0.8)",
            borderColor: "rgb(0 119 255 / 0.75)",
            borderWidth: 2,
            pointRadius: 2,
            data: dataPoints
        }]
    };

    new Chart(canvas, {
        type: 'line',
        data: data,
        options: {
            plugins: {
                title: {
                    display: true,
                    text: canvas.dataset.title,
                    font: {size: 16}
                }
            },
            scales: {
                x: {
                    type: 'time',
                    time: {
                        // Luxon format string
                        tooltipFormat: "DD T"
                    },
                    title: {
                        display: true,
                        text: canvas.dataset.xAxisName
                    }
                },
                y: {
                    reverse: canvas.dataset.reverse === 'true',
                    title: {
                        display: true,
                        text: canvas.dataset.yAxisName
                    }
                }
            }
        }
    });
});
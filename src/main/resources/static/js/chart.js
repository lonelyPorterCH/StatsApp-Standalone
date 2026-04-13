document.querySelectorAll('.line-chart').forEach(canvas => {
    const xValues = canvas.dataset.xValues.split(',');
    const yValues = canvas.dataset.yValues.split(',');

    new Chart(canvas, {
        type: "line",
        data: {
            labels: xValues,
            datasets: [{
                fill: false,
                lineTension: 0,
                backgroundColor: "rgba(0,0,255,1.0)",
                borderColor: "rgba(0,0,255,0.1)",
                data: yValues,
                label: canvas.dataset.title
            }]
        },
        options: {
            plugins: {
                legend: {display: true},
                title: {
                    display: true,
                    text: canvas.dataset.title,
                    font: {size: 16}
                }
            },
            scales: {
                x: {
                    type: "time",
                    time: {
                        unit: "day",
                        tooltipFormat: "dd MMM yyyy",
                        displayFormats: {
                            day: "dd MMM"
                        }
                    },
                    title: {
                        display: true,
                        text: canvas.dataset.xAxisName
                    }
                },
                y: {
                    title: {
                        display: true,
                        text: canvas.dataset.yAxisName
                    }
                }
            }
        }
    });
});
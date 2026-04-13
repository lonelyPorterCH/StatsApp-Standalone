document.querySelectorAll('.line-chart').forEach(canvas => {
    const xValues = canvas.dataset.xValues.split(',');
    const yValues = canvas.dataset.yValues.split(',');

    //Data
    const data = {
        labels: xValues,
        datasets: [{
            label: canvas.dataset.title,
            fill: false,
            tension: 0,
            backgroundColor: "rgba(0,0,255,0.5)",
            borderColor: "rgba(0,0,255,1.0)",
            data: yValues
        }]
    }

    new Chart(canvas, {
        type: 'line',
        data: data,
        options: {
            plugins: {
                //Title
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
                    title: {
                        display: true,
                        text: canvas.dataset.yAxisName
                    }
                }
            }
        }
    });
});
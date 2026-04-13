document.querySelectorAll('.line-chart').forEach(canvas => {
    const xValues = canvas.dataset.xValues.split(',');
    const yValues = canvas.dataset.yValues.split(',');

    //Data
    const data = {
        labels: xValues,
        datasets: [{
            label: canvas.dataset.yAxisName,
            fill: false,
            tension: 0,
            backgroundColor: "rgb(227 76 76 / 0.8)",
            borderColor: "rgb(255 0 0 / 0.75)",
            borderWidth: 2,
            pointRadius: 2,
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
                    reverse: canvas.dataset.reverse,
                    title: {
                        display: true,
                        text: canvas.dataset.yAxisName
                    }
                }
            }
        }
    });
});
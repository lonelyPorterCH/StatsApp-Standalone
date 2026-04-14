/*
    See also: https://www.chartjs.org/docs/latest/samples/scales/time-line.html
 */
document.querySelectorAll('.line-chart').forEach(canvas => {
    const dataPoints = JSON.parse(canvas.dataset.points).map(dp => ({
        x: new Date(dp.x),
        y: parseFloat(dp.y)
    }));
    const reverse = canvas.dataset.reverse === 'true';

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
                    ticks: {
                        //Decrease number of labels
                        maxTicksLimit: 8
                    },
                    time: {
                        // Luxon format string
                        tooltipFormat: "DD"
                    },
                    title: {
                        display: true,
                        text: canvas.dataset.xAxisName
                    }
                },
                y: {
                    reverse: reverse,
                    title: {
                        display: true,
                        text: canvas.dataset.yAxisName
                    }
                }
            }
        }
    });
});

document.querySelectorAll('.chart-card').forEach(card => {
    const addBtn = card.querySelector('.add-btn');
    const form = card.querySelector('.add-form');
    const submitBtn = card.querySelector('.submit-btn');
    const canvas = card.querySelector('.line-chart');

    // toggle form on + click and hide itself
    addBtn.addEventListener('click', () => {
        form.classList.toggle('visible');
        addBtn.style.display = 'none';
    });

    // submit new data point
    submitBtn.addEventListener('click', () => {
        const x = card.querySelector('.input-x').value;
        const y = card.querySelector('.input-y').value;

        if (!x || !y) return;

        fetch(`/stats/${canvas.id}/datapoint`, {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify({x, y})
        }).then(res => {
            if (res.ok) {
                form.classList.remove('visible');
                addBtn.style.display = '';   // restore button
                location.reload();   // simplest way to refresh the chart
            }
        });
    });
});
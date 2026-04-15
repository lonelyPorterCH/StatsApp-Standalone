/**
 * When the create button is pressed, collect all data and post it to /stats
 */
document.getElementById('create-btn').addEventListener('click', () => {
    const payload = {
        id: document.getElementById('new-id').value,
        title: document.getElementById('new-title').value,
        xAxisName: document.getElementById('new-x-axis-name').value,
        yAxisName: document.getElementById('new-y-axis-name').value,
        reverse: document.getElementById('new-reverse').checked,
        dataPoints: [{
            x: document.getElementById('new-point-x').value,
            y: document.getElementById('new-point-y').value
        }]
    };

    if (!payload.id || !payload.title || !payload.dataPoints[0].x || !payload.dataPoints[0].y) return;

    console.log(JSON.stringify(payload));

    fetch('api/stats', {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(payload)
    }).then(res => {
        if (res.ok) location.reload();
    });
});
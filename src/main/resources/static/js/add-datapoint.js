/**
 * When clicking on the Add Button, it is hidden and the form is shown.
 * The submit button of the form takes the entered values and posts them to /stats/{id}/datapoint
 */
document.querySelectorAll('.chart-card').forEach(card => {
    const addBtn = card.querySelector('.add-btn');
    const form = card.querySelector('.add-form');
    const submitBtn = card.querySelector('.submit-btn');
    const canvas = card.querySelector('.line-chart');

    if (addBtn) {
        // toggle form on + click and hide itself
        addBtn.addEventListener('click', () => {
            form.classList.toggle('visible');
            addBtn.style.display = 'none';
        });
    }


    if (submitBtn) {
        // submit new data point
        submitBtn.addEventListener('click', () => {
            const x = card.querySelector('.input-x').value;
            const y = card.querySelector('.input-y').value;

            if (!x || !y) return;

            fetch(`api/stats/${canvas.id}/datapoint`, {
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
    }
});
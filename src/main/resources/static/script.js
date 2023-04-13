document.addEventListener('DOMContentLoaded', function () {
    const nonInitialCells = document.querySelectorAll('td:not(.initial-value)');

    nonInitialCells.forEach(function (cell) {
        cell.addEventListener('click', function () {
            clearSelectedCells();
            cell.classList.add('selected');
            document.getElementById('row').value = cell.getAttribute('data-row');
            document.getElementById('col').value = cell.getAttribute('data-col');
        });
    });

    const valueButtons = document.querySelectorAll('.value-btn');
    valueButtons.forEach(function (button) {
        button.addEventListener('click', function () {
            const selectedCell = document.querySelector('td.selected');
            if (selectedCell) {
                const value = button.getAttribute('data-value');
                document.getElementById('value').value = value;
                document.forms[0].submit();
            }
        });
    });
});

function clearSelectedCells() {
    const selectedCells = document.querySelectorAll('td.selected');
    selectedCells.forEach(function (cell) {
        cell.classList.remove('selected');
    });
}

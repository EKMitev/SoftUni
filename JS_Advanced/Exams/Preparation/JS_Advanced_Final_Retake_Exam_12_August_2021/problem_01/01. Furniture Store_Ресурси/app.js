window.addEventListener('load', solve);

function solve() {
    const modelEl = document.getElementById('model');
    const yearEl = document.getElementById('year');
    const descriptionEl = document.getElementById('description');
    const priceEl = document.getElementById('price');

    const addBtn = document.getElementById('add');

    addBtn.addEventListener('click', addElement)

    function addElement(e) {
        e.preventDefault();
        const model = modelEl.value;
        const year = Number(yearEl.value);
        const description = descriptionEl.value;
        const price = Number(priceEl.value);

        if (model != '' && year > 0 && description != '' && price > 0) {
            const tbody = document.getElementById('furniture-list');

            const tr = document.createElement('tr');
            tr.classList.add("info");
            tr.innerHTML =
                `<td>${model}</td>
            <td>${price.toFixed(2)}</td>
            <td>
                <button class = "moreBtn">More Info</button>
                <button class = "buyBtn">Buy it</button>
            </td>`;

            const hiddenTr = document.createElement('tr');
            hiddenTr.classList.add('hide');
            hiddenTr.innerHTML =
                `<td>Year: ${year}</td>
                <td colspan="3">Description: ${description}</td>`;

            tbody.appendChild(tr);
            tbody.appendChild(hiddenTr);

            const [moreInfoBtn, buyItBtn] = tr.querySelectorAll('button');

            moreInfoBtn.addEventListener('click', moreInfo);
            buyItBtn.addEventListener('click', buyIt);

             modelEl.value = '';
             yearEl.value = '';
             descriptionEl.value = '';
             priceEl.value = '';
        }
    }

    function moreInfo(ev) {
        const hidden = ev.target.parentElement.parentElement.nextElementSibling;
        ev.target.parentElement.parentElement.nextElementSibling
        if (ev.target.textContent == 'More Info') {
            ev.target.textContent = 'Less Info';
            hidden.style.display = 'contents';
        } else {
            ev.target.textContent = 'More Info';
            hidden.style.display = 'none';
        }
    }

    function buyIt(event) {
        const total = document.querySelector('.total-price');
        const currentEl = event.target.parentElement.parentElement.children[1]

        const currentTotal = Number(total.textContent);
        const currentPrice = Number(currentEl.textContent);

        total.textContent = (currentTotal + currentPrice).toFixed(2);

        currentEl.parentElement.nextElementSibling.remove();
        currentEl.parentElement.remove();
    }
}
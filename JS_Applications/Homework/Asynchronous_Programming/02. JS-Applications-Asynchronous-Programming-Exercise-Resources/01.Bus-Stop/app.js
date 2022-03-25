async function getInfo() {
    const stopId = document.getElementById('stopId').value;
    const url = `http://localhost:3030/jsonstore/bus/businfo/${stopId}`;

    const div = document.getElementById('stopName');
    const ul = document.getElementById('buses');

    div.replaceChildren();
    ul.replaceChildren();

    try {
        const response = await fetch(url);
        if (response.status != 200) {
            throw new Error('Error');
        }

        const data = await response.json();

        div.textContent = `${data.name}`;
        
        Object.entries(data.buses).forEach(b => {
            const li = document.createElement('li');
            li.textContent = `Bus ${b[0]} arrives in ${b[1]} minutes`;
            ul.appendChild(li);
        })
    }
    catch (err){
        div.textContent = 'Error';1288
    }
}
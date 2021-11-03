function solve() {
    const label = document.querySelector(".info");
    const departBtn = document.getElementById('depart');
    const arriveBtn = document.getElementById('arrive');

    let nextStop = {
        next: "depot"
    }

    async function depart() {
        departBtn.disabled = true;
        arriveBtn.disabled = false;

        const url = `http://localhost:3030/jsonstore/bus/schedule/${nextStop.next}`

        const res = await fetch(url);
        nextStop = await res.json();

        label.textContent = "Next stop " + nextStop.name;

    }

    function arrive() {
        departBtn.disabled = false;
        arriveBtn.disabled = true;

        label.textContent = "Arriving at " + nextStop.name;
    }

    return {
        depart,
        arrive
    };
}

let result = solve();
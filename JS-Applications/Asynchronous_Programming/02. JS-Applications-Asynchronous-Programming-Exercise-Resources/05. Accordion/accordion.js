async function solution() {

    const res = await fetch('http://localhost:3030/jsonstore/advanced/articles/list');
    const data = await res.json();

    data.forEach(async (d) => {
        const response = await fetch(`http://localhost:3030/jsonstore/advanced/articles/details/${d._id}`);
        const dataForEach = await response.json();

        const div = document.createElement('div');
        div.classList.add('accordion');

        div.innerHTML =
            `<div class="head">
            <span>${dataForEach.title}</span>
            <button class="button" id="${dataForEach.id}">More</button>
        </div>
        <div class="extra">
            <p>${dataForEach.content}</p>
        </div>`;

        const btn = div.querySelector('button');
        btn.addEventListener('click', toggle)

        document.getElementById("main").appendChild(div)
    })
}

function toggle(e) {

    const divExtra = e.target.parentElement.nextElementSibling;

    if (e.target.textContent == 'More') {
        divExtra.style.display = 'block';
        e.target.textContent = 'Less';
    } else {
        divExtra.style.display = 'none';
        e.target.textContent = 'More';
    }
}
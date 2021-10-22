function solve() {

    const addBtn = document.getElementById("add");

    addBtn.addEventListener("click", add)

    function add(e) {
        e.preventDefault();

        const task = document.getElementById("task");
        const description = document.getElementById("description");
        const date = document.getElementById("date");

        if (task.value != '' && description.value != '' && date.value != '') {
            const ar = create("article", {},
                create("h3", {}, task.value),
                create("p", {}, "Description: ", description.value),
                create("p", {}, "Due Date: ", date.value),
                create("div", { className: "flex" },
                    create("button", { className: "green" }, "Start"),
                    create("button", { className: "red" }, "Delete")));

            const sections = Array.from(document.querySelectorAll("section"));


            sections[1].children[1].appendChild(ar);

            const [startBtn, deleteBtn] = Array.from(ar.querySelectorAll("button"));
            startBtn.addEventListener('click', () => { start(ar) })
            deleteBtn.addEventListener('click', () => { deleteEl(ar) })
        }
    }
    function deleteEl(article) {
        article.remove()
    }

    function start(article) {
        const div = article.lastElementChild;
        const finishBtn = create("button", { className: "orange" }, "Finish")
        finishBtn.addEventListener('click', () => { finish(article) })

        div.firstElementChild.remove()
        div.appendChild(finishBtn);
        const sections = Array.from(document.querySelectorAll("section"));

        sections[2].children[1].appendChild(article)
    }

    function finish(article) {
        article.lastElementChild.remove();
        const sections = Array.from(document.querySelectorAll("section"));

        sections[3].appendChild(article)
    }

    function create(type, attr, ...val) {
        const el = document.createElement(type);

        for (const a in attr) {
            el[a] = attr[a]
        }

        for (let v of val) {
            if (typeof v === "string" || v === "number") {
                v = document.createTextNode(v)
            }
            el.appendChild(v);
        }

        return el;
    }
}
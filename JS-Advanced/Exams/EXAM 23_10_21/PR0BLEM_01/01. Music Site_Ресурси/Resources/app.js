window.addEventListener('load', solve);

function solve() {
    const inputs = Array.from(document.querySelectorAll('input'));
    const addBtn = document.getElementById("add-btn");

    addBtn.addEventListener('click', add)


    function add(e) {
        e.preventDefault();
        const genre = document.getElementById("genre");
        const name = document.getElementById("name");
        const author = document.getElementById("author");
        const date = document.getElementById("date");

        if (genre.value != "" && name.value != "" && author.value != "" && date.value != "") {

            const el = createEl("div", { className: "hits-info" },
                createEl("img", { src: "./static/img/img.png" }),
                createEl("h2", {}, "Genre: ", genre.value),
                createEl("h2", {}, "Name: ", name.value),
                createEl("h2", {}, "Author: ", author.value),
                createEl("h3", {}, "Date: ", date.value),
                createEl("button", { className: "save-btn" }, "Save song"),
                createEl("button", { className: "like-btn" }, "Like song"),
                createEl("button", { className: "delete-btn" }, "Delete")
            )

            const [saveBtn, likeBtn, deleteBtn] = Array.from(el.querySelectorAll("button"))

            likeBtn.addEventListener('click', ()=> {like(el)});
            saveBtn.addEventListener('click', ()=> {save(el)});
            deleteBtn.addEventListener('click', ()=> {deleteEl(el)});

            const div = document.querySelector(".all-hits-container");
            div.appendChild(el);

            genre.value = "";
            name.value = "";
            author.value = "";
            date.value = "";
        }
    }

    function deleteEl(element){
        element.remove();
    }

    function save(element){
        Array.from(element.querySelectorAll("button"))[0].remove();
        Array.from(element.querySelectorAll("button"))[0].remove();

        document.querySelector(".saved-container").appendChild(element)
    }

    function like(element){
      const likesP = document.querySelector(".likes").firstElementChild
      
        let likeNumber = Number(likesP.textContent.substring(likesP.textContent.indexOf(':') + 2))
        
        likesP.textContent = `Total Likes: ${++likeNumber}`

        Array.from(element.querySelectorAll("button"))[1].disabled = true
    }

    function createEl(type, attributes, ...value) {

        const el = document.createElement(type);



        for (const prop in attributes) {

            el[prop] = attributes[prop];

        }

        for (let item of value) {

            if (typeof item == 'string' || typeof item == 'number') {
                item = document.createTextNode(item);
            }
            el.appendChild(item);
        }
        return el;
    }
}
function lockedProfile() {
    const main = document.getElementById("main");
    main.addEventListener("click", onClick);

    function onClick(ev) {
        if (ev.target.tagName == "BUTTON") {
            const profile = ev.target.parentElement;
            const radio = profile.querySelector(
                'input[type="radio"][value = "lock"]'
            ).checked;
            if (!radio) {
                const btn = ev.target;
                const hiddenInfo = btn.previousElementSibling;
                if (btn.textContent == "Show more") {
                    hiddenInfo.style.display = "block";
                    btn.textContent = "Hide it";
                } else {
                    hiddenInfo.style.display = "";
                    btn.textContent = "Show more";
                }
            }
        }
    }

    async function getData() {
        main.replaceChildren();
        const url = 'http://localhost:3030/jsonstore/advanced/profiles';

        const res = await fetch(url);
        const data = await res.json();
        let count = 1;

        Object.values(data).forEach(p => {
            const el = document.createElement('div');
            el.classList.add("profile")

            el.innerHTML =
                `<img src="./iconProfile2.png" class="userIcon" />
         <label>Lock</label>
         <input type="radio" name="user${count}Locked" value="lock" checked>
         <label>Unlock</label>
         <input type="radio" name="user${count}Locked" value="unlock"><br>
         <hr>
         <label>Username</label>
         <input type="text" name="user1Username" value="${p.username}" disabled readonly />
         <div id="user1HiddenFields">
             <hr>
             <label>Email:</label>
             <input type="email" name="user1Email" value="${p.email}" disabled readonly />
             <label>Age:</label>
             <input type="email" name="user1Age" value="${p.age}" disabled readonly />
         </div>
         <button>Show more</button>`;

            main.appendChild(el)
            count++;
        })
    }

    getData();
}
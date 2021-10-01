function create(words) {
  const parentDiv = document.getElementById("content");
  parentDiv.addEventListener("click", showContent);

  words.forEach((e) => {
    const myDiv = document.createElement("div");
    const p = document.createElement("p");
    p.textContent = e;
    p.style.display = "none";
    myDiv.appendChild(p);

    parentDiv.appendChild(myDiv);
  });

  function showContent(e) {
    if (e.target.tagName == "DIV" && e.target != "content") {
      e.target.children[0].style.display = "";
      console.log(e.target);
    }
  }
}

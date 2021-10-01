function solve() {
  const table = document.querySelector("table.table tbody");

  const [input, output] = Array.from(document.querySelectorAll("textarea"));
  const [generateBtn, buyBtn] = Array.from(document.querySelectorAll("button"));

  generateBtn.addEventListener("click", generate);

  function generate(e) {
    const data = JSON.parse(input.value);

    for (const i of data) {
    
      const row = document.createElement("tr");

      const td1 = document.createElement("td");
      const img = document.createElement("img");
      img.setAttribute("src", i.img);
      td1.appendChild(img);

      const td2 = document.createElement("td");
      const p1 = document.createElement("p")
      p1.textContent = i.name;
      td2.appendChild(p1);

      const td3 = document.createElement("td");
      const p2 = document.createElement("p")
      p2.textContent = i.price;
      td3.appendChild(p2);

      const td4 = document.createElement("td");
      const p3 = document.createElement("p")
      p3.textContent = i.decFactor;
      td4.appendChild(p3);

      const td5 = document.createElement("td")
      const checkBox = document.createElement("input");
      checkBox.setAttribute("type", "checkbox");

      td5.appendChild(checkBox);
      row.appendChild(td1);
      row.appendChild(td2);
      row.appendChild(td3);
      row.appendChild(td4);
      row.appendChild(td5);

      table.appendChild(row);
    }
  }
}

/*
[
    {
        "img":"https://www.ikea.com/PIAimages/0447583_PE597395_S5.JPG",
        "name": "Sofa",
        "price": "259",
        "decFactor":"0.4"

    },
    {
        "img":"https://www.stylespafurniture.com/wp-content/uploads/2020/03/Cove_3_Door_Wardrobe_1.jpg",
        "name": "Wardrobe",
        "price": "120",
        "decFactor":"1.2"
    }
]
*/

function search() {
  const cities = document.getElementById("towns").children;
  const input = document.getElementById("searchText").value;

  let count = 0;
  for (let i = 0; i < cities.length; i++) {
    if (cities[i].textContent.includes(input)) {
      cities[i].style.fontWeight = "bold";
      cities[i].style.textDecoration = "underline";
      count++;
    } else {
      cities[i].style.fontWeight = "normal";
      cities[i].style.textDecoration = "";
    }
  }
  document.getElementById("result").innerHTML = `${count} matches found`;
}

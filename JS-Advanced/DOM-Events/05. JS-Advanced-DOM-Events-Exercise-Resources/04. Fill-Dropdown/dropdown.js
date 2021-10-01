function addItem() {
  const text = document.getElementById("newItemText");
  const value = document.getElementById("newItemValue");
  const option = document.createElement("option");
  option.textContent = text.value;
  option.value = value.value;

  const dropdown = document.getElementById("menu");
  dropdown.appendChild(option);
  text.value = "";
  value.value = "";
}

function create(arr) {
  const myObj = {};
  for (let index = 0; index < arr.length; index += 2) {
      myObj[arr[index]] = Number(arr[index + 1]);
  }
  console.log(myObj)
}

create(["Yoghurt", "48", "Rise", "138", "Apple", "52"]);

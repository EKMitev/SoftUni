function solve(arr) {
  let outArr = [];

  for (let i = 0; i < arr.length; i++) {
    switch (arr[i]) {
      case "add":
        outArr.push(i + 1);
        break;
      default:
        outArr.pop();
    }
  }

  let out = outArr.length == 0 ? "Empty" : outArr.join("\n");
  console.log(out);
}

solve(["add", "add", "add", "add"]);

solve(["add", "add", "remove", "add", "add"]);

solve(["remove", "remove", "remove"]);

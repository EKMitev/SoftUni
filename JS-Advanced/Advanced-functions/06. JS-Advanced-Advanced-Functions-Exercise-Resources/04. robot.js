function solve() {
  const recipes = {
    apple: { carbohydrate: 1, flavour: 2 },
    lemonade: { carbohydrate: 10, flavour: 20 },
    burger: { carbohydrate: 5, fat: 7, flavour: 3 },
    eggs: { protein: 5, fat: 1, flavour: 1 },
    turkey: { protein: 10, carbohydrate: 10, fat: 10, flavour: 10 },
  };

  const robot = {
    carbohydrate: 0,
    protein: 0,
    fat: 0,
    flavour: 0,
  };

  function engine(str) {
    let [command, ...args] = str.split(" ");

    switch (command) {
      case "restock": {
        let [microelement, quantity] = args;
        robot[microelement] += Number(quantity);
        return "Success";
      }
      case "prepare": {
        let [recipe, quantity] = args;

        const newStorage = {};

        for (const element of Object.entries(recipes[recipe])) {
          const n = robot[element[0]];

          const remaining = n - Number(recipes[recipe][element[0]]) * quantity;

          if (remaining < 0) {
            return `Error: not enough ${element[0]} in stock`;
          } else {
            newStorage[element[0]] = remaining;
          }
        }
        Object.assign(robot, newStorage);
        return "Success";
      }
      case "report": {
        return `protein=${robot.protein} carbohydrate=${robot.carbohydrate} fat=${robot.fat} flavour=${robot.flavour}`; //protein=0 carbohydrate=4 fat=3 flavour=55
      }
    }
  }

  return engine;
}

let manager = solve();

console.log(manager("restock flavour 50"));
console.log(manager("prepare lemonade 4"));
console.log(manager("restock carbohydrate 10"));
console.log(manager("restock flavour 10"));
console.log(manager("prepare apple 1"));
console.log(manager("restock fat 10"));
console.log(manager("prepare burger 1"));
console.log(manager("report"));

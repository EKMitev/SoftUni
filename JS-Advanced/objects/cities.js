function func(array) {
  const map = {};

  array.forEach((element) => {
    let [town, product, price] = element.split(" | ");
    price = Number(price);
    if (!map[product]) {
      map[product] = {};
    }
    map[product][town] = price;
  });

  for (const prod in map) {
    const sorted = Object.entries(map[prod]).sort((f, s) => f[1] - s[1]);
    console.log(`${prod} -> ${sorted[0][1]} (${sorted[0][0]})`);
  }
}

func([
  "Sample Town | Sample Product | 100",
  "Sample Town | Orange | 2",
  "Sample Town | Peach | 1",
  "Sofia | Orange | 3",
  "Sofia | Peach | 2",
  "New York | Sample Product | 100",
  "New York | Burger | 10",
]);

//Sample Product -> 1000 (Sample Town)
//Orange -> 2 (Sample Town)
//Peach -> 1 (Sample Town)
//Burger -> 10 (New York)

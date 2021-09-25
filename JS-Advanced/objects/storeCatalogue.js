function sortCatalogue(array) {
  const catalogue = {};
  for (const product of array) {
    const [prod, price] = product.split(" : ");
    const letter = prod[0];
    if (!catalogue[letter]) {
      catalogue[letter] = {};
    }
    catalogue[letter][prod] = price;
  }

  const sorted = Object.keys(catalogue).sort((f, s) =>
    f[0].localeCompare(s[0])
  );

  for (const key of sorted) {
    console.log(key);
    const products = Object.entries(catalogue[key])
      .sort((f, s) => f[0].localeCompare(s[0]));

      products.forEach(element => {
          console.log(`  ${element[0]}: ${element[1]}`)
      });
  }
}

sortCatalogue([
  "Appricot : 20.4",
  "Fridge : 1500",
  "TV : 1499",
  "Deodorant : 10",
  "Boiler : 300",
  "Apple : 1.25",
  "Anti-Bug Spray : 15",
  "T-Shirt : 10",
]);

/*
A
  Anti-Bug Spray: 15
  Apple: 1.25
  Appricot: 20.4
B
  Boiler: 300
D
  Deodorant: 10
F
  Fridge: 1500
T
  T-Shirt: 10
  TV: 1499
  */

function func(array) {
  const map = {};

  for (const data of array) {
    const splitted = data.split(" | ");

    const city = splitted[0];
    const product = splitted[1];
    const price = Number(splitted[2]);

    if (product in map){
        if (price < map[product].price){
            map[product].price = price;
            map[product].city = city;
        }
    } else{
         map[city] = {[product]: Number(price)}
    }
  }
  console.log(map);
}

func([
  "Sample Town | Sample Product | 1000",
  "Sample Town | Orange | 2",
  "Sample Town | Peach | 1",
  "Sofia | Orange | 3",
  "Sofia | Peach | 2",
  "New York | Sample Product | 1000.1",
  "New York | Burger | 10",
]);

//Sample Product -> 1000 (Sample Town)
//Orange -> 2 (Sample Town)
//Peach -> 1 (Sample Town)
//Burger -> 10 (New York)

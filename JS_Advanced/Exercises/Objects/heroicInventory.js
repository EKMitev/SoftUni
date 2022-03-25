function arrayOfHeroes(array) {
  let heroes = [];
  for (const hero of array) {
    const data = hero.split(" / ");
    const obj = {
      name: data[0],
      level: Number(data[1]),
      items: data.length == 3 ? data[2].split(", ") : [],
    };

    heroes.push(obj);
  }
  return JSON.stringify(heroes);
}

console.log(
  arrayOfHeroes([
    "Isacc / 25 / Apple, GravityGun",
    "Derek / 12 / BarrelVest, DestructionSword",
    "Hes / 1 / Desolator, Sentinel, Antara",
  ])
);

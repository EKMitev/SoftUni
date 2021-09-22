function assembleCar(car) {
  const engine = createEngine(car.power);
  car["engine"] = engine;
  delete car.power;

  car["carriage"] = createCarriage(car.carriage, car.color);
  delete car.color;

  car["wheels"] = createWheels(car.wheelsize);
  delete car.wheelsize;

  function createEngine(power) {
    const myEngine = {
      power,
      volume: 0,
    };

    if (power <= 90) {
      myEngine.volume = 1800;
      myEngine.power = 90;
    } else if (power <= 120) {
      myEngine.volume = 2400;
      myEngine.power = 120;
    } else {
      myEngine.power = 200;
      myEngine.volume = 3500;
    }

    return myEngine;
  }

  function createCarriage(carriage, color) {
    return {
      type: carriage,
      color,
    };
  }

  function createWheels(wheelsize) {
    if (wheelsize % 2 == 0) {
      wheelsize--;
    }

    return [wheelsize, wheelsize, wheelsize, wheelsize];
  }

  return car;
}

console.log(
  assembleCar({
    model: "VW Golf II",
    power: 90,
    color: "blue",
    carriage: "hatchback",
    wheelsize: 14,
  })
);

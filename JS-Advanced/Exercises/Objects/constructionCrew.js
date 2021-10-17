function modify(worker) {
  if (worker.dizziness == true) {
    const water = 0.1 * worker.experience * worker.weight;
    worker.levelOfHydrated += water;
    worker.dizziness = false;
  }
  return worker;
}

console.log(
  modify({ weight: 80, experience: 1, levelOfHydrated: 0, dizziness: true })
);

function solve() {
  document.querySelector("#btnSend").addEventListener("click", onClick);

  function onClick() {
    let arr = JSON.parse(document.querySelector("#inputs textarea").value);

    const objArr = [];

    for (let i = 0; i < arr.length; i++) {
      const name = arr[i].split(" - ")[0];
      const obj = {
        name: name,
        average: 0,
        max: 0,
      };
      const workerData = arr[i].split(" - ")[1].split(", ");
      for (let i = 0; i < workerData.length; i++) {
        const workerSalary = Number(workerData[i].split(" ")[1]);
        const workerName = workerData[i].split(" ")[0];

        obj[workerName] = workerSalary;
      }

      let average = 0;
      for (let i = 1; i < Object.values(obj).length; i++) {
        average += Object.values(obj)[i];
      }
      average /= Object.values(obj).length - 3;
      obj.average = average;
      objArr.push(obj);
    }

    objArr.sort((f, s) => s.average - f.average);

    const val = Object.values(objArr[0]);
    let max = 0;
    for (let i = 3; i < val.length; i++) {
      if (max < val[i]) {
        max = val[i];
      }
    }
    objArr[0].max = max;

    let out1 = `Name: ${
      objArr[0].name
    } Average Salary: ${objArr[0].average.toFixed(2)} Best Salary: ${objArr[0].max.toFixed(2)}`;

    let finalObj = Object.entries(objArr[0]);
    let out2 = "";
    for (let i = 3; i < finalObj.length; i++) {
      const name_ = finalObj[i][0];
      const salary = finalObj[i][1];
      out2 += `Name: ${name_} With Salary: ${salary}`;
    }
    out2 = out2.trim();
    out1 = out1.trim();

    document.querySelector("#bestRestaurant p").textContent = out1;
    document.getElementById("workers").querySelector("p").textContent = out2;
  }
}
//Name: TheLake Average Salary: 913.33 Best Salary: 1300.00
//Name: Bob With Salary: 1300 Name: Joe With Salary: 780 Name: Jane With Salary: 660

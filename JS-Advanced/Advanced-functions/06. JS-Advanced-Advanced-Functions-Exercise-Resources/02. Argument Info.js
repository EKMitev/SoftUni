function solve(...args) {
    const obj = {};
  
    for (const i of args) {
      const type = typeof i;
      if (typeof i == "object") {
        console.log(`${type}:`);
      } else {
        const value = i;
  
        console.log(`${type}: ${value}`);
      }
  
      if (!Object.keys(obj).includes(type)) {
        obj[type] = 0;
      }
      obj[type] += 1;
    }
  
    for (let i of Object.entries(obj)) {
      console.log(i[0] + " = " + i[1]);
    }
  }

solve({ name: "bob" }, 3.333, 9.999);

/*
'object:',
'number: 3.333',
'number: 9.999',
'number = 2',
'object = 1
*/

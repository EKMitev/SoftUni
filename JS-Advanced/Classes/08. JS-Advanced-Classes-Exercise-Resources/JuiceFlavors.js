function solve(arr) {
    const obj = {};
    const map = new Map();
    let counter = 1;
    for (let i = 0; i < arr.length; i++) {
        let [fruit, quantity] = arr[i].split(' => ');
        quantity = Number(quantity)


        if (!obj[fruit]) {
            obj[fruit] = [quantity, 0];
        } else {
            obj[fruit][0] += quantity;
        }

        const newBottles = parseInt(obj[fruit][0] / 1000);
        obj[fruit][0] -= newBottles * 1000;
        obj[fruit][1] += newBottles;

        if (obj[fruit][1] > 0) {
            map.set(fruit, obj[fruit][1]);
        }


    }
    
   let str = '';
    for (const[key, value] of map){
        str += key + ' => ' + value + '\n';
    }

    return str;
}

console.log(solve(['Kiwi => 234',
    'Pear => 2345',
    'Watermelon => 3456',
    'Kiwi => 4567',
    'Pear => 5678',
    'Watermelon => 6789']))


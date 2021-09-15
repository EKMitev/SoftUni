function fruit(type, weight, money) {
    let total = (weight / 1000 * money).toFixed(2)
    console.log(`I need $${total} to buy ${(weight/1000).toFixed(2)} kilograms ${type}.`)
}

fruit('orange', 2500, 1.80)
function solve(arr, order) {
    class Ticket {
        constructor(destinationName, price, status) {
            this.destination = destinationName;
            this.price = Number(price);
            this.status = status;
        }
    }
    const tickets = [];
    for (const el of arr) {
        const data = el.split('|');
        const ticket = new Ticket(data[0], data[1], data[2])
        tickets.push(ticket);
    }

    tickets.sort((f, s) => {
        if (order == 'price'){
            return f[order] - s[order];
        }
       return f[order].localeCompare(s[order])
    })
    return tickets;
}

console.log(solve(['Philadelphia|94.20|available',
'New York City|95.99|available',
'New York City|95.99|sold',
'Boston|126.20|departed'],
'price'));
class Bank {
    #bankName;
    constructor(bankName) {
        this.#bankName = bankName;
        this.allCustomers = [];
    }

    newCustomer(customer) {
        if (this.allCustomers.some(c => c.firstName === customer.firstName && c.lastName === customer.lastName && c.personalId === customer.personalId)) {
            throw new Error(`${customer.firstName} ${customer.lastName} is already our customer!`)
        }

        this.allCustomers.push(customer);

        return customer
    }

    depositMoney(personalId, amount) {
        const customer = this.allCustomers.find(c => c.personalId === personalId);

        if (!customer) {
            throw new Error("We have no customer with this ID!")
        }

        if (!customer.totalMoney){
            customer.totalMoney = 0
        }

        customer.totalMoney += amount;

        if (!Array.isArray(customer.transactions)) {
            customer.transactions = [];
        }
        customer.transactions.push(`${customer.firstName} ${customer.lastName} made deposit of ${amount}$!`);

        return `${customer.totalMoney}$`
    }

    withdrawMoney(personalId, amount) {
        const customer = this.allCustomers.find(c => c.personalId === personalId);

        if (!customer) {
            throw new Error("We have no customer with this ID!")
        }

        if (customer.totalMoney - amount < 0) {
            throw new Error(`${customer.firstName} ${customer.lastName} does not have enough money to withdraw that amount!`)
        }

        customer.totalMoney -= amount;
        customer.transactions.push(`${customer.firstName} ${customer.lastName} withdrew ${amount}$!`);

        return `${customer.totalMoney}$`
    }

    customerInfo(personalId) {
        const customer = this.allCustomers.find(c => c.personalId === personalId);

        if (!customer) {
            throw new Error("We have no customer with this ID!")
        }

        const output = [];

        output.push(`Bank name: ${this.#bankName}`)
        output.push(`Customer name:: ${customer.firstName} ${customer.lastName}`)
        output.push(`Customer ID: ${customer.personalId}`)
        output.push(`Total Money: ${customer.totalMoney}`)
        output.push(`Transactions:`)

        for (let i = customer.transactions.length -1; i >= 0; i--) {
            output.push(`${i + 1}. ${customer.transactions[i]}`)
        }

        return output.join('\n')
    }
}


let bank = new Bank('SoftUni Bank');
let customer1 = bank.newCustomer({ firstName: 'Svetlin', lastName: 'Nakov', personalId: 1111111 });
console.log(customer1.firstName)

console.log(bank.newCustomer({ firstName: 'Svetlin', lastName: 'Nakov', personalId: 6233267 }));
console.log(bank.newCustomer({ firstName: 'Mihaela', lastName: 'Mileva', personalId: 4151596 }));

bank.depositMoney(6233267, 250);
console.log(bank.depositMoney(6233267, 250));
bank.depositMoney(4151596, 555);

console.log(bank.withdrawMoney(6233267, 125));

console.log(bank.customerInfo(6233267));


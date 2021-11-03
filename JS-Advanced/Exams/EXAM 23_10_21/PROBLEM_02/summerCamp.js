class SummerCamp {
    constructor(organizer, location) {
        this.organizer = organizer,
            this.location = location;
        this.priceForTheCamp = { "child": 150, "student": 300, "collegian": 500 },
            this.listOfParticipants = [];
    }

    registerParticipant(name, condition, money) {
        if (!this.priceForTheCamp.hasOwnProperty(condition)) {
            throw new Error("Unsuccessful registration at the camp.")
        }

        if (this.listOfParticipants.some(p => p.name == name)) {
            return `The ${name} is already registered at the camp.`;
        }

        if (money < this.priceForTheCamp[condition]) {
            return `The money is not enough to pay the stay at the camp.`
        }

        const participant = {
            name: name,
            condition: condition,
            power: 100,
            wins: 0
        }

        this.listOfParticipants.push(participant)

        return `The ${name} was successfully registered.`
    }

    unregisterParticipant(name) {
        const participant = this.listOfParticipants.find(p => p.name == name)

        if (!participant) {
            throw new Error(`The ${name} is not registered in the camp.`)
        }

        this.listOfParticipants.splice(this.listOfParticipants.indexOf(participant), 1);

        return `The ${name} removed successfully.`
    }

    timeToPlay(typeOfGame, participant1, participant2) {
        if (typeOfGame == "Battleship") {
            const p1 = this.listOfParticipants.find(p => p.name == participant1)

            if (!p1) {
                throw new Error(`Invalid entered name/s.`)
            }

            p1.power += 20;
            return `The ${p1.name} successfully completed the game ${typeOfGame}.`

        } else if (typeOfGame == "WaterBalloonFights") {
            const p1 = this.listOfParticipants.find(p => p.name == participant1)
            const p2 = this.listOfParticipants.find(p => p.name == participant2)

            if (!p1 || !p2) {
                throw new Error(`Invalid entered name/s.`)
            }

            if (p1.condition != p2.condition) {
                throw new Error(`Choose players with equal condition.`)
            }

            if (p1.power == p2.power) {
                return `There is no winner.`
            }

            const arr = [p1, p2]
            arr.sort((f, s) => s.power - f.power);
            arr[0].wins++;

            return `The ${arr[0].name} is winner in the game ${typeOfGame}.`
        }
    }

    toString() {
        const output = [];

        output.push(`${this.organizer} will take ${this.listOfParticipants.length} participants on camping to ${this.location}`)

        this.listOfParticipants.sort((f, s) => s.wins - f.wins)

        for (const p of this.listOfParticipants) {
            output.push(`${p.name} - ${p.condition} - ${p.power} - ${p.wins}`)
        }

        return output.join("\n")
    }
}
const summerCamp = new SummerCamp("Jane Austen", "Pancharevo Sofia 1137, Bulgaria");
console.log(summerCamp.registerParticipant("Petar Petarson", "student", 300));
console.log(summerCamp.timeToPlay("Battleship", "Petar Petarson"));
console.log(summerCamp.registerParticipant("Sara Dickinson", "child", 200));
//console.log(summerCamp.timeToPlay("WaterBalloonFights", "Petar Petarson", "Sara Dickinson"));
console.log(summerCamp.registerParticipant("Dimitur Kostov", "student", 300));
console.log(summerCamp.timeToPlay("WaterBalloonFights", "Petar Petarson", "Dimitur Kostov"));

console.log(summerCamp.toString());

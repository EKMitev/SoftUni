const cinema = require('./cinema.js')
const { expect } = require('chai')

describe('test', () => {
    describe('test_showMovies', () => {
        it('should return specific string', () => {
            expect(cinema.showMovies([])).equal("There are currently no movies to show.")
        })

        it("should return movies", () => {
            expect(cinema.showMovies(['King Kong', 'The Tomorrow War', 'Joker'])).equal('King Kong, The Tomorrow War, Joker')
        })
    })

    describe('test_ticketPrice', () => {
        it("should return price", () => {
            expect(cinema.ticketPrice('Premiere')).equal(12.00)
            expect(cinema.ticketPrice('Normal')).equal(7.50)
            expect(cinema.ticketPrice('Discount')).equal(5.50)
        })

        it("should throw", () => {
            expect(() => { cinema.ticketPrice(null) }).throw()
            expect(() => { cinema.ticketPrice(null) }).throw()
            expect(() => { cinema.ticketPrice(69) }).throw()
        })
    })

    describe('swapSeats', () => {
        it ("should return", ()=> {
            expect(cinema.swapSeatsInHall(1, 20)).equal("Successful change of seats in the hall.")
            expect(cinema.swapSeatsInHall(5, 10)).equal("Successful change of seats in the hall.")
        })

        it ("should fail", () => {
           expect(cinema.swapSeatsInHall(1.1, 2)).equal("Unsuccessful change of seats in the hall.")
           expect(cinema.swapSeatsInHall(0, 2)).equal("Unsuccessful change of seats in the hall.")
           expect(cinema.swapSeatsInHall(21, 2)).equal("Unsuccessful change of seats in the hall.")
           expect(cinema.swapSeatsInHall(1, 2.1)).equal("Unsuccessful change of seats in the hall.")
           expect(cinema.swapSeatsInHall(1, 0)).equal("Unsuccessful change of seats in the hall.")
           expect(cinema.swapSeatsInHall(1, 21)).equal("Unsuccessful change of seats in the hall.")

        })
    })
})


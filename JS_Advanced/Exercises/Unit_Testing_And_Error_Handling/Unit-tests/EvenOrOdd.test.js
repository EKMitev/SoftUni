const { isOddOrEven } = require('/home/emiliyan/GitHub/Softuni-Java/JS-Advanced/Unit-Testing-and-Error-Handling/Unit-tests/EvenOrOdd.js');
const { expect } = require("chai");

describe("EvenOrOdd", () => {
  it("should return undefined", () => {
    expect(isOddOrEven(1)).to.be.undefined;
  });
  it("should return odd", () => {
    expect(isOddOrEven('asd')).to.equal("odd");
  });
  it("should return even", () => {
    expect(isOddOrEven("as")).to.equal("even");
  });
});

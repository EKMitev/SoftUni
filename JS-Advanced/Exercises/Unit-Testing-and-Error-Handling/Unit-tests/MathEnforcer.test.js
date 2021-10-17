const mathEnforcer = require("./MathEnforcer");
const { expect } = require("chai");

describe("MathEnforcer", () => {
  describe("addFive", () => {
    it("should return undefined", () => {
      expect(mathEnforcer.addFive("0")).to.be.undefined;
    });

    it("should return 5", () => {
      expect(mathEnforcer.addFive(0)).equal(5);
    });

    it("should return 4", () => {
      expect(mathEnforcer.addFive(-1)).equal(4);
    });

    it("should return 5", () => {
      expect(mathEnforcer.addFive(0.01)).closeTo(5, 0.01);
    });
  });

  describe("subtractTen", () => {
    it("should return undefined", () => {
      expect(mathEnforcer.subtractTen("0")).to.be.undefined;
    });

    it("should return 5", () => {
      expect(mathEnforcer.subtractTen(15)).equal(5);
    });

    it("should return -11", () => {
      expect(mathEnforcer.subtractTen(-1)).equal(-11);
    });

    it("should return 0", () => {
      expect(mathEnforcer.subtractTen(10.01)).closeTo(0, 0.01);
    });
  });

  describe("sum", () => {
    it("should return undefined param 1 invalid", () => {
      expect(mathEnforcer.sum("0", 1)).to.be.undefined;
    });

    it("should return undefined param 2 invalid", () => {
      expect(mathEnforcer.sum(1, "0")).to.be.undefined;
    });

    it("should return 2", () => {
      expect(mathEnforcer.sum(1, 1)).equal(2);
    });

    it("should return -3", () => {
      expect(mathEnforcer.sum(-1, -2)).equal(-3);
      expect(mathEnforcer.sum(1, -4)).equal(-3);
      expect(mathEnforcer.sum(-4, 1)).equal(-3);
    });

    it("should return 5", () => {
      expect(mathEnforcer.sum(2.01, 3)).closeTo(5, 0.01);
    });
  });
});

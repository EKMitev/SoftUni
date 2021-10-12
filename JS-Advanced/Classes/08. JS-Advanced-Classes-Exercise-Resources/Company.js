class Company {
    constructor() {
        this.departments = {};
    }

    addEmployee(name, salary, position, department) {
        salary = Number(salary);
        if  (!name || !position || !department || !salary || salary <= 0) {
            throw new Error("Invalid input!");
        }

        if (this.departments[department] == undefined) {
            this.departments[department] = []
        }
        this.departments[department].push([name, salary, position])
        return `New employee is hired. Name: ${name}. Position: ${position}`
    }

    bestDepartment() {

        let department = '';
        let maxSalary = 0;
        Object.entries(this.departments).forEach(([key, value]) => {
            let salary = 0;

            value.forEach(e => {
                salary += e[1];
            })
            salary = salary / value.length;
            if (salary > maxSalary) {
                department = key;
                maxSalary = salary;
            }
        });
        if (department != '') {
            let res = `Best Department is: ${department}\nAverage salary: ${maxSalary.toFixed(2)}\n`;
            Object.values(this.departments[department]).sort((f, s) => {
                if (s[1] - f[1] == 0) {
                    return f[0].localeCompare(s[0])
                }
                return s[1] - f[1]
            }).forEach(e => {
                let em = `${e[0]} ${e[1]} ${e[2]}\n`;
                res += em;
            })
            return res.trim();
        }
    }
}

let c = new Company();
c.addEmployee("Stanimir", 2000, "engineer", "Construction");
c.addEmployee("Pesho", 1500, "electrical engineer", "Construction");
c.addEmployee("Slavi", 500, "dyer", "Construction");
c.addEmployee("Stan", 2000, "architect", "Construction");
c.addEmployee("Stanimir", 1200, "digital marketing manager", "Marketing");
c.addEmployee("Pesho", 1000, "graphical designer", "Marketing");
c.addEmployee("Gosho", 1350, "HR", "Human resources");
console.log(c.bestDepartment());
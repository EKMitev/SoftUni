#ex1
SELECT * FROM `departments`;

#ex2
SELECT `name` FROM `departments`;

#ex3
SELECT `first_name`, `last_name`, `salary` FROM `employees`;

#ex4
SELECT  `first_name`, `middle_name`, `last_name` FROM `employees`;

#ex5
SELECT concat(`first_name`, ".", `last_name`, "@softuni.bg") 
AS `full_email_address`
FROM `employees`;

#ex6
SELECT DISTINCT `salary`
FROM `employees`;

#ex7
SELECT * FROM `employees` 
WHERE `job_title` = "Sales Representative";

#ex8
SELECT `first_name`, `last_name`, `job_title` FROM `employees`
WHERE `salary` >= 20000 and `salary` <= 30000
ORDER BY `employee_id` ASC;

#ex9
SELECT concat_ws(" ", `first_name`, `middle_name`, `last_name`)
AS `Full Name`
FROM `employees`
WHERE `salary` = 25000 OR `salary` = 14000  OR `salary` = 12500 OR `salary` = 23600;

#ex10
SELECT `first_name`, `last_name` 
FROM `employees`
WHERE `manager_id` IS NULL;

#ex11
SELECT `first_name`, `last_name`, `salary`
FROM `employees`
WHERE `salary` > 50000
ORDER BY `salary` DESC;

#ex12
SELECT `first_name`, `last_name`
FROM `employees`
ORDER BY `salary` DESC
LIMIT 5;

#ex13
SELECT `first_name`, `last_name`
FROM `employees`
WHERE `department_id` != 4;

#ex14
SELECT * FROM `employees`
ORDER BY `salary` DESC, `first_name` ASC, `last_name` DESC, `middle_name` ASC;

#ex15
CREATE VIEW `v_employees_salaries` AS
SELECT `first_name`, `last_name`, `salary`
FROM `employees`;

#ex16
CREATE VIEW v_employees_job_titles AS 
SELECT concat_ws(" ", `first_name`, `middle_name`, `last_name`)
AS `Full Name`,
ifnull(job_title, " ") AS job_title
FROM employees;

#ex17
SELECT DISTINCT job_title 
FROM employees 
ORDER BY job_title ASC;

#ex18
SELECT * FROM projects
ORDER BY start_date, `name`
LIMIT 10;

#ex19
SELECT first_name, last_name, hire_date 
FROM employees
ORDER BY hire_date desc
LIMIT 7;

#ex20
UPDATE employees
SET salary = salary * 1.1200
WHERE department_id = 1 or department_id = 2 or department_id = 4 or department_id = 11;
SELECT salary FROM employees;









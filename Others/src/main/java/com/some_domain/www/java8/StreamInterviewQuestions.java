package com.some_domain.www.java8;

import java.util.*;
import java.util.stream.Collectors;

public class StreamInterviewQuestions {
    static List<Employee> employeeList = new ArrayList<>();

    public static void main(String[] args) {

        StreamInterviewQuestions instance = new StreamInterviewQuestions();


        employeeList.add(new Employee(100, "seshu", 25, "MALE", "HR", 2015, 1000.0));
        employeeList.add(new Employee(101, "srinu", 35, "MALE", "Finance", 2011, 10220.0));
        employeeList.add(new Employee(102, "santi", 15, "FEMALE", "Account", 2012, 10233.22));
        employeeList.add(new Employee(103, "Asraar", 32, "MALE", "Development", 2013, 1200.33));
        employeeList.add(new Employee(104, "Venki", 36, "MALE", "coding", 2014, 102220.222));
        employeeList.add(new Employee(105, "sumati", 22, "FEMALE", "sales", 2015, 1044320.0));
        employeeList.add(new Employee(106, "sarala", 33, "FEMALE", "HR", 2016, 10444.0));
        employeeList.add(new Employee(107, "pushpa", 44, "MALE", "sales", 2015, 234000.0));
        employeeList.add(new Employee(108, "revanth", 54, "MALE", "coding", 2014, 14400.0));
        employeeList.add(new Employee(109, "rani", 25, "FEMALE", "Development", 2015, 1044.0));
        employeeList.add(new Employee(110, "reddy", 54, "MALE", "Account", 2013, 10440.44));
        employeeList.add(new Employee(111, "geethu", 22, "FEMALE", "Finance", 2012, 223400.44));
        employeeList.add(new Employee(112, "sathya", 25, "MALE", "Finance", 2011, 22200.0));
        employeeList.add(new Employee(113, "vasanthi", 35, "FEMALE", "HR", 2010, 1020.0));
        employeeList.add(new Employee(114, "adi", 67, "MALE", "sales", 2010, 1020.0));
        employeeList.add(new Employee(115, "kavitha", 23, "FEMALE", "HR", 2017, 2000.0));
        employeeList.add(new Employee(116, "Asif", 24, "MALE", "HR", 2009, 1000.0));


        //Q:1 How many male and female Employees are there in the organization
        instance.countOfMaleAndFemaleEmployees();
        //Q:2 Print the name of all departments in the organization
        printDepartmentNames();
        //Q:3 What is the average age of male and female employees in the organization
        question3();
        //Q:4 Get the details of highest paid in the organization
        question4();
        //Q:5 Get the names of employees who have joined after 2012 in the organization
        question5();
        //Q:6 count the no of employees each department in the organization
        question6();
        //Q:7 what is the average salary of each department in the organization
        question7();
        //Q:8 Get the details of the youngest male employee in the HR department in the organization
        question8();
        //Q:9 who has the most working experience in the organization
        question9();
        //Q:10 how many male and female employees in the sales department team in the organization
        /*question10();
        //Q:11 what is the average salary of male and female employees in the organization
        question11();
        //Q:12 list down the names of all employees each department in the organization
        question12();
        //Q:13 what is average salary and total salary in the whole organization
        question13();
        //Q:14 Separate the employees who are younger or equal to 25 years from those employees who are older than 25 years
        question14();
        //Q:15 who is the oldest employee in the organization? what is his age and which department
        question15();*/
    }

    private void countOfMaleAndFemaleEmployees() {
        System.out.println("Count of male and female employees in organization");
        Map<String, Long> map = employeeList.stream()
                .collect(Collectors.groupingBy(i -> i.getGender(), Collectors.counting()));
        map.entrySet().stream().forEach(entry -> {
            System.out.println(entry);
        });
    }

    private static void printDepartmentNames() {
        System.out.println("Print the name of all departments in the organization");
        List<String> departments = employeeList.stream()
                .map(emp -> emp.getDepartment())
                .distinct()
                .collect(Collectors.toList());
        System.out.println(departments);
    }

    private static void question3() {
        System.out.println("Average age of male and female employees in the organization");
        Map<String, Double> map = employeeList.stream()
                .collect(Collectors.groupingBy(i -> i.getGender(), Collectors.averagingDouble(i -> i.getAge())));
        System.out.println(map);
    }

    private static void question4() {
        System.out.println("Get the details of highest paid employee in the organization");
        Optional<Employee> employeeOptional = employeeList.stream()
                .sorted(Comparator.comparing(i -> i.getSalary(), Comparator.reverseOrder()))
                .findFirst();
        if (employeeOptional.isPresent()) {
            System.out.println(employeeOptional.get().getName());
        }
    }

    private static void question5() {
        System.out.println("Get the names of employees who have joined after 2012 in the organization");
        List<String> employeeNames = employeeList.stream()
                .filter(i -> i.getYearOfJoining() > 2012)
                .map(e -> e.getName())
                .collect(Collectors.toList());
        System.out.println(employeeNames);
    }

    private static void question6() {
        System.out.println("count the no of employees each department in the organization");
        Map<String, Long> map = employeeList.stream()
                .collect(Collectors.groupingBy(i -> i.getDepartment(), Collectors.counting()));
        System.out.println(map);
    }

    private static void question7() {
        System.out.println("Average salary of each department in the organization");
        Map<String, Double> map = employeeList.stream()
                .collect(Collectors.groupingBy(i -> i.getDepartment(), Collectors.averagingDouble(i -> i.getSalary())));
        System.out.println(map);
    }

    private static void question8() {
        System.out.println("Details of youngest male employee in the HR department in the organization");
        Optional<Employee> optionalEmployee = employeeList.stream()
                .filter(i -> i.getDepartment().equalsIgnoreCase("HR"))
                .filter(i -> i.getGender().equalsIgnoreCase("MALE"))
                .sorted(Comparator.comparingInt(i -> i.getAge())).findFirst();
        if (optionalEmployee.isPresent()) {
            System.out.println(optionalEmployee.get());
        }
    }

    private static void question9() {
        System.out.println("who has the most and least working experience in the organization");

        Optional<Employee> optionalEmployee = employeeList.stream()
                .sorted(Comparator.comparing(i -> i.getYearOfJoining(), Comparator.reverseOrder()))
                .findFirst();
        if (optionalEmployee.isPresent()) {
            System.out.println(optionalEmployee.get());
        }

        optionalEmployee = employeeList.stream()
                .sorted(Comparator.comparingInt(i -> i.getYearOfJoining()))
                .findFirst();
        if (optionalEmployee.isPresent()) {
            System.out.println(optionalEmployee.get());
        }
    }

    private static void question10() {
        System.out.println("how many male and female employees in the sales department team in the organization");
        employeeList.stream().filter(e -> e.getDepartment().equalsIgnoreCase("sales"))
                .collect(Collectors.groupingBy(Employee::getGender, Collectors.counting())).entrySet().stream().forEach(System.out::println);
    }

    private static void question11() {
        System.out.println("what is the average salary of male and female employees in the organization");
        employeeList.stream().
                collect(Collectors.groupingBy(Employee::getGender, Collectors.averagingDouble(Employee::getSalary))).
                entrySet().stream().forEach(System.out::println);
    }

    private static void question12() {
        System.out.println("list down the names of all employees each department in the organization");
        employeeList.stream().
                collect(Collectors.groupingBy(Employee::getDepartment)).entrySet().stream().forEach(System.out::println);
    }

    private static void question13() {
        System.out.println("what is average salary and total salary in the whole organization");
        DoubleSummaryStatistics summaryStatistics = employeeList.stream().
                collect(Collectors.summarizingDouble(Employee::getSalary));
        System.out.println("average salary" + summaryStatistics.getAverage());
        System.out.println("total salary" + summaryStatistics.getSum());
    }

    private static void question14() {
        System.out.println("Separate the employees who are younger or equal to 25 years from those employees who are older than 25 years");
        Map<Boolean, List<Employee>> emp = employeeList.stream().collect(Collectors.partitioningBy(e -> e.getAge() > 25));
        Set<Map.Entry<Boolean, List<Employee>>> entrySet = emp.entrySet();
        for (Map.Entry<Boolean, List<Employee>> entry : entrySet) {
            if (entry.getKey()) {
                System.out.println("Employees older than 25 years");
            } else {
                System.out.println("Employees younger than or equal to 25 years");
            }
            entry.getValue().stream().forEach(System.out::println);
        }
    }

    private static void question15() {
        System.out.println("who is the oldest employee in the organization? what is his age and which department");
        /*  employeeList.stream().max(Comparator.comparingInt(Employee::getAge)).stream().forEach(System.out::println);*/
    }
}

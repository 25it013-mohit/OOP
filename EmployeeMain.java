abstract class Employee {
    private final String id;
    private final String name;

    public Employee(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public abstract double monthlySalary();
}

class FullTime extends Employee {
    private final double salary;

    public FullTime(String id, String name, double salary) {
        super(id, name);
        this.salary = salary;
    }

    @Override
    public double monthlySalary() {
        return salary;
    }
}

class PartTime extends Employee {
    private final double hoursWorked;
    private final double hourlyRate;

    public PartTime(String id, String name, double hoursWorked, double hourlyRate) {
        super(id, name);
        this.hoursWorked = hoursWorked;
        this.hourlyRate = hourlyRate;
    }

    @Override
    public double monthlySalary() {
        return hoursWorked * hourlyRate;
    }
}

class Intern extends Employee {
    private final double stipend;

    public Intern(String id, String name, double stipend) {
        super(id, name);
        this.stipend = stipend;
    }

    @Override
    public double monthlySalary() {
        return stipend;
    }
}

public class EmployeeMain {
    public static void main(String[] args) {
        Employee[] employees = new Employee[] {
            new FullTime("FT-101", "Alice Vance", 6500.00),
            new PartTime("PT-201", "Bob Smith", 80.0, 35.00),
            new Intern("IN-301", "Charlie Day", 1800.00),
            new FullTime("FT-102", "Diana Prince", 7200.00),
            new Intern("IN-302", "Evan Wright", 2000.00)
        };

        double totalPayroll = 0.0;

        System.out.printf("%-8s | %-16s | %-12s | %-20s%n", "ID", "Name", "Salary ($)", "Notes");
        System.out.println("------------------------------------------------------------------");

        for (Employee emp : employees) {
            double salary = emp.monthlySalary();
            totalPayroll += salary;

            String note = "";
            if (emp instanceof Intern) {
                note = "[Fixed Stipend - Tax Exempt]";
            }

            System.out.printf("%-8s | %-16s | %-12.2f | %-20s%n", 
                emp.getId(), emp.getName(), salary, note);
        }

        System.out.println("------------------------------------------------------------------");
        System.out.printf("Total Monthly Payroll: $%.2f%n", totalPayroll);
    }
}
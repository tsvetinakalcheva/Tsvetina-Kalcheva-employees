package emp;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAmount;

/**
 * Employee
 */
public class Employee {
  private String employeeId;
  private String projectId;
  private LocalDate dateFrom;
  private LocalDate dateTo;

  private Employee(EmployeeBuilder builder) {
    this.employeeId = builder.employeeId;
    this.projectId = builder.projectId;
    this.dateFrom = builder.dateFrom;
    this.dateTo = builder.dateTo;
  }

  public String getEmployeeId() {
    return employeeId;
  }

  public String getProjectId() {
    return projectId;
  }

  public LocalDate getDateFrom() {
    return dateFrom;
  }

  public LocalDate getDateTo() {
    return dateTo;
  }

  public static class EmployeeBuilder {
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private String employeeId;
    private String projectId;
    private LocalDate dateFrom;
    private LocalDate dateTo;

    public EmployeeBuilder addEmployeeId(String employeeId) {
      this.employeeId = employeeId;
      return this;
    }

    public EmployeeBuilder addProjectId(String projectId) {
      this.projectId = projectId;
      return this;
    }

    public EmployeeBuilder addDateFrom(String dateFrom) {
      if (dateFrom == null || dateFrom.isEmpty() || dateFrom.equalsIgnoreCase("NULL")) {
        this.dateFrom = LocalDate.now();
      } else {
        this.dateFrom = LocalDate.parse(dateFrom, formatter);
      }
      return this;
    }

    public EmployeeBuilder addDateTo(String dateTo) {
      if (dateTo == null || dateTo.isEmpty() || dateTo.equalsIgnoreCase("NULL")) {
        this.dateTo = LocalDate.now();
      } else {
        this.dateTo = LocalDate.parse(dateTo, formatter);
      }
      return this;
    }

    public Employee build() {
      return new Employee(this);
    }
  }
}

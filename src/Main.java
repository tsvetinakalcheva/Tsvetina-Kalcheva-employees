import emp.Employee;
import emp.EmployeePair;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

  public static void main(String[] args) throws Exception {
    String delimiter = ",";
    if(args.length<1){
      throw new Exception("Please provide path to the csv file");
    }
    String path = args[0];

    Map<String, List<Employee>> employeesMap = new HashMap<>();

    try (FileReader filereader = new FileReader(path);
        BufferedReader reader = new BufferedReader(filereader)) {

      String line = reader.readLine();
      String[] token;

      while (line != null) {
        token = line.split(delimiter);
        Employee employee = new Employee.EmployeeBuilder()
            .addEmployeeId(token[0])
            .addProjectId(token[1])
            .addDateFrom(token[2])
            .addDateTo(token[3])
            .build();

        if (employeesMap.get(token[1]) == null) {
          List<Employee> list = new ArrayList<>();
          list.add(employee);
          employeesMap.put(token[1], list);
        } else {
          employeesMap.get(token[1]).add(employee);
        }
        line = reader.readLine();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

    employeesMap.forEach((projectId, emp) -> {
      results(emp);
    });

  }

  /**
   * Method printing the pair of employees that have worked longest on a project and their time.
   * @param employees all employees that have worked on a project together
   */
  private static void results(List<Employee> employees) {
    List<EmployeePair> pairs = new ArrayList<>();
    for (Employee e1 : employees) {
      for (Employee e2 : employees) {
        if(!e2.getEmployeeId().equals(e1.getEmployeeId())) {
          EmployeePair employeePair = new EmployeePair();
          employeePair.setFirstEmp(e1);
          employeePair.setSecondEmp(e2);
          employeePair.setDaysWorkingTogether(pairEmployee(e1, e2));
          pairs.add(employeePair);
        }
      }
    }
    Collections.sort(pairs, Collections.reverseOrder());
    System.out.println(pairs.get(0).toString());
  }

  /**
   * @param e1 First employee
   * @param e2 Second employee
   * @return the time they have worked together on the same project
   */
  private static long pairEmployee(Employee e1, Employee e2) {
    long days = 0;
    if(e1.getDateFrom().isAfter(e2.getDateTo()) || e2.getDateFrom().isAfter(e1.getDateTo())){
      return days;
    }
    if (e1.getDateTo().isBefore(e2.getDateTo())) {
      days = ChronoUnit.DAYS.between(e2.getDateFrom(), e1.getDateTo());
    } else {
      days = ChronoUnit.DAYS.between(e1.getDateFrom(), e2.getDateTo());
    }
    return days;
  }
}


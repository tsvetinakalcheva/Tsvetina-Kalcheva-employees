package sirma.employee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import sirma.employee.employees.Employee;
import sirma.employee.employees.EmployeePair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;


@Service
public class EmployeeServiceImpl implements EmployeeService {
    public static final String DELIMITER = ",";
    private final Path rootLocation;
    private final Map<String, List<Employee>> employeesMap = new HashMap<>();

    @Autowired
    public EmployeeServiceImpl(EmployeeProperties properties) {
        this.rootLocation = Paths.get(properties.getLocation());
    }

    /**
     * store method reads the file and save the data in Map
     * @param file
     */
    @Override
    public void store(MultipartFile file) {

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(file.getInputStream()))) {

            String line = reader.readLine();
            String[] token;

            while (line != null) {
                token = line.split(DELIMITER);
                Employee employee = Employee.builder()
                        .employeeId(Integer.valueOf(token[0]))
                        .projectId(Integer.valueOf(token[1]))
                        .dateFrom(LocalDate.parse(token[2]))
                        .dateTo(token[3].equalsIgnoreCase("null") ? LocalDate.now() : LocalDate.parse(token[3]))
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
    }

    @Override
    public Path load(String filename) {
        return rootLocation.resolve(filename);
    }

    /**
     * Returns a list of output data
     * @return
     */
    @Override
    public List<EmployeePair> getPairs() {
        return this.employeesMap.entrySet().stream().flatMap(entry ->
                results(entry.getValue()).stream()).toList();
    }


    private List<EmployeePair> results(List<Employee> employees) {
        List<EmployeePair> pairs = new ArrayList<>();
        for (Employee e1 : employees) {
            for (Employee e2 : employees) {
                long daysWorkingTogether = pairEmployee(e1, e2);
                if (e1.getEmployeeId() < e2.getEmployeeId() && daysWorkingTogether > 0) {
                    pairs.add(EmployeePair.builder()
                            .firstEmp(e1)
                            .secondEmp(e2)
                            .daysWorkingTogether(daysWorkingTogether)
                            .build());
                }
            }
        }
        pairs.sort(Collections.reverseOrder());
        return pairs;
    }


    private long pairEmployee(Employee e1, Employee e2) {
        long days = 0;
        if (e1.getDateFrom().isAfter(e2.getDateTo()) || e2.getDateFrom().isAfter(e1.getDateTo())) {
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

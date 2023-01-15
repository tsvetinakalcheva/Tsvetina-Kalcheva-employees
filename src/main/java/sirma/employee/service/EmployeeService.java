package sirma.employee.service;

import org.springframework.web.multipart.MultipartFile;
import sirma.employee.employees.EmployeePair;

import java.nio.file.Path;
import java.util.List;

public interface EmployeeService {
    void store(MultipartFile file);

    Path load(String filename);

    List<EmployeePair> getPairs();

  ;


}

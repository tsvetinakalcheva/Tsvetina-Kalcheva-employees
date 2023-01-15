package sirma.employee.employees;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class Employee {
    @NotNull
    private Integer employeeId;
    @NotNull
    private Integer projectId;
    @Past
    @NotNull
    @DateTimeFormat
    private LocalDate dateFrom;
    @DateTimeFormat
    private LocalDate dateTo;

}

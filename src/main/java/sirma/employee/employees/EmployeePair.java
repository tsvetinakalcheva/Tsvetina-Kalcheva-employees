package sirma.employee.employees;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.NonNull;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class EmployeePair implements Comparable<EmployeePair> {
    @NonNull
    private Employee firstEmp;
    @NonNull
    private Employee secondEmp;
    private long daysWorkingTogether;

    @Override
    public int compareTo(EmployeePair otherPair) {
        if (this.getDaysWorkingTogether() == otherPair.getDaysWorkingTogether()) {
            return 0;
        } else {
            return this.getDaysWorkingTogether() > otherPair.getDaysWorkingTogether() ? 1 : -1;
        }
    }

}

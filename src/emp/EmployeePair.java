package emp;


/**
 * Helper class to store a pair of employees and their time
 */
public class EmployeePair implements Comparable<EmployeePair>{
  private Employee firstEmp;
  private Employee secondEmp;
  private long daysWorkingTogether;

  public Employee getFirstEmp() {
    return firstEmp;
  }

  public void setFirstEmp(Employee firstEmp) {
    this.firstEmp = firstEmp;
  }

  public Employee getSecondEmp() {
    return secondEmp;
  }

  public void setSecondEmp(Employee secondEmp) {
    this.secondEmp = secondEmp;
  }

  public long getDaysWorkingTogether() {
    return daysWorkingTogether;
  }

  public void setDaysWorkingTogether(long daysWorkingTogether) {
    this.daysWorkingTogether = daysWorkingTogether;
  }

  @Override
  public int compareTo(EmployeePair otherPair) {
    if(getDaysWorkingTogether()==otherPair.getDaysWorkingTogether())
      return 0;
    else if(getDaysWorkingTogether()>otherPair.getDaysWorkingTogether())
      return 1;
    else
      return -1;
  }

  @Override
  public String toString() {
    return String.join(", ", firstEmp.getEmployeeId(), secondEmp.getEmployeeId(), getDaysWorkingTogether()+"");
  }
}

package sirma.employee.exceptions;


public class EmployeeFileNotFoundException extends EmployeeException {
    public EmployeeFileNotFoundException(String message) {
        super(message);
    }

    public EmployeeFileNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}

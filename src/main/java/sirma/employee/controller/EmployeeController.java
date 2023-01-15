package sirma.employee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sirma.employee.employees.EmployeePair;
import sirma.employee.exceptions.EmployeeFileNotFoundException;
import sirma.employee.service.EmployeeService;

import java.util.List;


@Controller
public class EmployeeController {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/")
    public String listUploadedFiles(Model model) {
        List<EmployeePair> pairs = employeeService.getPairs();
        model.addAttribute("pairs", pairs);
        return "uploadForm";
    }


    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes, Model model) {

        employeeService.store(file);
        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");

        return "redirect:/";
    }

    @ExceptionHandler(EmployeeFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(EmployeeFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }
}



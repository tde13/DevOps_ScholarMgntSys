package net.javaguides.sms.controller;

import net.javaguides.sms.Config.CustomUserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import net.javaguides.sms.entity.Student;
import net.javaguides.sms.service.StudentService;

import java.security.Principal;

@Controller
public class StudentController {
	
	private StudentService studentService;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;


	public StudentController(StudentService studentService) {
		super();
		this.studentService = studentService;
	}
	
	// handler method to handle list students and return mode and view
	@GetMapping("/list")
	public String listStudents(Model model) {
		model.addAttribute("students", studentService.getAllStudents());
		return "students";
	}
	
	@GetMapping("/students/new")
	public String createStudentForm(Model model) {
		
		// create student object to hold student form data
		Student student = new Student();
		model.addAttribute("student", student);
		return "create_student";
		
	}
	
	@PostMapping("/students")
	public String saveStudent(@ModelAttribute("student") Student student) {
		student.setRole("ROLE_USER");
		student.setPassword(passwordEncoder.encode("1234"));
		studentService.saveStudent(student);
		return "redirect:/list";
	}
	
	@GetMapping("/user/students/edit/{id}")
	public String editStudentForm(@PathVariable Long id, Model model) {
		CustomUserDetail user = null;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			String currentUserName = authentication.getName();
		}
		Object myUser = (authentication != null) ? authentication.getPrincipal() :  null;
		if (myUser instanceof CustomUserDetail) {
			user = (CustomUserDetail) myUser;
		}
		if (user.getUser().getId() == id || user.getUser().getRole().equals("ROLE_ADMIN")){
			model.addAttribute("student", studentService.getStudentById(id));
			return "edit_student";
		}
		else
			return "error";
	}

//	@GetMapping("/user/students/edit/{id}")
//	public String editStudentForm(@PathVariable Long id, Model model, Principal principal) {
//		String username = principal.getName();
//		boolean hasOwnership = studentService.checkOwnership(username, id);
//		if(hasOwnership) {
//			model.addAttribute("student", studentService.getStudentById(id));
//			return "edit_student";
//		}
//		else
//			return "error";
//
//	}

	@PostMapping("/edit/{id}")
	public String updateStudent(@PathVariable Long id,
			@ModelAttribute("student") Student student,
			Model model) {
		
		// get student from database by id
		Student existingStudent = studentService.getStudentById(id);
//		existingStudent.setId(id);
		existingStudent.setFirstName(student.getFirstName());
		existingStudent.setLastName(student.getLastName());
		existingStudent.setEmail(student.getEmail());
		existingStudent.setCurrentOrg(student.getCurrentOrg());
		existingStudent.setPhone(student.getPhone());
		existingStudent.setAddress(student.getAddress());
		existingStudent.setBatch(student.getBatch());
		existingStudent.setSchoolName(student.getSchoolName());
		existingStudent.setDegree(student.getDegree());
		existingStudent.setOrgRole(student.getOrgRole());
		
		// save updated student object
		studentService.updateStudent(existingStudent);
		return "redirect:/list";
	}
	
	// handler method to handle delete student request
	
	@GetMapping("/students/{id}")
	public String deleteStudent(@PathVariable Long id) {
		studentService.deleteStudentById(id);
		return "redirect:/list";
	}
	
}

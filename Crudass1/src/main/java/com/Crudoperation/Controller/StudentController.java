package com.Crudoperation.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Crudoperation.DTO.AddressDTO;
import com.Crudoperation.DTO.ResultDTIO;
import com.Crudoperation.DTO.StudentDTO;
import com.Crudoperation.Entity.Student;
import com.Crudoperation.Service.StudentService;

@RestController
@RequestMapping("/Student")
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	@PostMapping("/saveStudent")
	public StudentDTO addStudent(@RequestBody StudentDTO studentDTO) {
		return studentService.addStudent(studentDTO);
	}
	@GetMapping("/get")
	private StudentDTO getStudentById(@RequestParam Long id) {
		return studentService.getStudentById(id);
	}
	@GetMapping("/getAll")
	private  List<Student> getStudentById(){
		return studentService.getAllStudents();
	}
	@PutMapping ("/update/{id}")
	private StudentDTO updateStudent(@RequestBody StudentDTO studentDTO) {
		return studentService.updateStudent(studentDTO);
		}
	@DeleteMapping("/delete/{id}")
	private String deleteStudent(@PathVariable Long id) {
		return studentService.deleteStudent(id);
	}
	@PutMapping("/status")
	private String updateStudentStatus(@RequestParam String status, @RequestParam Long id) {
		studentService.updateStudentStatus(id, status);
		return "status updated";
	}
	@GetMapping("/getabc")
	private AddressDTO getAddressById(@RequestParam Long id) {
		return studentService.getAddressById(id);
	}
//	@GetMapping("/search")
//	private List<Student> getStudentsortBy(@RequestParam(defaultValue= "10") Long id,@RequestParam(defaultValue="active") String sortBy){
//		return studentService.getStudentsortBy(id,sortBy);
//		
//	}
	
	
	@GetMapping("/filter") 
	private ResultDTIO fetchStudentsByFilter(@RequestParam(defaultValue = "0") int pageNumber,
			                                  @RequestParam(defaultValue = "5") int pageSize, String filter) {
												return studentService.fetchStudentsByFilter(pageNumber, pageSize, filter);
		
	}
	@GetMapping("/addressfilter")
	private ResultDTIO fetchAddresssByFilter(@RequestParam(defaultValue = "0") int pageNo,
			                                 @RequestParam(defaultValue = "10")int pageSize, String filter) {
												return studentService.fetchAddresssByFilter(pageNo,pageSize,filter);
		
	}




}

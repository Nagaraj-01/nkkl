package com.Crudoperation.StudentServiceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.Crudoperation.DTO.AddressDTO;
import com.Crudoperation.DTO.ResultDTIO;
import com.Crudoperation.DTO.StudentDTO;
import com.Crudoperation.Entity.Address;
import com.Crudoperation.Entity.Student;
import com.Crudoperation.Repository.AddressRepository;
import com.Crudoperation.Repository.StudentRepository;
import com.Crudoperation.Service.AddressService;
import com.Crudoperation.Service.StudentService;
import com.Crudoperation.Utils.StudentUtils;
@Service
public class StudentServiceImpl extends StudentUtils implements StudentService {

	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private AddressService addressService;
	
	@Autowired
	private  AddressRepository addressRepository;
	@Override
	public StudentDTO addStudent(StudentDTO studentDTO) {
		Student save = studentRepository.save(convertStudentDTOToDO(studentDTO));
		List<AddressDTO> addressDTOs = new ArrayList<>();
		
		studentDTO.getAddressDTO().stream().forEach(address ->{
			AddressDTO addres = new AddressDTO();
			//addres.setAddresstype(address.getAddresstype());
			addres.setAddresstype(address.getAddresstype());
			addres.setHouseNo(address.getHouseNo());
			addres.setCity(address.getCity());
			addres.setState(address.getState());
			addres.setPincode(address.getPincode());
			addres.setId(address.getId());
			addres.setStudent(save);
			
			AddressDTO saveaddres = addressService.saveAddress(addres);
			addressDTOs.add(saveaddres);
		});
		StudentDTO convertStudentDOToDTO =convertStudentDOToDTO(save);
//		convertStudentDOToDTO.setAddressDTO(addressDTOs);
		return convertStudentDOToDTO;
	}

	
	@Override
	public StudentDTO updateStudent(StudentDTO studentDTO) {
		Student save = studentRepository.save(convertStudentDTOToDO(studentDTO));
		List<AddressDTO> addressDTOs = new ArrayList<>();
		
		studentDTO.getAddressDTO().stream().forEach(address ->{
			AddressDTO add =new AddressDTO();
			add.setAddresstype(address.getAddresstype());
			add.setHouseNo(address.getHouseNo());
			add.setCity(address.getCity());
			add.setId(address.getId());
			add.setState(address.getState());
			add.setPincode(address.getPincode());
			add.setStudent(save);
			AddressDTO saveadd =addressService.updateAddress(add);
			addressDTOs.add(saveadd);
			
		});
		StudentDTO convertStudentDOToDTO=convertStudentDOToDTO(save);
//		convertStudentDOToDTO.setAddress(addressDTOs);
		return convertStudentDOToDTO;
	}
	
	@Override
	public List<Student> getAllStudents() {
		List<Student> findAll = studentRepository.findAll();
		return findAll;
	}

	@Override
	public String deleteStudent(Long id) {
		studentRepository.deleteById(id);
		return "deleted student record";
	}

	@Override
	public StudentDTO getStudentById(Long id) {
		Student stud =studentRepository.findById(id).get();
		StudentDTO stdDto = convertStudentDOToDTO(stud);
		return stdDto;
	}

	@Override
	public String updateStudentStatus(Long id, String status) {
		studentRepository.updateStudentStatusById(id, status);
		return "Student status updated";
	}

	@Override
	public AddressDTO getAddressById(Long id) {
		return addressService.getAddressById(id);
	}
	
//	@Override
//	public List<Student> getStudentsortBy(Long id,String active ) {
//		List<Student> findAll =studentRepository.getStudentsortBy(id,active);
//		return findAll;
//	}


	@Override
	public ResultDTIO fetchStudentsByFilter(int pageNumber, int pageSize, String filter) {
		
		ResultDTIO resultDTIO = new ResultDTIO();
		
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<Student> page = studentRepository.findAll(pageable);
	    
		page = studentRepository.findByFilter(pageable, filter);
		 
		List<Student> list = new ArrayList<>();
		
		for(Student student : page) {
			list.add(student);
		}
		
		resultDTIO.setCode("code");
		resultDTIO.setMessage("Student by filter");
		resultDTIO.setData(list);
		return resultDTIO;
	}


	@Override
	public ResultDTIO fetchAddresssByFilter(int pageNo, int pageSize, String filter) {
		
		ResultDTIO  resultDTo= new ResultDTIO();
		
		Pageable pageable =PageRequest.of(pageNo, pageSize);
		Page<Address> page =addressRepository.findAll(pageable);
		
		page = addressRepository.findByFilter(pageable, filter);
		List<Address> list = new ArrayList<>();
		
		for(Address address : page) {
			list.add(address);
		}
		
		resultDTo.setCode("address");
		resultDTo.setMessage("address filtered");
		resultDTo.setData(list);
		

		
		return resultDTo;
	}

}

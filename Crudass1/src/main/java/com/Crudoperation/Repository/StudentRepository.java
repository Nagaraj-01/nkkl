package com.Crudoperation.Repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.Crudoperation.Entity.Address;
import com.Crudoperation.Entity.Student;
@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{
	@Transactional
	@Modifying
	@Query("update Student a set a.active = :status where a.id = :id")
	 void updateStudentStatusById(@Param("id") long id, @Param("status") String status);
	
	@Query("select s from Address s where s.id=:id")
	void getStudentById(@Param("id") Long id);
	
//	@Query("select s from Student s where s.active=:active")
//	 List<Student> findsortBy();
	
//	@Query(value="select a from Student a where a.active=:active or a.id=:id")
//	 List<Student> getStudentsortBy(@Param("id") Long id, @Param("active") String active );

	@Query("Select s from Student s where (s.course=?1) OR (s.student_Name=?1) OR (s.Department=?1) or (s.active=?1)")
	Page<Student> findByFilter(Pageable pageable, String filter);
	


//	@Query("select c from Student c where c.course=:course")
//	StudentDTO getStudentBycouse(@Param ("course") String course);
//	
//	@Query("select n from Student n where n.student_name=:active")
//	StudentDTO getStudentBystudent_name(@Param ("active") String student_name);
//	
//	@Query("select c from Student c where c.course=:course")
//	List<Student> findBycourse();

}

package com.Crudoperation.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.Crudoperation.Entity.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

	@Query("select a from Address a where (a.city=?1) OR (a.houseNo=?1) OR (a.pincode=?1) OR (a.state=?1) OR (a.addresstype=?1)")
	Page<Address> findByFilter(Pageable pageable, String filter);
}

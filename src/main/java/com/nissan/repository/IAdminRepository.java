package com.nissan.repository;


import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nissan.model.BankDetails;

@Repository
public interface IAdminRepository extends CrudRepository<BankDetails, Integer> {
	
	@Modifying
	@Query("update  com.nissan.model.BankDetails set isActive=0 where accountNumber=?1 ")
	public void deleteCustomer(int accNum);
 
	@Modifying
	@Query("update com.nissan.model.BankDetails set mobNum=?2 , email=?3 where accountNumber=?1")
	public void updateCustomerDetails(int accNum,long mobNum,String email);
 

}

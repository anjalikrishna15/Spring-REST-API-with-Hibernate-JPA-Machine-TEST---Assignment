package com.nissan.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nissan.model.BankDetails;
@Repository
public interface ICustomerRepository extends CrudRepository<BankDetails, Integer> {
/*	@Modifying
	@Query("update com.nissan.model.BankDetails set balance=(balance+?2) where accountNumber=?1 ")
	public void deposit(int accNum,int amount);
	
	@Modifying
	@Query("update com.nissan.model.BankDetails set balance=(balance-?2) where accountNumber=?1 ")
	public void withdraw(int accNum,int amount);
	
	@Modifying
	@Query("select new balance com.nissan.model.BankDetails  where accountNumber=?1 ")
	public void showBalance(int accNum);
*/

}

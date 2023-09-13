package com.nissan.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nissan.model.BankDetails;
@Repository
public interface ICustomerRepository extends CrudRepository<BankDetails, Integer> {


}

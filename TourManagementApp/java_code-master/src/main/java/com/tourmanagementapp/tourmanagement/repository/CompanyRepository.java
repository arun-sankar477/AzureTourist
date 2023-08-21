package com.tourmanagementapp.tourmanagement.repository;

import com.tourmanagementapp.tourmanagement.models.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRepository extends MongoRepository<Company,Long> {
    Company findByBranchId(String branchId);
    Company findByBranchName(String branchName);
    Company findByPlace(String place);
	List<Company> findByBranchNameOrPlaceOrBranchId(String branchId, String branchName, String place);
	 List<Company> findByBranchIdContaining(String branchId);
	 List<Company> findByBranchNameContaining(String branchName);
	 List<Company> findByPlaceContaining(String place);
}

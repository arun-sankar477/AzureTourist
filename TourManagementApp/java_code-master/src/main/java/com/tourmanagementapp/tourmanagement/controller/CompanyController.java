package com.tourmanagementapp.tourmanagement.controller;

import com.tourmanagementapp.tourmanagement.models.Company;
import com.tourmanagementapp.tourmanagement.models.Company.CustomException;
import com.tourmanagementapp.tourmanagement.repository.CompanyRepository;
import com.tourmanagementapp.tourmanagement.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
//@CrossOrigin(origins="http://localhost:3000")
//@RequestMapping("tourism/api/v1")
public class CompanyController {

    private final CompanyService companyService;

    private final CompanyRepository companyRepository;

    @Autowired
    public CompanyController(CompanyService companyService, CompanyRepository companyRepository) {
        this.companyService = companyService;
        this.companyRepository = companyRepository;
    }

    @PostMapping("branch/add-places")
    public ResponseEntity<?> addCompany(@RequestBody Company company) {
        try {
            Company createdCompany = companyService.addCompany(company);
            return new ResponseEntity<>(createdCompany, HttpStatus.CREATED);
        } catch (Company.CustomException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/get")
    public ResponseEntity<?> get(){
        List<Company> all = companyRepository.findAll();
        return new ResponseEntity<List<Company>>(all,HttpStatus.OK);

    }

   @PutMapping("branch/update-tariff/{branchId}")
    public ResponseEntity<String> updateTariffDetails(@PathVariable String branchId,
                                                      @RequestBody TrafficUpdateRequest trafficUpdateRequest) {
        try {
            companyService.updateTrafficDetails(branchId,trafficUpdateRequest);
            return ResponseEntity.ok("Tariff details updated successfully.");
        } catch (Company.CustomException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
//    @PutMapping("branch/update-tariff/{branchId}")
//    public ResponseEntity<String> updateTariffDetails(@PathVariable String branchId,
//                                                      @RequestBody Map<String, Object> updateData) {
//        try {
//        	String newplace=(String)updateData.get("place");
//        	double newtariff=(double)updateData.get("Tariff");
//            companyService.updateTrafficDetails(branchId,newplace,newtariff);
//            return ResponseEntity.ok("Tariff details updated successfully.");
//        } catch (Company.CustomException e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
//        }
//    }
    
    @GetMapping("/get/{branchName}")
    public ResponseEntity<?> get(@PathVariable ("branchName") String branchName){
    	try {
       Company company =companyService.getByName(branchName);
       if(company!=null) {
    	   return ResponseEntity.ok(company);
       }
       else {return ResponseEntity.notFound().build();
       }}
    	catch (Company.CustomException e) {
    	return	ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
			// TODO: handle exception
		}
    	
    	
 

    }
    
    @GetMapping("/search/{keyWord}")
    public ResponseEntity<List<Company>> searchCompany(@PathVariable ("keyWord") String keyWord ){
    	List<Company> companies = companyService.searchCompany(keyWord);
    	return ResponseEntity.ok(companies);
    }


    @GetMapping("/getp/{place}")
    public ResponseEntity<?> getp(@PathVariable ("place") String place){
    	try {
       Company company =companyService.getByPlace(place);
       if(company!=null) {
    	   return ResponseEntity.ok(company);
       }
       else {return ResponseEntity.notFound().build();
       }}
    	catch (Company.CustomException e) {
    	return	ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
			// TODO: handle exception
		}
}      
    
    
    @GetMapping("/admin/{criteria}/{criteriaValue}")
    public ResponseEntity<List<Company>> searchCompanies(@PathVariable String criteria, @PathVariable String criteriaValue) {
    try {
    List<Company> companies = companyService.searchCompanies(criteria, criteriaValue);
    return ResponseEntity.ok(companies);
    } catch (Company.CustomException e) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }
    }
    
    
//    @PutMapping("branch/update-tariff/{branchId}")
//    public ResponseEntity<String> updateTariffDetails(@PathVariable String branchId,
//                                                      @RequestBody Company cplace , @RequestBody Company ctariff) {
//        try {
//            companyService.updateTrafficDetails(branchId,cplace,ctariff);
//            return ResponseEntity.ok("Tariff details updated successfully.");
//        } catch (Company.CustomException e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
//        }
//    }
    
    
    @GetMapping("/getid/{branchId}")
    public ResponseEntity<?> getid(@PathVariable ("branchId") String branchId){
    	try {
       Company company =companyService.getByBranchId(branchId);
       if(company!=null) {
    	   return ResponseEntity.ok(company);
       }
       else {return ResponseEntity.notFound().build();
       }}
    	catch (Company.CustomException e) {
    	return	ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
			// TODO: handle exception
		}}}

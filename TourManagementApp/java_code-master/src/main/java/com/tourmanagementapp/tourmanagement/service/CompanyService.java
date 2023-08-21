package com.tourmanagementapp.tourmanagement.service;


import com.tourmanagementapp.tourmanagement.controller.TrafficUpdateRequest;
//import com.example.demo.UserEntity;
//import com.tourmanagementapp.tourmanagement.controller.TrafficUpdateRequest;
import com.tourmanagementapp.tourmanagement.models.Company;
//import com.tourmanagementapp.tourmanagement.models.TrafficDetails;
import com.tourmanagementapp.tourmanagement.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    @Autowired
    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }



    public Company addCompany(Company company) {
        // Set unique branchId and addedDate here
        // (You can use UUID or any other mechanism to generate a unique branchId)
       company.setBranchId(generateUniqueBranchId());
        company.setAddedDate(LocalDate.now());
        validateCompanyDetails(company);
        return companyRepository.save(company);}

        // Save the company with tariff details
   /*     List<TrafficDetails> tariffDetails = company.getTrafficDetails();
        if (tariffDetails != null) {
            for (TrafficDetails tariffDetail : tariffDetails) {

                // Validate and set the tariff details
              //  validateAndSetTariffDetails(tariffDetail);
                tariffDetail.setCompany(company);
            }
        }

        return companyRepository.save(company);
    }
*/
    private void validateCompanyDetails(Company company) {
        // Website should contain "www"
        if (!company.getWebsite().contains("www")) {
            throw new Company.CustomException("Website should contain 'www'.");
        }

        // Validate email format
        if (!company.getEmail().matches("\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}\\b")) {
            throw new Company.CustomException("Invalid email format.");
        }

        // Validate contact number (exactly 10 digits and numeric)
        if (!company.getContact().matches("\\d{10}")) {
            throw new Company.CustomException("Mobile number must be exactly 10 digits and contain only numeric characters.");
        }
        if (company.getTariff() < 50000 || company.getTariff() > 100000) {
            throw new Company.CustomException("Tariff details must range between 50,000 - 100,000.");
        }
    }

    private String generateUniqueBranchId() {
        // Implement your logic to generate a unique branchId
        // Example: Use UUID.randomUUID().toString() to generate a unique identifier
        return UUID.randomUUID().toString();
    }

    private void validateAndSetTariffDetails(TrafficUpdateRequest trafficUpdateRequest) {
        //double tariff = tariffDetail.getTariff();
        if (trafficUpdateRequest.getTariff() < 50000 || trafficUpdateRequest.getTariff() > 100000) {
            throw new Company.CustomException("Tariff details must range between 50,000 - 100,000.");
        }
    }

   public void updateTrafficDetails(String branchId, TrafficUpdateRequest trafficUpdateRequest) {
        Company company = companyRepository.findByBranchId(branchId);

       if (company == null) {
           throw new Company.CustomException("Invalid branchId");
      }
      validateAndSetTariffDetails(trafficUpdateRequest);
      //List<TrafficDetails> trafficDetails = company.getTrafficDetails();
      if (trafficUpdateRequest != null) {
      	company.setTariff(trafficUpdateRequest.getTariff());
         // for (TrafficDetails trafficDetail : trafficDetails) {
             //   if (trafficDetail.getPlace().equalsIgnoreCase(trafficUpdateRequest.getPlace())) {
                  // Validate and set the tariff details

                   //trafficDetail.setTariff(trafficUpdateRequest.getTariff());


                   // Update the place in the Company entity
                  // company.setPlace(trafficUpdateRequest.getPlace());
         //      }
         // }

       }

      // Update the lastUpdateDate before saving the Company entity
       company.setLastUpdateDate(LocalDate.now());
       companyRepository.save(company);
        
    }

    
//    public void updateTrafficDetails(String branchId, String newplace, double newtariff) {
//      // Optional<Company>  comOptional = companyRepository.findByBranchId(branchId);
//    	Company company = companyRepository.findByBranchId(branchId);
//
//        if (company == null) {
//            throw new Company.CustomException("Invalid branchId");
//
//        }
//        validateAndSetTariffDetails(newplace,newtariff);
//        //List<TrafficDetails> trafficDetails = company.getTrafficDetails();
//
//
//     //   if (newtariff != null) {
//        	company.setTariff(newtariff);
//        	company.setPlace(newplace);
//           // for (TrafficDetails trafficDetail : trafficDetails) {
//             //   if (trafficDetail.getPlace().equalsIgnoreCase(trafficUpdateRequest.getPlace())) {
//                    // Validate and set the tariff details
//
//                    //trafficDetail.setTariff(trafficUpdateRequest.getTariff());
//
//
//                    // Update the place in the Company entity
//                   // company.setPlace(trafficUpdateRequest.getPlace());
//          //      }
//
//           // }
//
//      //  }
//
//        // Update the lastUpdateDate before saving the Company entity
//        company.setLastUpdateDate(LocalDate.now());
//        companyRepository.save(company);
//        
//    }

    public Company getByName(String bName) {
    	if(companyRepository.findByBranchName(bName)==null) {
    		throw new Company.CustomException("Invalid Branch Name");
    	}
		return companyRepository.findByBranchName(bName);
	
}
    public Company getByPlace(String bPlace) {
    	if(companyRepository.findByPlace(bPlace)==null) {
    		throw new Company.CustomException("Invalid Place");
    	}
		return companyRepository.findByPlace(bPlace);
	
}

    public Company getByBranchId(String bId) {
    	if(companyRepository.findByBranchId(bId)==null) {
    		throw new Company.CustomException("Invalid BranchId");
    	}
		return companyRepository.findByBranchId(bId);
	
}



	public List<Company> searchCompany(String keyWord) {
		// TODO Auto-generated method stub
		
		return companyRepository.findByBranchNameOrPlaceOrBranchId(keyWord,keyWord,keyWord);
	}



	public List<Company> searchCompanies(String criteria, String criteriaValue) {
		// TODO Auto-generated method stub
		
		//return null;
		switch (criteria.toLowerCase()) {
		case "branchid":
		return companyRepository.findByBranchIdContaining(criteriaValue);
		case "branchname":
		return companyRepository.findByBranchNameContaining(criteriaValue);
		case "place":
		return companyRepository.findByPlaceContaining(criteriaValue);
		default:
		throw new Company.CustomException("Invalid search criteria.");
		}
		}
	}
   


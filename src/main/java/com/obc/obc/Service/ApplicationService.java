package com.obc.obc.Service;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.obc.obc.Entity.Application;
import com.obc.obc.Repository.ApplicationRepository;
import jakarta.transaction.Transactional;
@Service
public class ApplicationService 
{
    private final ApplicationRepository applicationRepository;
    public ApplicationService (ApplicationRepository applicationRepository)
    {
        this.applicationRepository=applicationRepository;
    }
    //Form Stored Details
    public Application saveApplication(String userid,String applicationid,String name,String DoB,String gender,String birthplace,String fathername,String mothername,String permanetadd,String postaladd,String mobno,String email,LocalDate applyieddate,String status)
    {
        Application newApplication=new Application();
        newApplication.setUserid(userid);
        newApplication.setApplicationid(applicationid);
        newApplication.setName(name);
        newApplication.setDoB(DoB);
        newApplication.setGender(gender);
        newApplication.setBirthplace(birthplace);
        newApplication.setFathername(fathername);
        newApplication.setMothername(mothername);
        newApplication.setPermanetadd(permanetadd);
        newApplication.setPostaladd(postaladd);
        newApplication.setMobno(mobno);
        newApplication.setEmail(email);
        newApplication.setApplyieddate(applyieddate);
        newApplication.setStatus(status);
        return applicationRepository.save(newApplication);
    }
    //Show Details
    public List<Application> getAllApplications()
    {
        return applicationRepository.findAll();
    }
    public List<Application> getApplicationsByStatus(String status)
    {
        return applicationRepository.findByStatus(status);
    }
    public List<Application> getAllApplication()
    {
        return applicationRepository.findAll();
    }
    //Count Command
    public Long countApplications(String status)
    {
        return applicationRepository.countByStatus(status);
    }
    public Long countApplications()
    {
        return applicationRepository.count();
    }
    //Delete Command
    @Transactional
    public void deleteApplication(Long id)
    {
         applicationRepository.deleteById(id);
    }
    //View Command
    public Application viewApplication(Long id)
    {
        return applicationRepository.findById(id).orElse(null);
    }
    //Edit Command used in view details
    public void updateApplication(Application application)
    {
            applicationRepository.save(application);
    }
    //Status update
    public void setBcStatus(Long id, String status) {
        Optional<Application> optionalApplication = applicationRepository.findById(id);
        if (optionalApplication.isPresent()) {
            Application application = optionalApplication.get();
            application.setStatus(status);
            application.setUpdationdate(new Timestamp(System.currentTimeMillis()));
            applicationRepository.save(application);
        } else {
            // Handle case when application with given id is not found
            throw new IllegalArgumentException("Application with id " + id + " not found");
        }
    }
    public List<Application> getAlApplications() 
    {
        return applicationRepository.findAll();
    }
    // Certificate Verified
   public String getStatus(String mobno,String applicationid)
   {
        System.out.println(applicationRepository.getStatus(mobno,applicationid));

    return applicationRepository.getStatus(mobno,applicationid);
   }
   public Application getByCertificate(String m,String aid)
    {
        return applicationRepository.findByMobnoAndApplicationid(m, aid);
    }
    //Search Engine
    public List<Application> search(String query)
    {
        return applicationRepository.findByApplicationidOrNameOrMobno(query, query, query);
    }
    //Between Date to Search
    public List<Application> getApplicationBetweenDates(String startDate,String endDate)
    {
        return applicationRepository.findByDoBBetweenDates(startDate,endDate);
    }
}
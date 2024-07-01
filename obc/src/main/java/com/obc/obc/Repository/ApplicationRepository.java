package com.obc.obc.Repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.obc.obc.Entity.Application;
public interface ApplicationRepository extends JpaRepository<Application,Long>
{
    Application findByName(String name);
    List<Application> findByMobno(String mobno);
    List<Application> findByUserid(String userid);
    @Query(value = "SELECT status FROM Application WHERE mobno = :mobno AND applicationid = :applicationid", nativeQuery = true)
    String getStatus(@Param("mobno") String mobno, @Param("applicationid") String applicationid);
    Application findByMobnoAndApplicationid(String mobno, String applicationid);
    List<Application> findByStatus(String status);
    List<Application> findByApplicationidOrNameOrMobno(String applicationid, String name, String mobno);
    @Query("SELECT a FROM Application a WHERE a.DoB BETWEEN :startDate AND :endDate")
    List<Application> findByDoBBetweenDates(@Param("startDate") String startDate, @Param("endDate") String endDate);
    Long countByStatus(String status);
    }
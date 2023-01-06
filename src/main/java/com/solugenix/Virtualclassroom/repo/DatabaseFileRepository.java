package com.solugenix.Virtualclassroom.repo;

import com.solugenix.Virtualclassroom.entity.DatabaseFileEnity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DatabaseFileRepository extends JpaRepository<DatabaseFileEnity, Long> {
    List<DatabaseFileEnity> findByTypeIs(String type);

    public List<DatabaseFileEnity> findByUploadedId(String uploadedId);

 //   @Query(value = "SELECT * FROM admin WHERE user_name=? AND password=?",nativeQuery = true)
//     @Query(value="SELECT MONTH(STORED_DATE) , COUNT(*)  FROM  files WHERE YEAR(stored_date)=2022 GROUP BY MONTH(STORED_DATE) " , nativeQuery = true)
//     public List<PptVideoCount> getCountByMonthMap();

//    @Transactional(readOnly = true)
//    @Query("SELECT new com.solugenix.Virtualclassroom.model.PptVideoCount( c.MONTH(STORED_DATE) , COUNT(*)) FROM  DatabaseFileEnity AS c  WHERE c.YEAR(stored_date)=2022 GROUP BY MONTH(STORED_DATE) ")
//    public List<PptVideoCount> countTotalMaterialByMonths();


    @Query(value="SELECT MONTH(STORED_DATE)  FROM  files WHERE YEAR(stored_date)=2022 AND type=? GROUP BY MONTH(STORED_DATE) " , nativeQuery = true)
    public List<Number> getCountByMonth(String type);

    @Query(value="SELECT  COUNT(*) FROM  files WHERE YEAR(stored_date)=2022 AND type=? GROUP BY MONTH(STORED_DATE) " , nativeQuery = true)
    public List<Long>  getCountByRecords(String type);

//    "SELECT MONTH(STORED_DATE), COUNT(*) FROM  files WHERE YEAR(stored_date)=2022 AND type=?""


}
package com.random.DAO;

import com.random.entity.Student;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentDAO extends JpaRepository<Student, Integer> {
//todo проверить!!!!
    @Query("from Student where e_isPresent=true")
    List<Student> findAllisPresentTrue();
//    List<Student> findAllisPresent();

    @Query("from Student where e_isPresent=false")
    List<Student> findAllisPresentFalse();
//    List<Student> findAllisNotPresent();

    @Query("from Student where e_isPresent=true AND l_isAnswer=true" )
    List<Student> findAllisPresentTrueAndAnswerTrue();

    @Query("from Student where e_isPresent=true AND l_isAnswer=false " )
    List<Student> findAllisPresentTrueAndAnswerFalse();


}

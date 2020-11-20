package com.steveyu.hadoopnetdisk.repository;

import com.steveyu.hadoopnetdisk.pojo.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}

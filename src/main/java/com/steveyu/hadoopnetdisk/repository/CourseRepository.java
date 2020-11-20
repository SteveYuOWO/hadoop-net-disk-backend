package com.steveyu.hadoopnetdisk.repository;

import com.steveyu.hadoopnetdisk.pojo.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Integer> {
}

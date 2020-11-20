package com.steveyu.hadoopnetdisk.api;

import com.steveyu.hadoopnetdisk.pojo.Admin;
import com.steveyu.hadoopnetdisk.pojo.Student;
import com.steveyu.hadoopnetdisk.pojo.Teacher;
import com.steveyu.hadoopnetdisk.repository.AdminRepository;
import com.steveyu.hadoopnetdisk.repository.StudentRepository;
import com.steveyu.hadoopnetdisk.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@RestController
@RequestMapping("login")
public class LoginApi {
    @Autowired
    StudentRepository studentRepository;

    @PostMapping("student")
    public Student loginStudent(@RequestBody Student student) {
        return student == null ?
                null:
                studentRepository.findOne(Example.of(student)).orElse(null);
    }

    @Autowired
    TeacherRepository teacherRepository;

    @PostMapping("teacher")
    public Teacher loginTeacher(@RequestBody Teacher teacher) {
        return teacher == null ?
                null:
                teacherRepository.findOne(Example.of(teacher)).orElse(null);
    }

    @Autowired
    AdminRepository adminRepository;

    @PostMapping("admin")
    public Admin loginAdmin(@RequestBody Admin admin) {
        return admin == null ?
                null:
                adminRepository.findOne(Example.of(admin)).orElse(null);
    }
}

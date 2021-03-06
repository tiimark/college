package com.mark.college.service;

import com.mark.college.entity.Student;
import com.mark.college.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Tiffany Mark on 27/06/2016.
 */

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Student findStudent(int id){
        Student student = studentRepository.findById(id);

        if(student != null)
            return student;
        return null;
    }

    public Student auth(int id, String password){
        Student student = studentRepository.findByIdAndPassword(id, password);

        if(student != null)
            return student;
        return null;
    }

}

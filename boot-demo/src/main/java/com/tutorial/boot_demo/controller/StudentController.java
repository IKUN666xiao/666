package com.tutorial.boot_demo.controller;

import com.tutorial.boot_demo.Response;
import com.tutorial.boot_demo.dao.Student;
import com.tutorial.boot_demo.dto.StudentDTO;
import com.tutorial.boot_demo.service.StudentService;
import org.apache.coyote.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {
    @Autowired
    private StudentService studentService;
    @GetMapping("/student/{id}")
    public Response<StudentDTO> getStudentById(@PathVariable long id){
        return Response.newSuccess(studentService.getStudentById(id));
    }
    @PostMapping("/student")
    public Response<Long> addNewStudent(@RequestBody StudentDTO studentDTO){
        return  Response.newSuccess(studentService.addNewStudent(studentDTO));
    }
    @GetMapping("/student")
    public Response<List<StudentDTO>>getAllusers(){
        return Response.newSuccess(studentService.getAllusers());
    }
    @DeleteMapping("/student/{id}")
    public void  deleteStudentByid(@PathVariable long id){
        studentService.deleteStudentById(id);
    }
    @PutMapping("/student/{id}")
    public Response<StudentDTO> updateStudentById(@PathVariable long id ,@RequestBody StudentDTO studentDTO){
        studentDTO.setId(id);
        studentService.updateStudentById(studentDTO);
        return  Response.newSuccess(null);
    }

}

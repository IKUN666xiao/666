package com.tutorial.boot_demo.service;

import com.tutorial.boot_demo.dto.PlayerDTO;
import com.tutorial.boot_demo.dto.StudentDTO;

import  java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;


public interface StudentService {
    StudentDTO getStudentById(long id);

    PlayerDTO getPlayerById(long id);
  //  String findMatch(String deviceId);
    Long addNewStudent(StudentDTO studentDTO);

    Long addNewPlayer(PlayerDTO playerDTO);

    void deleteStudentById(long id);

    List<StudentDTO> getAllusers();

    List<PlayerDTO> getAllplayers();

    void updateStudentById(StudentDTO studentDTO);

    void updatePlayerById(PlayerDTO playerDTO);

}

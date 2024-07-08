package com.tutorial.boot_demo.converter;

import com.tutorial.boot_demo.dao.Player;
import com.tutorial.boot_demo.dao.Student;
import com.tutorial.boot_demo.dto.PlayerDTO;
import com.tutorial.boot_demo.dto.StudentDTO;

public class StudentConverter {
    public static StudentDTO convertStudent(Student student) {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setId(student.getId());
        studentDTO.setName(student.getName());
        studentDTO.setEmail(student.getEmail());
        studentDTO.setAge(student.getAge());
        return studentDTO;
    }
        public static Student convertStudent(StudentDTO studentDTO){
            Student student = new Student();
            student.setName(studentDTO.getName());
            student.setEmail(studentDTO.getEmail());
            student.setAge(studentDTO.getAge());
            return student;


        }
    public static PlayerDTO convertplayer(Player player) {
        PlayerDTO playerDTO = new PlayerDTO();
        playerDTO.setId(player.getId());
        playerDTO.setName(player.getUsername());
        playerDTO.setEmail(player.getEmail());
        return playerDTO;
    }
    public static Player convertplayer(PlayerDTO playerDTO){
        Player player = new Player();
        player.setUsername(playerDTO.getName());
        player.setEmail(playerDTO.getEmail());
        return player;


    }

}

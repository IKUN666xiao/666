package com.tutorial.boot_demo.service;

import com.tutorial.boot_demo.converter.StudentConverter;
import com.tutorial.boot_demo.dao.Player;
import com.tutorial.boot_demo.dao.PlayerResponsitory;
import com.tutorial.boot_demo.dao.Student;
import com.tutorial.boot_demo.dao.StudentRespository;
import com.tutorial.boot_demo.dto.PlayerDTO;
import com.tutorial.boot_demo.dto.StudentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import  java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class StudentServiceimpl implements StudentService{
    @Autowired
    private StudentRespository studentRespository;
    @Autowired
    private PlayerResponsitory playerRespository;
//    private ConcurrentMap<String, AtomicInteger> matchmakingQueue = new ConcurrentHashMap<>();
//
//    // ... 其他方法 ...
//
//    public String findMatch(String deviceId) {
//        // 增加设备ID的请求计数器
//        matchmakingQueue.computeIfAbsent(deviceId, k -> new AtomicInteger()).incrementAndGet();
//
//        // 检查是否有匹配的对手
//        for (String candidate : matchmakingQueue.keySet()) {
//            if (!candidate.equals(deviceId)) {
//                // 减少两个设备ID的请求计数器
//                int candidateCount = matchmakingQueue.get(candidate).decrementAndGet();
//                int selfCount = matchmakingQueue.get(deviceId).decrementAndGet();
//
//                // 如果两个设备ID的请求计数器都为0，从map中移除它们
//                if (candidateCount == 0) {
//                    matchmakingQueue.remove(candidate);
//                }
//                if (selfCount == 0) {
//                    matchmakingQueue.remove(deviceId);
//                }
//
//                return candidate; // 返回匹配的设备ID
//            }
//        }
//
//        // 如果没有找到匹配，返回null
//        return null;
//    }

    @Override
    public StudentDTO getStudentById(long id) {
        Student student=studentRespository.findById(id).orElseThrow(() -> new RuntimeException("NOT FOUND"));
        return StudentConverter.convertStudent(student);
    }
    @Override
    public PlayerDTO getPlayerById(long id) {
        Player player=playerRespository.findById(id).orElseThrow(() -> new RuntimeException("NOT FOUND"));
        return StudentConverter.convertplayer(player);
    }

    @Override
    public Long addNewStudent(StudentDTO studentDTO) {
        Student student= StudentConverter.convertStudent(studentDTO);
        student =studentRespository.save(student);

        return student.getId();
    }
    @Override
    public Long addNewPlayer(PlayerDTO playerDTO) {
        Player player= StudentConverter.convertplayer(playerDTO);
        player =playerRespository.save(player);

        return player.getId();
    }

    @Override
    public void deleteStudentById(long id) {
        studentRespository.findById(id).orElseThrow(() -> new  IllegalArgumentException("id: "+id+"doesn't exist!"));
        studentRespository.deleteById(id);
    }
    @Override
    public List<StudentDTO>getAllusers(){
        List <Student> students=studentRespository.findAll();
        return students.stream().
                map(StudentConverter::convertStudent).
                collect(Collectors.toList());
    }
    @Override
    public List<PlayerDTO> getAllplayers(){
        List<Player> players=playerRespository.findAll();
        return players.stream().
                map(StudentConverter::convertplayer).
                collect(Collectors.toList());
    }
    @Override
    //不回馈失误的数据
    //@Transactional
    public void updateStudentById(StudentDTO studentDTO) {
        Student studentToUpDdate = studentRespository.findById(studentDTO.getId())
                .orElseThrow(() -> new RuntimeException("not found"));
        studentToUpDdate.setName(studentDTO.getName());
        studentToUpDdate.setId(studentDTO.getId());
        studentToUpDdate.setEmail(studentDTO.getEmail());
        studentRespository.save(studentToUpDdate);
    }
    public void updatePlayerById(PlayerDTO playerDTO) {
        Player playerToUpDdate = playerRespository.findById(playerDTO.getId())
                .orElseThrow(() -> new RuntimeException("not found"));
        playerToUpDdate.setUsername(playerDTO.getName());
        playerToUpDdate.setId(playerDTO.getId());
        playerToUpDdate.setEmail(playerDTO.getEmail());
        playerRespository.save(playerToUpDdate);
    }

}

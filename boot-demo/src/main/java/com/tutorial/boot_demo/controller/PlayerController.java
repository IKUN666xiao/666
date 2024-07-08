package com.tutorial.boot_demo.controller;

import com.tutorial.boot_demo.Response;
import com.tutorial.boot_demo.dao.Player;
import com.tutorial.boot_demo.dao.PlayerResponsitory;
import com.tutorial.boot_demo.dto.PlayerDTO;
import com.tutorial.boot_demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class PlayerController {
    @Autowired
    private PlayerResponsitory playerResponsitory;
    @Autowired
    private StudentService studentService;
    @GetMapping("/player/{id}")
    public Response<PlayerDTO> getPlayerById(@PathVariable long id){
        return Response.newSuccess(studentService.getPlayerById(id));
    }
    @PostMapping("/player")
    public Response<Long> addNewPlayer(@RequestBody PlayerDTO playerDTO){
        return  Response.newSuccess(studentService.addNewPlayer(playerDTO));
    }
    @GetMapping("/player")
    public Response<List<PlayerDTO>>getAllplayers(){
        return Response.newSuccess(studentService.getAllplayers());
    }
    @DeleteMapping("/player/{id}")
    public void  deleteStudentByid(@PathVariable long id){
        studentService.deleteStudentById(id);
    }
    @PutMapping("/player/{id}")
    public Response<PlayerDTO> updatePlayerById(@PathVariable long id ,@RequestBody PlayerDTO playerDTO){
        playerDTO.setId(id);
        studentService.updatePlayerById(playerDTO);
        return  Response.newSuccess(null);
    }
    @PostMapping("/kunking")
    public ResponseEntity<Map<String, String>> requestMatch(@RequestBody Map<String,Long> IKUN) {
        // 直接获取name和deviceId
        String name = String.valueOf(IKUN.get("name"));
        long deviceId = IKUN.get("id");
        Map<String, String> response = new HashMap<>();
            if (name != null) {
                // 找到匹配，返回匹配的设备ID
                response.put("ok", "正在匹配");
                return ResponseEntity.ok(response);

            } else {
                // 没有找到匹配，返回等待匹配的消息
                response.put("ok", "发送匹配请求失败");
                return ResponseEntity.ok(response);
            }
        }


    @PostMapping("/waitkun")
    public ResponseEntity<Map<String, String>> waitMatch(@RequestBody Map<String, Long> IKUN) {
        // 直接获取name和deviceId
        long deviceId = 0;
        deviceId = IKUN.get("id");
        Map<String, String> response = new HashMap<>();
        if (deviceId != 0) {
            response.put("ok", "正在匹配");
        } else {
            response.put("ok", "not");
        }
        return ResponseEntity.ok(response);
    }
    @GetMapping("/getPlayerId")
    public ResponseEntity<Map<String, String>> getPlayerId() {
        List<Player> players = playerResponsitory.findAll(); // 获取所有Player对象的列表
        Map<String, String> response = new HashMap<>();
        if (!players.isEmpty()) {
            response.put("playerId", String.valueOf(players.get(0).getId())); // 如果列表不为空，获取第一个Player的playerId
        } else {
            response.put("playerId", "Not player was found"); // 如果列表为空，返回未找到Player的消息
        }
        return ResponseEntity.ok(response); // 返回响应
    }






}

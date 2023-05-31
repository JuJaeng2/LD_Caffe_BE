package LD_Caffe.ld_caffe.controller;

import LD_Caffe.ld_caffe.dto.UserDTO;
import LD_Caffe.ld_caffe.entity.User;
import LD_Caffe.ld_caffe.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/list")  // 모든 유저 조회 메서드
    public String findAllUser(Model model){
        model.addAttribute("userList",userService.findAllUser());
        return "userList";
    }

    @GetMapping("/{u_id}")  // u_id 값으로 특정 유저 조회 메서드
    public String userInfo(@PathVariable("u_id")String u_id,Model model){
        Optional<User> user = userService.findUserById(u_id);
        model.addAttribute("user",user.get());
        return "userInfo";
    }

    @PostMapping("/signup")
    @CrossOrigin(origins = "http://localhost:3000/")
    public String signUpUser(@RequestBody UserDTO userDTO){
        userService.saveUser(userDTO);
        return "";
    }

}
package com.lms.controller;


import com.lms.dto.UserDto;
import com.lms.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDto userDto){
        return new ResponseEntity<>(this.userService.login(userDto), HttpStatus.OK);
    }

    @PostMapping("/createUser")
    public ResponseEntity<?> createUser(@RequestBody UserDto userDto){
        return new ResponseEntity<>(this.userService.createUser(userDto), HttpStatus.OK);
    }

    @PutMapping("/updateUser")
    public ResponseEntity<?> updateUser(@RequestBody UserDto userDto){
        return new ResponseEntity<>(this.userService.updateUser(userDto), HttpStatus.OK);
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<?> getAllUsers(){
        return new ResponseEntity<>(this.userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSingleUser(@PathVariable Long id){
        return new ResponseEntity<>(this.userService.getSingleUser(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
        return new ResponseEntity<>(this.userService.deleteUser(id), HttpStatus.OK);
    }
}

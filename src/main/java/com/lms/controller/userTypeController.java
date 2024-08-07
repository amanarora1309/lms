package com.lms.controller;


import com.lms.dto.UserTypeDto;
import com.lms.services.UserTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class userTypeController {
    private UserTypeService userTypeService;

    public userTypeController(UserTypeService userTypeService) {
        this.userTypeService = userTypeService;
    }


    @PostMapping("/createUserType")
    public ResponseEntity<?> createUserType(@RequestBody UserTypeDto userTypeDto){
        return new ResponseEntity<>(this.userTypeService.createUserType(userTypeDto), HttpStatus.OK);
    }

    @PutMapping("/updateUserType")
    public ResponseEntity<?> updateUserType(@RequestBody UserTypeDto userTypeDto){
        return new ResponseEntity<>(this.userTypeService.updateUserType(userTypeDto), HttpStatus.OK);
    }

    @GetMapping("/getAllUserTypes")
    public ResponseEntity<?> getAllUserTypes(){
        return new ResponseEntity<>(this.userTypeService.getAllUserTypes(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSingleUserType(@PathVariable Long id){
        return new ResponseEntity<>(this.userTypeService.getSingleUserType(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserType(@PathVariable Long id){
        return new ResponseEntity<>(this.userTypeService.deleteUserType(id), HttpStatus.OK);
    }
}

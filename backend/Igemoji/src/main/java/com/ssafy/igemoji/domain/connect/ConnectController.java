package com.ssafy.igemoji.domain.connect;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/connect")
public class ConnectController {
    @GetMapping()
    public ResponseEntity<String> connectTest(){
        return ResponseEntity.status(HttpStatus.OK).body("연결에 성공하였습니다.");
    }
}

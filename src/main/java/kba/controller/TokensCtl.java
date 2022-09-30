package kba.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import kba.service.TokenSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/tokens")
@Api("Tokens actions controller")
public class TokensCtl {

    @Autowired
    TokenSvc tokenSvc;

    @PostMapping("/generate")
    @ApiOperation("Generate a client token")
    ResponseEntity<String> generateToken(
            @ApiParam(value="Client mail", required = true)
            @RequestParam("mail")String mail,
            @ApiParam(value="Client password", required = true)
            @RequestParam("password")String password) {
        //TODO EXCEPTION HANDLER
        return new ResponseEntity<>(tokenSvc.generateToken(mail ,password), HttpStatus.OK);
    }

}

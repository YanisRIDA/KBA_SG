package kba.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@Api("Bank operations controller")
public class OperationsCtl {

    @GetMapping("/test")
    @ApiOperation("test")
    String test() {
        return "test";
    }

}

package kba.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import kba.model.dto.ClientDTO;
import kba.service.ClientSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/clients")
@Api("Clients actions controller")
public class ClientCtl {

    @Autowired
    ClientSvc clientSvc;

    @PostMapping("/create")
    @ApiOperation("Create a client account")
    ResponseEntity<ClientDTO> createAccount(
            @ApiParam(name="body", value="Client account to create", required = true)
            @Valid @RequestBody ClientDTO clientDTO) {
        //TODO EXCEPTION HANDLER
        return new ResponseEntity<>(clientSvc.createClient(clientDTO), HttpStatus.CREATED);
    }

}

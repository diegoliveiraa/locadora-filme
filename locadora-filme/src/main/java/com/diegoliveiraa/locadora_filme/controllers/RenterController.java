package com.diegoliveiraa.locadora_filme.controllers;

import com.diegoliveiraa.locadora_filme.dtos.RenterDTO;
import com.diegoliveiraa.locadora_filme.entitys.Renter;
import com.diegoliveiraa.locadora_filme.servives.RenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/renters")
public class RenterController {
    @Autowired
    private RenterService renterService;

    @PostMapping
    public ResponseEntity<Renter> createRenter(@RequestBody RenterDTO data) {

        Renter newRenter = this.renterService.createRenter(data);
        return new ResponseEntity<>(newRenter, HttpStatus.CREATED);
    }

    @PostMapping("/update-renter")
    public ResponseEntity<Renter> updateRenter(@RequestBody RenterDTO data) {

        Renter updateRenter = this.renterService.updateRenter(data);
        return new ResponseEntity<>(updateRenter, HttpStatus.OK);
    }

    @RequestMapping
    public ResponseEntity<List<Renter>> searchAllUser() {

        List<Renter> allRenter = this.renterService.getAllRenter();
        return new ResponseEntity<>(allRenter, HttpStatus.OK);
    }

    @RequestMapping("/search-renter")
    public ResponseEntity<Renter> searchRenter(@RequestBody String document, @RequestBody String name) {

        Renter searchRenter = this.renterService.findByDocumentOrName(document, name);
        return new ResponseEntity<>(searchRenter, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Renter> deleteRenter(@RequestBody String id) {
        Renter deleteRenter = this.renterService.deleteRenter(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

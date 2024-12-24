package com.diegoliveiraa.locadora_filme.controllers;

import com.diegoliveiraa.locadora_filme.dtos.LocationDTO;
import com.diegoliveiraa.locadora_filme.entitys.Film;
import com.diegoliveiraa.locadora_filme.entitys.Location;
import com.diegoliveiraa.locadora_filme.servives.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class LocationController {

    @Autowired
    private LocationService locationService;

    public ResponseEntity<Location> createLocation(@RequestBody LocationDTO data) throws Exception {

        Location newLocation = this.locationService.createLocation(data);
        return new ResponseEntity<>(newLocation, HttpStatus.CREATED);
    }

    @PostMapping("/devolution")
    public ResponseEntity<Location> devolutionLocation(@RequestBody LocationDTO data) throws Exception {

        Location devolutionLocation = this.locationService.devolutionLocation(data);
        return new ResponseEntity<>(devolutionLocation, HttpStatus.OK);
    }

    @RequestMapping
    public ResponseEntity<List<Location>> searchAllLocation() {

        List<Location> allLocation = this.locationService.getAllLocation();
        return new ResponseEntity<>(allLocation, HttpStatus.OK);
    }

    @RequestMapping("/search-location")
    public ResponseEntity<Location> searchLocationByRenter(@RequestBody String renter) {

        Location searchLocation = this.locationService.findLocationByRenter(renter);
        return new ResponseEntity<>(searchLocation, HttpStatus.OK);
    }

    @RequestMapping("/search-overdue-location")
    public ResponseEntity<List<Film>> searchOverdueLocation(@RequestBody LocationDTO data) {

        List<Film> overdueLocations = this.locationService.findOverdueLocations(data);

        return new ResponseEntity<>(overdueLocations, HttpStatus.OK);
    }
}

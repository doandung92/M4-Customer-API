package com.codegym.controller;

import com.codegym.model.Customer;
import com.codegym.model.Province;
import com.codegym.service.province.IProvinceService;
import com.codegym.service.province.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/provinces")
public class ProvinceController {

    @Autowired
    IProvinceService provinceService;

    @GetMapping
    public ResponseEntity<Iterable<Province>> listProvinces(){
        Iterable<Province> provinces = provinceService.findAll();
        return new ResponseEntity<>(provinces, HttpStatus.OK) ;
    }
    @GetMapping("/{id}")
    public ResponseEntity<Province> findById(@PathVariable("id") Long id){
        Province province = provinceService.findById(id);
        if (province != null) return new ResponseEntity<>(province, HttpStatus.OK) ;
        return new ResponseEntity<>(HttpStatus.NOT_FOUND) ;
    }
    @PostMapping
    public ResponseEntity<Province> createProvince(@RequestBody Province province, UriComponentsBuilder ucBuilder) {
        provinceService.save(province);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/provinces/{id}").buildAndExpand(province.getId()).toUri());
        return new ResponseEntity<>(province, headers, HttpStatus.CREATED);
    }
    @PutMapping
    public ResponseEntity<Province> saveProvince(@RequestBody Province province){
        provinceService.save(province);
        return new ResponseEntity<>(province, HttpStatus.OK) ;
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Province> deleteProvince(@PathVariable("id") Long id){
        Province province = provinceService.findById(id);
        if (province != null) {
            provinceService.deleteById(id);
            return new ResponseEntity<>(province, HttpStatus.OK) ;
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND) ;
    }
}

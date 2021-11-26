package com.app.demo.controller;

import com.app.demo.entity.CurrencyModel;
import com.app.demo.model.*;
import com.app.demo.repository.CurrencyModelRepository;
import com.app.demo.service.ApiService;
import com.app.demo.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/")
public class ApiController {

    @Autowired
    private ApiService apiService;

    @Autowired
    private CurrencyService currencyService;


    @GetMapping("/getTestData")
    public ResponseEntity<Coindesk> getTestData(){
        return new ResponseEntity<>(apiService.coindestTestAPI(), HttpStatus.OK);
    }

    @GetMapping("/coindesk")
    public ResponseEntity<CoindeskInfo> coindesk(){
        return new ResponseEntity<>(apiService.getCoinDeskInfo(), HttpStatus.OK);
    }

    @GetMapping("/query")
    public ResponseEntity<List<CurrencyModel>> query(@RequestParam(required = false) String title) {
        try {
            List<CurrencyModel> currencies = new ArrayList<CurrencyModel>();

            if (title == null)
                currencies = currencyService.query();

            if (currencies.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(currencies, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/insert")
    public ResponseEntity<CurrencyModel> insert(@RequestBody CurrencyModel currency) {
        try {
            CurrencyModel _currency = currencyService.insert(currency);
            return new ResponseEntity<>(_currency, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CurrencyModel> update(@PathVariable("id") long id, @RequestBody CurrencyModel currency) {

        CurrencyModel _currency = currencyService.update(id, currency);
        if (_currency != null) {
            return new ResponseEntity<>(_currency, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") long id) {
        try {
            currencyService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}

package com.youtube.jwt.controller;

import com.youtube.jwt.entity.Hwe_wards;
import com.youtube.jwt.entity.Product;
import com.youtube.jwt.exception.ProductNotFoundException;
import com.youtube.jwt.service.ProductService;
import com.youtube.jwt.service.WardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/ward")
public class WardController {

    @Autowired
    private ProductService productService;

    @Autowired
    private WardService wardService;

    @GetMapping("/getall")
    public ResponseEntity<List<Hwe_wards>> getWards() throws FileNotFoundException {
        return wardService.getWard();
    }

    @PostMapping("/AddWard")
    public Hwe_wards addWard(@RequestBody @Valid Hwe_wards wards){
        return wardService.addWards(wards);
    }

    @DeleteMapping("deleteWard/{id}")
    public String deleteWard(@PathVariable("id") @Valid int id ) {
        wardService.deleteWard(id);
        return "Ward successfully deleted";
    }

    @PutMapping("/updateWard/{id}")
    public Hwe_wards updateWard(@RequestBody Hwe_wards wards,
                                 @PathVariable("id") int wardId)
    {
        return wardService.updateWard(
                wards, wardId);
    }




}

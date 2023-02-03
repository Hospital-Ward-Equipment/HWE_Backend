package com.youtube.jwt.controller;

import com.youtube.jwt.entity.Hwe_equipments;
import com.youtube.jwt.payload.BrokenChartResponse;
import com.youtube.jwt.service.EquipmentService;
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
@RequestMapping("api/equipment")
public class EquipmentController {



    @Autowired
    private EquipmentService equipmentService;

    @GetMapping("/getall")
    public ResponseEntity<List<Hwe_equipments>> getEquipment() throws FileNotFoundException {
        System.out.println("Get all");
        return equipmentService.getequipment();
    }
    @GetMapping("/getBrokenDetailsForChart")
    public ResponseEntity<List<BrokenChartResponse>> getBrokenDetailsForChart() throws FileNotFoundException {
        return equipmentService.getBrokenDetailsForChart();
    }

    @GetMapping("/getallWithWard")
    public ResponseEntity<List<Hwe_equipments>> getallWithWard() throws FileNotFoundException {
        System.out.println("Get all");
        return equipmentService.getequipmentWithWard();
    }

    @PostMapping("/Addequipment")
    public Hwe_equipments addequipment(@RequestBody @Valid Hwe_equipments equipments){
        return equipmentService.addEquipment(equipments);
    }

    @DeleteMapping("deleteequipment/{id}")
    public String deleteEquipment(@PathVariable("id") @Valid int id ) {
        equipmentService.deleteEquipment(id);
        return "equipment successfully deleted";
    }

    @PutMapping("/updateequipment/{id}")
    public Hwe_equipments updateequipment(@RequestBody Hwe_equipments equipments,
                                 @PathVariable("id") int equipmentId)
    {
        return equipmentService.updateEquipment(
                equipments, equipmentId);
    }
//    @PutMapping("/updateequipmentqty/{id}/{qty}")
//    public equipment updateequipmentqty(@PathVariable("id") int productId , @PathVariable("qty") int qty)
//    {
//        //To decrease set value as minus
//        return productService.updateequipmentqty(productId,qty);
//
//    }



}

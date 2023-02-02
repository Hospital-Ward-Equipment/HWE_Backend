package com.youtube.jwt.controller;

import com.youtube.jwt.entity.Hwe_equipments;
import com.youtube.jwt.entity.Hwe_ward_equipments;
import com.youtube.jwt.payload.BrokenUsableUpdateRequest;
import com.youtube.jwt.service.WardEquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.FileNotFoundException;
import java.util.List;

@RestController
@RequestMapping("api/ward_equipment")
public class WardEquipmentsController {


    @Autowired
    private WardEquipmentService wardEquipmentService;

    @GetMapping("/getall")
    public ResponseEntity<List<Hwe_ward_equipments>> getProduct() throws FileNotFoundException {
        System.out.println("Get all");
        return wardEquipmentService.getWardEquipment();
    }

    @PostMapping("/AddWardEquipment")
    public Hwe_ward_equipments addWardEquipment(@RequestBody @Valid Hwe_ward_equipments wardEquipments){
        return wardEquipmentService.addWardEquipment(wardEquipments);
    }

    @DeleteMapping("deleteWardEquipment/{id}")
    public String deleteWardEquipment(@PathVariable("id") @Valid int id ) {
        wardEquipmentService.deleteWardEquipment(id);
        return "equipment successfully deleted";
    }

    @PutMapping("/updateWardEquipment/{id}")
    public Hwe_ward_equipments updateWardEquipment(@RequestBody Hwe_ward_equipments hwe_ward_equipments,
                                 @PathVariable("id") int WardequipmentId)
    {
        return wardEquipmentService.updateWardEquipment(
                hwe_ward_equipments, WardequipmentId);
    }
    @PutMapping("/updateEquipmentBrokenUsable")
    public long updateEquipmentBrokenUsable(@RequestBody BrokenUsableUpdateRequest request) throws FileNotFoundException {
        System.out.println("updateEquipmentBrokenUsable controller call");
        return wardEquipmentService.updateEquipmentBrokenUsable(request);
    }



}

package com.youtube.jwt.service;


import com.youtube.jwt.dao.*;
import com.youtube.jwt.dto.EquipmentResponse;
import com.youtube.jwt.entity.Hwe_equipments;
import com.youtube.jwt.entity.Hwe_ward_equipments;
import com.youtube.jwt.entity.Product;
import com.youtube.jwt.payload.ApiResponse;
import com.youtube.jwt.payload.BrokenUsableUpdateRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class WardEquipmentService {



    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private ProductRepository repo;

    @Autowired
    private EquipmentRepository equipmentRepository;

    @Autowired
    private WardEquipmentRepository wardEquipmentRepository;

    @Autowired
    private EquipmentJdbcRepository equipmentJdbcRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(WardEquipmentService.class);

    private static final String UNAUTHORISED_MESSAGE = "Authentication failed";
    @Autowired
    private WardRepository wardRepository;


    public ResponseEntity getWardEquipment() throws FileNotFoundException {

        try{
            LOGGER.info("Successfully get ward");
            return new ResponseEntity<List<Hwe_ward_equipments>>(wardEquipmentRepository.findAll(), HttpStatus.OK);
        }
        catch (Exception e){
            LOGGER.error(">>> Unable to get product");
            LOGGER.error("Context", e);
            return ResponseEntity.ok(new ApiResponse(false, UNAUTHORISED_MESSAGE));
        }

    }
    public Hwe_ward_equipments addWardEquipment(Hwe_ward_equipments wardEquipments) {

        try{
            LOGGER.info("Successfully add Ward");
            return wardEquipmentRepository.save(wardEquipments);

        }
        catch (Exception e){
            LOGGER.error(">>> Unable to add product");
            LOGGER.error("Context", e);
            return null;
        }
    }

    public String deleteWardEquipment(int id) {

        try{
            LOGGER.info("Successfully Delete ward");
            wardEquipmentRepository.deleteById((int) id);
            return "success";
        }
        catch (Exception e){
            LOGGER.error(">>> Unable to delete product");
            LOGGER.error("Context", e);
            return null;
        }

    }


    public Hwe_ward_equipments updateWardEquipment(Hwe_ward_equipments hwe_ward_equipments, int wardEquipmentId) {

        try{
            LOGGER.info(">>> Update Equipment successfully");
            Hwe_ward_equipments depDB = wardEquipmentRepository.findById(wardEquipmentId).get();

            if (Objects.nonNull(hwe_ward_equipments.getBroken()) && !"".equals(hwe_ward_equipments.getBroken())) {
                depDB.setBroken(hwe_ward_equipments.getBroken());
            }
            if (Objects.nonNull(hwe_ward_equipments.getUsable()) && !"".equals(hwe_ward_equipments.getUsable())) {
                depDB.setUsable(hwe_ward_equipments.getUsable());
            }

            return wardEquipmentRepository.save(depDB);
        }
        catch (Exception e){
            LOGGER.error(">>> Unable to update product");
            LOGGER.error("Context", e);
            return null;
        }
    }
    public long updateEquipmentBrokenUsable(BrokenUsableUpdateRequest request) throws FileNotFoundException {

        try{
            LOGGER.info(">>> Update Product successfully");
            return (equipmentJdbcRepository.updateEquipmentBrokenUsable(request));
        }
        catch (Exception e){
            LOGGER.error(">>> Unable to get product");
            LOGGER.error("Context", e);
            return 0;
        }

    }

    public List<Product> listAll() {
        return repo.findAll(Sort.by("name").ascending());
    }


}

package com.youtube.jwt.service;


import com.youtube.jwt.dao.EquipmentJdbcRepository;
import com.youtube.jwt.dao.EquipmentRepository;
import com.youtube.jwt.dao.ProductRepository;
import com.youtube.jwt.dao.WardRepository;
import com.youtube.jwt.dto.EquipmentResponse;
import com.youtube.jwt.entity.Hwe_equipments;
import com.youtube.jwt.entity.Hwe_wards;
import com.youtube.jwt.entity.Product;
import com.youtube.jwt.payload.ApiResponse;
import com.youtube.jwt.payload.BrokenChartResponse;
import com.youtube.jwt.payload.BrokenWardEquipmentChart;
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
import java.util.stream.Collectors;

@Service
@Transactional
public class EquipmentService {



    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private ProductRepository repo;

    @Autowired
    private EquipmentRepository equipmentRepository;

    @Autowired
    private EquipmentJdbcRepository equipmentJdbcRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(EquipmentService.class);

    private static final String UNAUTHORISED_MESSAGE = "Authentication failed";
    @Autowired
    private WardRepository wardRepository;


    public ResponseEntity getequipment() throws FileNotFoundException {

        try{
            LOGGER.info("Successfully get equipment details");
            return new ResponseEntity<List<Hwe_equipments>>(equipmentRepository.findAll(), HttpStatus.OK);
        }
        catch (Exception e){
            LOGGER.error(">>> Unable to get product");
            LOGGER.error("Context", e);
            return ResponseEntity.ok(new ApiResponse(false, UNAUTHORISED_MESSAGE));
        }

    }
    public ResponseEntity getBrokenDetailsForChart() throws FileNotFoundException {

        try{
            LOGGER.info("Successfully get equipment details");
            return new ResponseEntity<List<BrokenChartResponse>>(equipmentJdbcRepository.getequipmentWithWard(), HttpStatus.OK);
        }
        catch (Exception e){
            LOGGER.error(">>> Unable to get product");
            LOGGER.error("Context", e);
            return ResponseEntity.ok(new ApiResponse(false, UNAUTHORISED_MESSAGE));
        }

    }
    public ResponseEntity getBrokenCountforWard() throws FileNotFoundException {

        try{
            LOGGER.info("Successfully get equipment details");
            return new ResponseEntity<List<BrokenWardEquipmentChart>>(equipmentJdbcRepository.getBrokenCountforWard(), HttpStatus.OK);
        }
        catch (Exception e){
            LOGGER.error(">>> Unable to get product");
            LOGGER.error("Context", e);
            return ResponseEntity.ok(new ApiResponse(false, UNAUTHORISED_MESSAGE));
        }

    }

    public ResponseEntity getequipmentWithWard() throws FileNotFoundException {

        try{
            LOGGER.info("Successfully get equipment details");
//            return new ResponseEntity<List<Hwe_equipments>>(equipmentRepository.findAll(), HttpStatus.OK);
            return new ResponseEntity<List<EquipmentResponse>>(equipmentJdbcRepository.getAllEquipmentListWithWard(),HttpStatus.OK);
        }
        catch (Exception e){
            LOGGER.error(">>> Unable to get product");
            LOGGER.error("Context", e);
            return ResponseEntity.ok(new ApiResponse(false, UNAUTHORISED_MESSAGE));
        }

    }
    public Hwe_equipments addEquipment(Hwe_equipments equipments) {

        try{
            LOGGER.info("Successfully add Ward");
            return equipmentRepository.save(equipments);

        }
        catch (Exception e){
            LOGGER.error(">>> Unable to add product");
            LOGGER.error("Context", e);
            return null;
        }
    }

    public String deleteEquipment(int id) {

        try{
            LOGGER.info("Successfully delete ward");
            equipmentRepository.deleteById((int) id);
            return "success";
        }
        catch (Exception e){
            LOGGER.error(">>> Unable to delete product");
            LOGGER.error("Context", e);
            return null;
        }

    }


    public Hwe_equipments updateEquipment(Hwe_equipments equipments, int equipmentId) {

        try{
            LOGGER.info("Successfully update product");
            Hwe_equipments depDB = equipmentRepository.findById(equipmentId).get();

            if (Objects.nonNull(equipments.getName()) && !"".equalsIgnoreCase(equipments.getName())) {
                depDB.setName(equipments.getName());
            }
            if (Objects.nonNull(equipments.getCount()) && !"".equals(equipments.getCount())) {
                depDB.setCount(equipments.getCount());
            }

            return equipmentRepository.save(depDB);
        }
        catch (Exception e){
            LOGGER.error(">>> Unable to update product");
            LOGGER.error("Context", e);
            return null;
        }
    }


    public List<Product> listAll() {
        return repo.findAll(Sort.by("name").ascending());
    }


}

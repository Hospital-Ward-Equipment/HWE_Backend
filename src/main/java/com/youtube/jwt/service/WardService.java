package com.youtube.jwt.service;


import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.youtube.jwt.dao.ProductRepository;
import com.youtube.jwt.dao.WardRepository;
import com.youtube.jwt.entity.Hwe_wards;
import com.youtube.jwt.entity.Product;
import com.youtube.jwt.exception.ProductNotFoundException;
import com.youtube.jwt.payload.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional
public class WardService {

    @Autowired
    private ProductRepository repo;

    @Autowired
    private WardRepository wardRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(WardService.class);

    private static final String UNAUTHORISED_MESSAGE = "Authentication failed";



    public ResponseEntity getWard() throws FileNotFoundException {

        try{
            LOGGER.info("Successfully get ward");
            return new ResponseEntity<List<Hwe_wards>>(wardRepository.findAll(), HttpStatus.OK);
        }
        catch (Exception e){
            LOGGER.error(">>> Unable to get product");
            LOGGER.error("Context", e);
            return ResponseEntity.ok(new ApiResponse(false, UNAUTHORISED_MESSAGE));
        }

    }
    public Hwe_wards addWards(Hwe_wards wards) {

        try{
            LOGGER.info("Successfully add Ward");
            return wardRepository.save(wards);

        }
        catch (Exception e){
            LOGGER.error(">>> Unable to add product");
            LOGGER.error("Context", e);
            return null;
        }
    }

    public String deleteWard(int id) {

        try{
            LOGGER.info("Successfully delete ward");
            wardRepository.deleteById((int) id);
            return "success";
        }
        catch (Exception e){
            LOGGER.error(">>> Unable to delete product");
            LOGGER.error("Context", e);
            return null;
        }

    }


    public Hwe_wards updateWard(Hwe_wards wards, int wardId) {

        try{
            LOGGER.info("Successfully update product");
            Hwe_wards depDB = wardRepository.findById(wardId).get();

            if (Objects.nonNull(wards.getName()) && !"".equalsIgnoreCase(wards.getName())) {
                depDB.setName(wards.getName());
            }

            return wardRepository.save(depDB);
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

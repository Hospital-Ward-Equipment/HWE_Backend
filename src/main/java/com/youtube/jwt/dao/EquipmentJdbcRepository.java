package com.youtube.jwt.dao;

import com.youtube.jwt.dto.EquipmentResponse;
import com.youtube.jwt.entity.Hwe_ward_equipments;
import com.youtube.jwt.payload.BrokenChartResponse;
import com.youtube.jwt.payload.BrokenUsableUpdateRequest;
import com.youtube.jwt.payload.WardEquipmentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EquipmentJdbcRepository {

    private static final String GET_EQUIPMENT_LIST_WITH_WARD="SELECT hwe.ward_equipment_id as id ,\n" +
            "hwe.broken, hwe.usable, \n" +
            "hwe.hwe_equipments as eid ,\n" +
            "hwe.hwe_wards as wid, \n" +
            "hw.name as Wname, \n" +
            "he.name as Ename\n" +
            "FROM hwe_ward_equipments hwe\n" +
            "Inner join hwe_wards hw on hw.id=hwe.hwe_wards\n" +
            "INNER JOIN hwe_equipments he on he.id=hwe.hwe_equipments ";
    private static final String UPDATE_BROKEN_USABLE_QTY="UPDATE hwe_ward_equipments SET broken = ?, usable = ? WHERE (ward_equipment_id = ?)";
    private static final String GET_BROKEN_SUM_FOR_CHART="SELECT he.id, he.name,COALESCE(sum(hwe.broken),0) as Broken_sum,COALESCE(sum(hwe.usable),0) as Usble_sum,he.count " +
            "FROM hwe_equipments he " +
            "LEFT JOIN hwe_ward_equipments hwe " +
            "ON he.id=hwe.hwe_equipments " +
            "GROUP BY he.id";

    private static final String INSERT_DATA_TO_WARD_EQUIPMENT_TABLE="INSERT INTO hwe_ward_equipments (broken, usable, hwe_equipments, hwe_wards) VALUES (?, ?, ?, ?)";

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public List<EquipmentResponse> getAllEquipmentListWithWard() {
        System.out.println("jdbc template call");
        return jdbcTemplate
                .query(GET_EQUIPMENT_LIST_WITH_WARD,(rs, rowNum) ->
                                new EquipmentResponse(
                                        rs.getInt("id"),
                                        rs.getString("Ename"),
                                        rs.getString("Wname"),
                                        rs.getInt("eid"),
                                        rs.getInt("wid"),
                                        rs.getInt("usable"),
                                        rs.getInt("broken")
                                        )

                );
    }
    public List<BrokenChartResponse> getequipmentWithWard() {
        return jdbcTemplate
                .query(GET_BROKEN_SUM_FOR_CHART,(rs, rowNum) ->
                        new BrokenChartResponse(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getInt("Broken_sum"),
                                rs.getInt(("Usble_sum")),
                                rs.getInt("count")
                        )

                );
    }

    public Long updateEquipmentBrokenUsable(BrokenUsableUpdateRequest request) {
        MapSqlParameterSource namedParameters =new MapSqlParameterSource();

        int rowsAffected= jdbcTemplate.update(UPDATE_BROKEN_USABLE_QTY,new Object[] {request.getBroken(), request.getUsable(),request.getId()});
        return (long) rowsAffected;
    }
    public Long insertToWardEquipment(WardEquipmentRequest request) {
        int result=jdbcTemplate.update(INSERT_DATA_TO_WARD_EQUIPMENT_TABLE, new Object[]{request.getBroken(),request.getUsable(),request.getHwe_equipments(),request.getHwe_wards()});
        return (long) result;
    }
}

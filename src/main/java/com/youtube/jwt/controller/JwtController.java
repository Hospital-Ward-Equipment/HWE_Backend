package com.youtube.jwt.controller;

import com.youtube.jwt.entity.JwtRequest;
import com.youtube.jwt.entity.JwtResponse;
import com.youtube.jwt.payload.ApiResponse;
import com.youtube.jwt.service.JwtService;
import com.youtube.jwt.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import static org.hibernate.tool.schema.SchemaToolingLogging.LOGGER;

@RestController
@CrossOrigin
public class JwtController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping({"/authenticate"})
    public JwtResponse createJwtToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        return jwtService.createJwtToken(jwtRequest);
    }
    @GetMapping("/is-expired")
    public ResponseEntity<ApiResponse> isExpired( @RequestHeader("Authorization") String token) {
        try {
            if (StringUtils.hasText(token) && token.startsWith("Bearer ")) {
                String jwt = token.substring(7);
                boolean isSuccess = jwtUtil.validateToken(jwt);
                String userId = jwtUtil.getUserIdFromJWT_Pro(jwt);
                String role=jwtUtil.getFirstRole(jwt);
                System.out.println("===========================");

                System.out.println("isSuccess - "+isSuccess);
                System.out.println("userId - "+userId);
                System.out.println("name - "+role);

                System.out.println("===========================");
//                return ResponseEntity.ok(new ApiResponse(false, "Not a Valid Token"));

                if (!isSuccess) {
                    LOGGER.info(">>> Expired token");
                    return ResponseEntity.ok(new ApiResponse(true, "Expired token"));
                } else {
                    return ResponseEntity.ok(new ApiResponse(false, "Session has not expired",role));
                }
            } else {
                LOGGER.error(">>> User authentication failed.");
                return ResponseEntity.ok(new ApiResponse(true, "Not a Valid Token"));
            }
        } catch (Exception e) {
            LOGGER.info(">>> Invalid token.");
            return ResponseEntity.ok(new ApiResponse(true, "Logout Success"));
        }
    }
}

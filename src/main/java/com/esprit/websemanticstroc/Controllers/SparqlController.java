package com.esprit.websemanticstroc.Controllers;

import com.esprit.websemanticstroc.Services.SparqlService;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;

@RestController
public class SparqlController {

    @Autowired
    private SparqlService sparqlService;

    @Autowired
    public SparqlController(SparqlService sparqlService) {
        this.sparqlService = sparqlService;
    }


    public static ResponseEntity<String> convertResultSetToJson(ResultSet resultSet) {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ResultSetFormatter.outputAsJSON(outputStream, resultSet);
            String json = new String(outputStream.toByteArray());
            return new ResponseEntity<>(json, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error converting ResultSet to JSON", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/eventsWithDetails")
    public ResponseEntity<String> getEventsWithDetails() {
        ResultSet resultSet = sparqlService.getEventsWithDetails();
        return convertResultSetToJson(resultSet);
    }



    @GetMapping("/adminsWithDetails")
    public ResponseEntity<String> getAdminWithDetails() {
        ResultSet resultSet = sparqlService.getAdminsWithDetails();
        return convertResultSetToJson(resultSet);
    }


    @GetMapping("/clientsWithDetails")
    public ResponseEntity<String> getClientsWithDetails() {
        ResultSet resultSet = sparqlService.getClientsWithDetails();
        return convertResultSetToJson(resultSet);
    }

}


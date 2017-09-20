/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.controllers;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.services.BlueprintsServices;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author hcadavid
 */
@RestController
@Service
@RequestMapping(value = "/blueprints")
public class BlueprintAPIController {

    
    private BlueprintsServices blueprintServices;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> blueprintsGetManager() {
        //obtener datos que se enviarán a través del API
        Set<Blueprint> result = blueprintServices.getAllBlueprints();
        return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
    }
    
    @RequestMapping(method = RequestMethod.GET , path = "/{authorName}")
    public ResponseEntity<?> blueprintsByAuthor(@PathVariable String authorName){
        Set<Blueprint> result = null;
        try {
            result = blueprintServices.getBlueprintsByAuthor(authorName);
            return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
        } catch (BlueprintNotFoundException e) {
            //Logger.getLogger(BlueprintAPIController.class.getName()).log(Level.SEVERE, null, e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        
    }
    
    @RequestMapping(method = RequestMethod.GET , path = "/{authorName}/{bpname}")
    public ResponseEntity<?> blueprintByAuthorAndName(@PathVariable String authorName, @PathVariable String bpname){
        Blueprint result = null;
        try {
            result = blueprintServices.getBlueprint(authorName, bpname);
            return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
        } catch (BlueprintNotFoundException e) {
            //Logger.getLogger(BlueprintAPIController.class.getName()).log(Level.SEVERE, null, e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }        
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> manejadorPostRecursoXX(@RequestBody Blueprint bp) {
        try {
            //registrar dato
            blueprintServices.addNewBlueprint(bp);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (BlueprintPersistenceException e) {
            //Logger.getLogger(BlueprintAPIController.class.getName()).log(Level.SEVERE, null, e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
        }
    }

    @Autowired
    public void setBlueprintServices(BlueprintsServices blueprintServices) {
        this.blueprintServices = blueprintServices;
    }
    
    /*@RequestMapping(method = RequestMethod.PUT, path = "/{authorName}/{bpname}")
    public ResponseEntity<?> updateBlueprintPoint(@PathVariable String authorName, @PathVariable String bpname, @RequestBody Point point){
        try{
            blueprintServices.updateBlueprint(authorName, bpname, point);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (BlueprintNotFoundException e){
            Blueprint bp = new Blueprint(authorName, bpname);
            bp.addPoint(point);
            try {
                blueprintServices.addNewBlueprint(bp);
                return new ResponseEntity<>(HttpStatus.CREATED);
            } catch (BlueprintPersistenceException ex) {
                //Logger.getLogger(BlueprintAPIController.class.getName()).log(Level.SEVERE, null, ex);
                return new ResponseEntity<>(ex.getMessage(), HttpStatus.FORBIDDEN);
            }
        }
    }*/
    
    @RequestMapping(method = RequestMethod.PUT, path = "/{authorName}/{bpname}")
    public ResponseEntity<?> updateBlueprintPoints(@PathVariable String authorName, @PathVariable String bpname, @RequestBody List<Point> points){
        try{
            blueprintServices.updateBlueprint(authorName, bpname, points);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (BlueprintNotFoundException e){
            Blueprint bp = new Blueprint(authorName, bpname);
            bp.putPoints(points);
            try {
                blueprintServices.addNewBlueprint(bp);
                return new ResponseEntity<>(HttpStatus.CREATED);
            } catch (BlueprintPersistenceException ex) {
                //Logger.getLogger(BlueprintAPIController.class.getName()).log(Level.SEVERE, null, ex);
                return new ResponseEntity<>(ex.getMessage(), HttpStatus.FORBIDDEN);
            }
        }
    }

}

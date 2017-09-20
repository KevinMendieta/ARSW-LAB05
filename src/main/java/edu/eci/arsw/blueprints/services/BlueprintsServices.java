/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.services;

import edu.eci.arsw.blueprints.filters.BlueprintFilter;
import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.persistence.BlueprintsPersistence;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author hcadavid
 */
@Service
public class BlueprintsServices {
   
    @Autowired
    BlueprintsPersistence bpp = null;
    @Autowired
    BlueprintFilter bpf = null;
    
    public void addNewBlueprint(Blueprint bp) throws BlueprintPersistenceException{
        bpp.saveBlueprint(bpf.filter(bp));
        //bpp.saveBlueprint(bp);
    }
    
    public Set<Blueprint> getAllBlueprints(){
        return bpp.getAllBlueprints();
    }
    
    /**
     * 
     * @param author blueprint's author
     * @param name blueprint's name
     * @return the blueprint of the given name created by the given author
     * @throws BlueprintNotFoundException if there is no such blueprint
     */
    public Blueprint getBlueprint(String author,String name) throws BlueprintNotFoundException{
        return bpp.getBlueprint(author, name);
    }
    
    /**
     * @param author blueprint's author name.
     * @return all the blueprints of the given author.
     * @throws BlueprintNotFoundException if the given author doesn't exist
     */
    public Set<Blueprint> getBlueprintsByAuthor(String author) throws BlueprintNotFoundException{
        return bpp.getBlueprintsByAuthor(author);
    }
    
    /**
     * Update a blueprint with a given point.
     * @param author blueprint's author name.
     * @param name blueprint's name
     * @param point the new point in the blueprint. 
     * @throws BlueprintNotFoundException if the blueprint does not exist.
     */
    public void updateBlueprint(String author,String name, Point point) throws BlueprintNotFoundException{
        bpp.updateBlueprint(author, name, point);
    }
    
    /**
     * Update a blueprint with given points.
     * @param author blueprint's author name.
     * @param name blueprint's name
     * @param points List with the new points for the blueprint.
     * @throws BlueprintNotFoundException if the blueprint does not exist.
     */
    public void updateBlueprint(String author,String name, List<Point> points) throws BlueprintNotFoundException {
        bpp.updateBlueprint(author, name, points);
    }
    
}
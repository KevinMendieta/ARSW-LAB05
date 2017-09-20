/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.persistence;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import java.util.List;
import java.util.Set;

/**
 *
 * @author hcadavid
 */
public interface BlueprintsPersistence {
    
    /**
     * 
     * @param bp the new blueprint
     * @throws BlueprintPersistenceException if a blueprint with the same name already exists,
     *    or any other low-level persistence error occurs.
     */
    public void saveBlueprint(Blueprint bp) throws BlueprintPersistenceException;
    
    /**
     * 
     * @param author blueprint's author
     * @param bprintname blueprint's name
     * @return the blueprint of the given name and author
     * @throws BlueprintNotFoundException if there is no such blueprint
     */
    public Blueprint getBlueprint(String author,String bprintname) throws BlueprintNotFoundException;
    
    /**
     * 
     * @param author blueprint's author 
     * @return all the blueprints of the given author
     * @throws BlueprintNotFoundException if the given author doesn't exist
     */
    public Set<Blueprint> getBlueprintsByAuthor(String author) throws BlueprintNotFoundException;
    
    /**
     * Gets all the current blueprints on memory.
     * @return a set with all the current blueprints.
     */
    public Set<Blueprint> getAllBlueprints();
    
    /**
     * Update a blueprint with a given point.
     * @param authorName blueprint's author name.
     * @param blueprintName blueprint's name.
     * @param point the new point in the blueprint.
     * @throws BlueprintNotFoundException if the blueprint does not exist.
     */
    public void updateBlueprint(String authorName, String blueprintName, Point point) throws BlueprintNotFoundException;
    
    /**
     * Update a blueprint with given points.
     * @param authorName blueprint's author name.
     * @param blueprintName blueprint's name.
     * @param points List with the new points for the blueprint.
     * @throws BlueprintNotFoundException if the blueprint does not exist.
     */
    public void updateBlueprint(String authorName, String blueprintName, List<Point> points) throws BlueprintNotFoundException;
    
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.persistence.impl;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.persistence.BlueprintsPersistence;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Service;

/**
 *
 * @author hcadavid
 */
@Service
public class InMemoryBlueprintPersistence implements BlueprintsPersistence{

    private final Map<Tuple<String,String>,Blueprint> blueprints = new ConcurrentHashMap<>();

    public InMemoryBlueprintPersistence() {
        //load stub data
        Point[] pts=new Point[]{new Point(140, 140),new Point(115, 115), new Point(115, 115)};
        Blueprint bp=new Blueprint("juan", "INC",pts);
        blueprints.put(new Tuple<>(bp.getAuthor(),bp.getName()), bp);
        bp=new Blueprint("juan", "SAS",pts);
        blueprints.put(new Tuple<>(bp.getAuthor(),bp.getName()), bp);
        bp=new Blueprint("juan", "ARS",pts);
        blueprints.put(new Tuple<>(bp.getAuthor(),bp.getName()), bp);
        bp=new Blueprint("carl", "SOS",pts);
        blueprints.put(new Tuple<>(bp.getAuthor(),bp.getName()), bp);
        
    }    
    
    @Override
    public synchronized void saveBlueprint(Blueprint bp) throws BlueprintPersistenceException {
        if (blueprints.containsKey(new Tuple<>(bp.getAuthor(),bp.getName()))){
            throw new BlueprintPersistenceException("The given blueprint already exists: " + bp);
        }
        else{
            blueprints.put(new Tuple<>(bp.getAuthor(),bp.getName()), bp);
        }        
    }

    @Override
    public Blueprint getBlueprint(String author, String bprintname) throws BlueprintNotFoundException {
        Blueprint result = null;
        if (blueprints.containsKey(new Tuple<>(author,bprintname))){
            result = blueprints.get(new Tuple<>(author, bprintname));
        }else{
            throw new BlueprintNotFoundException("There is no blueprint with the given data: " + author + ", " + bprintname);
        }
        return result;
    }
    
    @Override
    public Set<Blueprint> getBlueprintsByAuthor(String author) throws BlueprintNotFoundException{
        Set<Blueprint> result = new LinkedHashSet<>();
        for (Tuple<String, String> tuple : blueprints.keySet()) {
            if (tuple.getElem1().equals(author)) {
                result.add(blueprints.get(tuple));
            }
        }
        if (result.isEmpty()) throw new BlueprintNotFoundException("There are no blueprints with the given author: " + author);
        return result;
    }

    @Override
    public Set<Blueprint> getAllBlueprints() {
        HashSet<Blueprint> result = new HashSet<>();
        for (Blueprint values : blueprints.values()) {
            result.add(values);
        }
        return result;
    }
    
    @Override
    public synchronized void updateBlueprint(String authorName, String blueprintName, Point point) throws BlueprintNotFoundException {
        if (blueprints.containsKey(new Tuple<>(authorName, blueprintName))) {
            blueprints.get(new Tuple<>(authorName, blueprintName)).addPoint(point);
        } else {
            throw new BlueprintNotFoundException("There is no blueprint whith the given data: " + authorName + ", " + blueprintName);
        } 
    }
    
    @Override
    public synchronized void updateBlueprint(String authorName, String blueprintName, List<Point> points) throws BlueprintNotFoundException {
        if (blueprints.containsKey(new Tuple<>(authorName, blueprintName))) {
            Blueprint bp = blueprints.get(new Tuple<>(authorName, blueprintName));
            for (Point point : points) {
                 bp.addPoint(point);
            }
        } else {
            throw new BlueprintNotFoundException("There is no blueprint whith the given data: " + authorName + ", " + blueprintName);
        }
    }

}
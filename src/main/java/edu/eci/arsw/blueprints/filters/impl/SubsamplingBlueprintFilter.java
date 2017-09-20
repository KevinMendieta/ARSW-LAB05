/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.filters.impl;

import edu.eci.arsw.blueprints.filters.BlueprintFilter;
import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author KevinMendieta
 */
//@Service
public class SubsamplingBlueprintFilter implements BlueprintFilter {

    @Override
    public Blueprint filter(Blueprint blueprint) {
        Blueprint newBlueprint = new Blueprint(blueprint.getAuthor(), blueprint.getName());
        List<Point> points = blueprint.getPoints();
        for (int i = 0; i < points.size(); i++) {
            if(i % 2 == 0) newBlueprint.addPoint(points.get(i));
        }
        return newBlueprint;
    }

}

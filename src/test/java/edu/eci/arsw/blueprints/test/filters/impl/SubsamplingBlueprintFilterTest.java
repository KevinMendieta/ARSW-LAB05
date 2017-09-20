/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.test.filters.impl;

import edu.eci.arsw.blueprints.filters.impl.SubsamplingBlueprintFilter;
import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import java.util.ArrayList;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author 2118677
 */
public class SubsamplingBlueprintFilterTest {
    
    @Test
    public void filterTest(){
        SubsamplingBlueprintFilter sbf = new SubsamplingBlueprintFilter();
        Point[] pts = new Point[]{new Point(0, 0), new Point(1, 10), new Point(1, 1), new Point(1, 1), new Point(1, 1)};
        ArrayList<Point> filteredPoints = new ArrayList<>();
        filteredPoints.add(new Point(0, 0));
        filteredPoints.add(new Point(1, 1));
        filteredPoints.add(new Point(1, 1));
        Blueprint bp = sbf.filter(new Blueprint("john", "thepaint", pts));
        for (int i = 0; i < bp.getPoints().size(); i++){
            Assert.assertTrue("Points should be filtered",filteredPoints.get(i).equals(bp.getPoints().get(i)));
        }
    }
    
}

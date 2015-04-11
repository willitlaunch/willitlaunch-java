/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package willitlaunch.controls;

import eu.hansolo.enzo.gauge.Linear;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.layout.GridPane;
import org.json.JSONObject;

/**
 *
 * @author ben
 */
public class BarsControl extends ControlBase {
    public DoubleProperty value = new SimpleDoubleProperty(0.0);
    public DoubleProperty max = new SimpleDoubleProperty(100.0);
    public DoubleProperty min = new SimpleDoubleProperty(0.0);
    
    Linear linear;
    public BarsControl(int id, double max, double min, String label) {
        super(id);
        
        this.max.set(max);
        this.min.set(min);
        
        linear = new Linear();
        linear.minValueProperty().bind(this.min);
        linear.maxValueProperty().bind(this.max);
        linear.valueProperty().bind(value);
        addGauge(linear);
        
    }

    @Override
    public void update(JSONObject obj)
    {
        double val = (double)obj.get("value");
        value.set(val);
    }
    
    @Override
    protected void configureGauge() {
        linear.setTitle("Default");
//    gauge.setUnit("°F");
//    gauge.setMinValue(32);
//    gauge.setMaxValue(212);
//    gauge.setSections(new Section(104, 140),
//                      new Section(140, 176),
//                      new Section(176, 212));
        linear.setStyle("-needle       : rgb(  0,   0, 255);" +
                       "-section0-fill: rgb(255, 0,   0);" +
                       "-section1-fill: rgb(0, 255,   0);" +
                       "-section2-fill: rgb(0,   0,   255);");

    }
    
    
    
    
    
    
}

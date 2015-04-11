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

/**
 *
 * @author ben
 */
public class TemperatureControl extends ControlBase {
    public DoubleProperty value = new SimpleDoubleProperty(0.0);
    Linear linear;
    public TemperatureControl(int id) {
        super(id);
        
        linear = new Linear();
        linear.valueProperty().bind(value);
        addGauge(linear);
        
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

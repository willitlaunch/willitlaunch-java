/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package willitlaunch.controls;

import eu.hansolo.enzo.common.Section;
import eu.hansolo.enzo.gauge.Gauge;
import eu.hansolo.enzo.gauge.Linear;
import eu.hansolo.enzo.gauge.SimpleGauge;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.layout.GridPane;

/**
 *
 * @author ben
 */
public class DialControl extends ControlBase {
    public DoubleProperty value = new SimpleDoubleProperty(0.0);
    public DoubleProperty max = new SimpleDoubleProperty(100.0);
    public DoubleProperty min = new SimpleDoubleProperty(0.0);
    
    SimpleGauge linear;
    public DialControl(int id) {
        super(id);
        
        linear = new SimpleGauge();
        linear.minValueProperty().bind(min);
        linear.maxValueProperty().bind(max);
        linear.valueProperty().bind(value);
        addGauge(linear);
        
    }

    @Override
    protected void configureGauge() {
        linear.setTitle("Default");
        linear.setUnit("°C");
        linear.setSections(new Section(104, 140),
                          new Section(140, 176),
                          new Section(176, 212));
        linear.setStyle("-needle       : rgb(  0,   0, 255);" +
                       "-section0-fill: rgb(255, 0,   0);" +
                       "-section1-fill: rgb(0, 255,   0);" +
                       "-section2-fill: rgb(0,   0,   255);");

    }
    
    
    
    
    
    
}

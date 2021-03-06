/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package willitlaunch.gauges;

import eu.hansolo.enzo.common.Section;
import eu.hansolo.enzo.gauge.Gauge;
import eu.hansolo.enzo.gauge.Linear;
import eu.hansolo.enzo.gauge.SimpleGauge;
import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.layout.GridPane;
import org.json.JSONObject;

/**
 *
 * @author ben
 */
public class DialGauge extends GaugeBase {
    public DoubleProperty value = new SimpleDoubleProperty(0.0);
    public DoubleProperty max = new SimpleDoubleProperty(100.0);
    public DoubleProperty min = new SimpleDoubleProperty(0.0);
    
    SimpleGauge linear;
    public DialGauge(int id, double max, double min, String label) {
        super(id);
        this.name.set(label);
        this.max.set(max);
        this.min.set(min);
        
        linear = new SimpleGauge();
        linear.minValueProperty().bind(this.min);
        linear.maxValueProperty().bind(this.max);
        linear.valueProperty().bind(value);
        linear.setTitle(label);
        addGauge(linear);
        this.configureGauge();
        
    }

    @Override
    protected final void configureGauge() {
        
        //linear.setUnit("");
        double diff = max.get() - min.get();
        linear.setSections(new Section(min.get(), diff/3),
                          new Section(diff/3, diff*2/3),
                          new Section(diff*2/3, max.get()));
        linear.setStyle("-needle       : rgb(  0,   0, 255);" +
                       "-section0-fill: rgb(0, 255,   0);" +
                       "-section1-fill: rgb(0, 255,   0);" +
                       "-section2-fill: rgb(0,   0,   255);");

    }
    
    @Override
    public void update(JSONObject obj)
    {
        double val = (double)obj.getDouble("Value");
        Platform.runLater(new Runnable(){
            @Override
            public void run() {
                value.set(val);
            }
        });
        
    }
    
    
    
    
}

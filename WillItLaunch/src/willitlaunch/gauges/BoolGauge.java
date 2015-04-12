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
import eu.hansolo.enzo.simpleindicator.SimpleIndicator;
import eu.hansolo.enzo.simpleindicator.SimpleIndicator.IndicatorStyle;
import eu.hansolo.enzo.simpleindicator.SimpleIndicatorBuilder;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import org.json.JSONObject;

/**
 *
 * @author ben
 */
public class BoolGauge extends GaugeBase {
    public BooleanProperty value = new SimpleBooleanProperty(true);
    public ObjectProperty<IndicatorStyle> style = new SimpleObjectProperty<IndicatorStyle>(IndicatorStyle.RED);
     
    SimpleIndicator linear;
    public BoolGauge(int id) {
        super(id);
        linear = new SimpleIndicator();
        linear.onProperty().bind(value);
        linear.indicatorStyleProperty().bind(style);
        addGauge(linear);
        
        this.setPadding(new Insets(50));
    }

    BoolGauge(int id, String label) {
        this(id); 
        this.name.set(label);
    }

    @Override
    protected void configureGauge() {
       
        
    }
    
    @Override
    public void update(JSONObject obj)
    {
        boolean val = (boolean)obj.get("value");
        value.set(val);
    }
    
    
    
    
    
}

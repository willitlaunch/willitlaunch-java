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
import eu.hansolo.enzo.simpleindicator.SimpleIndicator;
import eu.hansolo.enzo.simpleindicator.SimpleIndicator.IndicatorStyle;
import eu.hansolo.enzo.simpleindicator.SimpleIndicatorBuilder;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.layout.GridPane;

/**
 *
 * @author ben
 */
public class BoolControl extends ControlBase {
    public BooleanProperty value = new SimpleBooleanProperty(true);
    public ObjectProperty<IndicatorStyle> style = new SimpleObjectProperty<IndicatorStyle>(IndicatorStyle.RED);
     
    SimpleIndicator linear;
    public BoolControl(int id) {
        super(id);
        linear = new SimpleIndicator();
        linear.onProperty().bind(linear.pressedProperty());
        linear.indicatorStyleProperty().bind(style);
        addGauge(linear);
    }

    @Override
    protected void configureGauge() {
       
        
    }
    
    
    
    
    
    
}

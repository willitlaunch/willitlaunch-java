/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package willitlaunch.controls;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import org.json.JSONObject;
import willitlaunch.messaging.MessageManager;

/**
 *
 * @author ben
 */
public class SliderControl extends ControlBase {
    Slider slider = new Slider();
    public DoubleProperty value = new SimpleDoubleProperty(0.0);
    public DoubleProperty max = new SimpleDoubleProperty(100.0);
    public DoubleProperty min = new SimpleDoubleProperty(0.0);
    
    
    public SliderControl (int id, String label){
        super(id);
        this.setBackground(new Background(new BackgroundFill(Color.YELLOW, null, null)));
        //this.setMinSize(100, 100);
        
        //slider.textProperty().set(label);
        addControl(slider);       
    }

    SliderControl(int id, double min, double max, String label) {
        super(id);
        this.max.set(max);
        this.min.set(min);
        name.set(label);
        slider.minProperty().bind(this.min);
        slider.maxProperty().bind(this.max);
        value.bind(slider.valueProperty());
        //this.setMinSize(100, 100);
        
        value.addListener(o -> MessageManager.get().sendUpdatedValue(this));
        
        //slider.textProperty().set(label);
        addControl(slider);
    }
    
    
    
    @Override
    protected void configureGauge() {
        
    }

    @Override
    public void update(JSONObject obj) {
       
    }

    @Override
    protected void callServer() {
        
    }

    @Override
    public Object getValue() {
       return (float)value.get();
    }

    @Override
    public void setOnChangedEvent(EventHandler<? super MouseEvent> event) {
       //slider.setOnMousePressed(event);
    }
    
}

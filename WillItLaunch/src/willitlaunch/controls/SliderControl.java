/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package willitlaunch.controls;

import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import org.json.JSONObject;

/**
 *
 * @author ben
 */
public class SliderControl extends ControlBase {
    Slider slider = new Slider();
    
    public SliderControl (int id, String label){
        super(id);
        this.setBackground(new Background(new BackgroundFill(Color.YELLOW, null, null)));
        //this.setMinSize(100, 100);
        
        //slider.textProperty().set(label);
        addControl(slider);
        updated = slider.pressedProperty().asObject();
        
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
       return true;
    }

    @Override
    public void setOnChangedEvent(EventHandler<? super MouseEvent> event) {
       slider.setOnMousePressed(event);
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package willitlaunch.controls;

import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
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
public class ButtonControl extends ControlBase {
    Button button = new Button();
    
    public ButtonControl (int id, String label){
        super(id);
        value = false;
        //this.setMinSize(100, 100);       
        button.textProperty().set(label);
        addControl(button);
        
        button.setOnMousePressed(o -> {
            value = true;
            MessageManager.get().sendUpdatedValue(this);
                });
        button.setOnMouseReleased(o -> {
            value = false;
            MessageManager.get().sendUpdatedValue(this);
                });     
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
       return value;
    }

    @Override
    public void setOnChangedEvent(EventHandler<? super MouseEvent> event) {
       //button.setOnMousePressed(event);
    }
    
}

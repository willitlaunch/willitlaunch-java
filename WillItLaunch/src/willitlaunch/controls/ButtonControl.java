/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package willitlaunch.controls;

import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.scene.control.Button;
import org.json.JSONObject;

/**
 *
 * @author ben
 */
public class ButtonControl extends ControlBase {
    Button button = new Button();
    
    public ButtonControl (int id, String label){
        super(id);
        button.textProperty().set(label);
        updated = button.pressedProperty().asObject();
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
    
}

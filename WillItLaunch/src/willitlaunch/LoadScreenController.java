/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package willitlaunch;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import willitlaunch.messaging.WebSocketClientEndPoint;


/**
 *
 * @author ben
 */
public class LoadScreenController implements Initializable {
    
    @FXML
    private TextField ipBox;
    
    @FXML
    private Button connect;
    
    @FXML
    private Pane logoPane;
    
    @FXML
    private void connectTo(ActionEvent event) {
        connectToServer(ipBox.getText());
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//       Image img = new Image(getClass().getResourceAsStream("/rocket.png"),200,200,false,false);
//       
////        ImageView imgView = new ImageView(img);
//        ImageView iv = new ImageView(img);
//        
//        
//        logoPane.getChildren().add(iv);

    }    
    
    private boolean connectToServer(String ip){
        if (ip != ""){
            WillItLaunch.load(Screen.FlightControl);
        }
        return false;
    }
    
}

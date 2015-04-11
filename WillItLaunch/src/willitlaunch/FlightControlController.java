/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package willitlaunch;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author ben
 */
public class FlightControlController implements Initializable {
    @FXML
    private AnchorPane mainWindow;
    @FXML
    private ListView<?> checkList;
    @FXML
    private Label timeLabel;
    @FXML
    private Button goButton;
    @FXML
    private GridPane gaugesGrid;
    @FXML
    private GridPane controlsGrid;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    
    
}

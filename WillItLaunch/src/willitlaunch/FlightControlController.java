/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package willitlaunch;

import eu.hansolo.enzo.common.Section;
import eu.hansolo.enzo.gauge.Gauge;
import eu.hansolo.enzo.gauge.Linear;
import eu.hansolo.enzo.gauge.RadialBargraph;
import eu.hansolo.enzo.gauge.RadialSteelGauge;
import eu.hansolo.enzo.gauge.SimpleGauge;
import java.net.URL;
import java.util.HashMap;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javax.swing.SwingUtilities;
import org.json.JSONObject;
import willitlaunch.controls.ButtonControl;
import willitlaunch.controls.ControlBase;
import willitlaunch.gauges.BarsGauge;
import willitlaunch.gauges.DialGauge;
import willitlaunch.gauges.GaugeBase;
import willitlaunch.messaging.MessageManager;

/**
 * FXML Controller class
 *
 * @author ben
 */
public class FlightControlController implements Initializable {
    MessageManager msg = new MessageManager(this);
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
    @FXML
    private Label controllerTitle;

    public HashMap<Integer,GaugeBase> outputGaugeMap = new HashMap<Integer,GaugeBase>();
    public HashMap<Integer,ControlBase> outputControlMap = new HashMap<Integer,ControlBase>();
    public StringProperty title = new SimpleStringProperty();
    private DoubleProperty gaugeValue = new SimpleDoubleProperty(0.0);
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        controllerTitle.textProperty().bind(title);
        msg.listen();  
    }   
    
        
    public void createGauge(GaugeBase gauge){
        outputGaugeMap.put(gauge.id, gauge);
        int row = (int)Math.floor(outputGaugeMap.keySet().size()/4.0);
        int col = outputGaugeMap.keySet().size() % 4;
         Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        createGauge(gauge, row, col);
                    }
                });
        
    }    
    
    public void createGauge(GaugeBase gauge,int row,int col){
        try{
        setGaugeDefaults(gauge);
        GridPane.setConstraints(gauge, col, row);
        gaugesGrid.getChildren().add(gauge);
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void createControl(ControlBase control){
        outputControlMap.put(control.id, control);
        int row = (int)Math.floor(outputControlMap.keySet().size()/4.0);
        int col = outputControlMap.keySet().size() % 4;
         Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        createControl(control, row, col);
                    }
                });
        
    }    
    
    public void createControl(ControlBase control,int row,int col){
        try{
            setControlDefaults(control);
            GridPane.setConstraints(control, col, row);
            controlsGrid.getChildren().add(control);
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void updateGauge(int id, JSONObject object)
    {
        GaugeBase widget = outputGaugeMap.get(id);
        if (widget != null) widget.update(object);      
    }
    
    private void setGaugeDefaults(Region gauge) {
//    gauge.setTitle("Default");
//    gauge.setUnit("°F");
//    gauge.setMinValue(32);
//    gauge.setMaxValue(212);
        

    }
    
    private void setControlDefaults(Region control) {
//    gauge.setTitle("Default");
//    gauge.setUnit("°F");
//    gauge.setMinValue(32);
//    gauge.setMaxValue(212);
//    gauge.setSections(new Section(104, 140),
//                      new Section(140, 176),
//                      new Section(176, 212));
        control.setStyle("-needle       : rgb(  0,   0, 255);" +
                       "-section0-fill: rgb(255, 0,   0);" +
                       "-section1-fill: rgb(0, 255,   0);" +
                       "-section2-fill: rgb(0,   0,   255);");

    }
    
}

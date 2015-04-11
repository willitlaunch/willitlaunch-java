/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package willitlaunch;

import eu.hansolo.enzo.gauge.Gauge;
import eu.hansolo.enzo.gauge.Linear;
import eu.hansolo.enzo.gauge.RadialBargraph;
import eu.hansolo.enzo.gauge.RadialSteelGauge;
import eu.hansolo.enzo.gauge.SimpleGauge;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
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
import willitlaunch.controls.TemperatureControl;

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

    private DoubleProperty gaugeValue = new SimpleDoubleProperty(0.0);
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Gauge gauge = new Gauge();
        gauge.valueProperty().bind(gaugeValue);
        createGauge(gauge,0,0);
        Linear linear = new Linear();
        linear.valueProperty().bind(gaugeValue);
        createGauge(linear,0,1);
        SimpleGauge simpleGauge = new SimpleGauge();
        simpleGauge.valueProperty().bind(gaugeValue);
        createGauge(simpleGauge,0,2);
        RadialSteelGauge rsGauge = new RadialSteelGauge();
        rsGauge.valueProperty().bind(gaugeValue);
        createGauge(rsGauge,0,3);
        RadialBargraph rbg = new RadialBargraph();
        rbg.valueProperty().bind(gaugeValue);
        createGauge(rbg,1,0);
        SimpleGauge sGauge = new SimpleGauge();
        sGauge.valueProperty().bind(gaugeValue);
        createGauge(sGauge,1,1);
        TemperatureControl testControl = new TemperatureControl(2);
        testControl.value.bind(gaugeValue);
        testControl.name.set("Temperature");
        createGauge(testControl, 1, 2);
        runGauges();
    }   
    
    public void runGauges(){
        Random rand = new Random();
        int i = 0;
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        gaugeValue.set(rand.nextDouble()*100);
                    }
                });
            }
        }, 0, 2000);
    }
    
    public void createGauge(Region gauge,int row,int col){
        
        setGaugeDefaults(gauge);
        GridPane.setConstraints(gauge, col, row);
        gaugesGrid.getChildren().add(gauge);
    }
    
    private void setGaugeDefaults(Region gauge) {
//    gauge.setTitle("Default");
//    gauge.setUnit("°F");
//    gauge.setMinValue(32);
//    gauge.setMaxValue(212);
//    gauge.setSections(new Section(104, 140),
//                      new Section(140, 176),
//                      new Section(176, 212));
        gauge.setStyle("-needle       : rgb(  0,   0, 255);" +
                       "-section0-fill: rgb(255, 0,   0);" +
                       "-section1-fill: rgb(0, 255,   0);" +
                       "-section2-fill: rgb(0,   0,   255);");

    }
    
}

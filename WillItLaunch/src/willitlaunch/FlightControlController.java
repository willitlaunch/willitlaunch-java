/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package willitlaunch;

import Utils.AnchorPaneUtils;
import eu.hansolo.enzo.clock.Clock;
import eu.hansolo.enzo.common.Section;
import eu.hansolo.enzo.gauge.Gauge;
import eu.hansolo.enzo.gauge.Linear;
import eu.hansolo.enzo.gauge.RadialBargraph;
import eu.hansolo.enzo.gauge.RadialSteelGauge;
import eu.hansolo.enzo.gauge.SimpleGauge;
import eu.hansolo.enzo.sevensegment.SevenSegment;
import java.net.URL;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
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
    MessageManager msg = new MessageManager(this, WillItLaunch.url);
    @FXML
    private AnchorPane mainWindow;
    @FXML
    public ListView<String> checkList;
    @FXML
    private AnchorPane timeLabel;
    @FXML
    private GridPane gaugesGrid;
    @FXML
    private GridPane controlsGrid;
    @FXML
    private Label controllerTitle;
    @FXML
    private StackPane stackPane;

    public ObservableList<String> rules = FXCollections.observableArrayList();
    public HashMap<Integer,GaugeBase> outputGaugeMap = new HashMap<Integer,GaugeBase>();
    public HashMap<Integer,ControlBase> outputControlMap = new HashMap<Integer,ControlBase>();
    public StringProperty title = new SimpleStringProperty();
    private DoubleProperty gaugeValue = new SimpleDoubleProperty(0.0);
    public ObjectProperty<LocalTime> timeLeft = new SimpleObjectProperty<LocalTime>();
    HBox goNogoPane = new HBox();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        controllerTitle.textProperty().bind(title);
        msg.listen(); 
        setUpTimeCounterAsClock();
        checkList.setItems(rules);
    }   
    
    private void setUpTimeCounterAsClock(){
        Clock timeCounter = new Clock(timeLeft.get());
        timeCounter.timeProperty().bind(timeLeft);
        timeCounter.setSecondPointerVisible(true);
        timeCounter.setDesign(Clock.Design.DB);
        timeCounter.setOnMousePressed(event -> enablePollMode());
        timeCounter.setOnMouseReleased(event -> disablePollMode());
        gaugesGrid.setStyle("-fx-background-color:#404040;");
        controlsGrid.setStyle("-fx-background-color:#252525;");
        
        timeLabel.getChildren().setAll(timeCounter);
        AnchorPaneUtils.setAnchors(timeCounter, 0.0, 0.0, 0.0, 0.0);
    }
        
    public void setName(String name)
    {
        Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        title.set(name);
                    }
                });
    }
    
    public void createGauge(GaugeBase gauge){
        outputGaugeMap.put(gauge.id, gauge);
        int tid = outputGaugeMap.keySet().size() - 1;
        int row = (int)Math.floor(tid/4.0);
        int col = tid % 4;
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
        int tid = outputControlMap.keySet().size() - 1;
        int row = (int)Math.floor(tid/4.0);
        int col = tid % 4;
         Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        createControl(control, row, col);
                    }
                });   
    }  
    
    public void hideControls()
    {
        Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                          controlsGrid.setVisible(false);
                    }
                });   
      
    }
    
    public void showControls()
    {
        Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        controlsGrid.setVisible(true);
//                        outputControlMap.keySet().forEach(id -> {
//                            ControlBase control = outputControlMap.get(id);
//                            gaugesGrid.getChildren().add(control);                            
//                        });
                    }
                });        
    } 
    
    public void enablePollMode()
    {
        hideControls();  
        showGoNoGo();
    }
    
    public void disablePollMode()
    {
        showControls();
        hideGoNoGo();
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
    
    public void updateGauge(int id, JSONObject object){
        GaugeBase widget = outputGaugeMap.get(id);
        if (widget != null) widget.update(object);      
    }
    
    public void updateClock(int value)
    {
                 Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                         timeLeft.set(LocalTime.of(1, 1, value));
                    }
                });  
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
    
    public void showGoNoGo(){
        goNogoPane.getChildren().clear();
        goNogoPane.setPrefSize(3000,300);
        Button go = new Button("Go");
        Button no = new Button("No Go");
        go.setPrefSize(1500, 300);
        no.setPrefSize(1500, 300);
        goNogoPane.getChildren().addAll(go,no);
        stackPane.getChildren().add(goNogoPane);
        goNogoPane.toFront();
        go.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        no.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));

        
    }
    
    public void hideGoNoGo(){
        stackPane.getChildren().remove(goNogoPane);
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package willitlaunch.controls;

import Utils.AnchorPaneUtils;
import java.util.Random;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.text.TextAlignment;
import org.json.JSONObject;
import willitlaunch.gauges.BarsGauge;
import willitlaunch.gauges.BoolGauge;
import willitlaunch.gauges.DialGauge;
import willitlaunch.gauges.GaugeType;

/**
 *
 * @author ben
 */
public abstract class ControlBase extends StackPane {
    public static Random rand = new Random();
    //private static Object ControlType;
    public int id = rand.nextInt();
    public StringProperty name = new SimpleStringProperty();
    public Label title = new Label();
    public Object value;
    public ReadOnlyObjectProperty<?> updated = new SimpleObjectProperty<>();
    
    
    public ControlBase(int id){
        this.id = id;
        title.getStyleClass().add("item-title");
        title.textProperty().bind(name);
        this.getChildren().add(title);
        StackPane.setAlignment(title, Pos.TOP_CENTER);
        this.setStyle("-fx-background-color:#505050;-fx-margin:5");
        
        title.setTextAlignment(TextAlignment.CENTER);
        title.setAlignment(Pos.CENTER);             
    }
    
    public abstract void setOnChangedEvent(EventHandler<? super MouseEvent> event);
    
    public abstract Object getValue();
    
    protected abstract void configureGauge();
    
    protected void addControl(Region region){
        //AnchorPaneUtils.setAnchors(region, 0.0, null, 0.0, null);
        this.getChildren().add(region);
        //region.setPadding(new Insets(0));
        StackPane.setAlignment(region, Pos.CENTER);
        region.toFront();
    }
    
    public static ControlBase createControl(JSONObject obj)    {
        int id = (int)obj.get("Wid") + ((int)obj.get("Gid")*100);
        ControlType type = Enum.valueOf(ControlType.class, obj.get("Style").toString());
        String label = obj.get("Label").toString();
        if (type == ControlType.button) return new ButtonControl(id, label);        
        if (type == ControlType.slider) return new SliderControl(id, (double)obj.getDouble("Min"), (double)obj.getDouble("Max"), label);
//        if (type == OutputType.bars) return new BoolControl(id, label);
        return null;
    }
    
    public abstract void update(JSONObject obj);
    protected abstract void callServer();
    
}

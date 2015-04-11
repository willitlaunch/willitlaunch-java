/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package willitlaunch.gauges;

import Utils.AnchorPaneUtils;
import java.util.Random;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.text.TextAlignment;
import org.json.JSONObject;
import willitlaunch.controls.BarsControl;
import willitlaunch.controls.BoolControl;
import willitlaunch.controls.DialControl;
import willitlaunch.controls.OutputType;

/**
 *
 * @author ben
 */
public abstract class GaugeBase extends StackPane {
    public static Random rand = new Random();
    public int id = rand.nextInt();
    public StringProperty name = new SimpleStringProperty();
    public Label title = new Label();
    
    
    
    public GaugeBase(int id){
        title.getStyleClass().add("item-title");
        title.textProperty().bind(name);
        this.getChildren().add(title);
        StackPane.setAlignment(title, Pos.TOP_CENTER);
       
        title.setTextAlignment(TextAlignment.CENTER);
        title.setAlignment(Pos.CENTER);
        
    }
    
    protected abstract void configureGauge();
    
    protected void addControl(Region region){
        AnchorPaneUtils.setAnchors(region, 0.0, null, 0.0, null);
        this.getChildren().add(region);
        //region.setPadding(new Insets(0));
        StackPane.setAlignment(region, Pos.BOTTOM_CENTER);
        region.toBack();
    }
    
    
    public static GaugeBase createControl(JSONObject obj)
    {
        int id = (int)obj.get("id");
        OutputType type = Enum.valueOf(OutputType.class, obj.get("type").toString());
        String label = obj.get("label").toString();
//        if (type == OutputType.bars) return new BarsControl(id, (double)obj.get("max"), (double)obj.get("min"), label);
//        if (type == OutputType.dial) return new DialControl(id, (double)obj.get("max"), (double)obj.get("min"), label);
//        if (type == OutputType.bars) return new BoolControl(id, label);
        return null;
    }
    
    public abstract void update(JSONObject obj);
    protected abstract void callServer();
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package willitlaunch.gauges;

import Utils.AnchorPaneUtils;
import com.sun.org.apache.xpath.internal.operations.Bool;
import java.util.Random;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.text.TextAlignment;
import org.json.JSONObject;

/**
 *
 * @author ben
 */
public abstract class GaugeBase extends StackPane{
    public static Random rand = new Random();
    public int id = rand.nextInt();
    public StringProperty name = new SimpleStringProperty();
    public Label title = new Label();
    
    
    
    public GaugeBase(int id){
        this.id = id;
        title.getStyleClass().add("item-title");
        title.textProperty().bind(name);
        this.getChildren().add(title);
        StackPane.setAlignment(title, Pos.TOP_CENTER);
       
        title.setTextAlignment(TextAlignment.CENTER);
        title.setAlignment(Pos.CENTER);     
    }
    
    protected abstract void configureGauge();
    
    protected void addGauge(Region region){
        AnchorPaneUtils.setAnchors(region, 50.0, null, 0.0, null);
        this.getChildren().add(region);
        region.setPadding(new Insets(25));
        StackPane.setAlignment(region, Pos.CENTER);
        region.toBack();
    }
    
    
    public static GaugeBase createGauge(JSONObject obj)
    {
        int id = (int)obj.get("Wid") + ((int)obj.get("Gid")*100);
        GaugeType type = Enum.valueOf(GaugeType.class, obj.get("Style").toString());
        String label = obj.get("Label").toString();
        //if (type == OutputType.bars) return new BarsControl(id, (double)obj.get("max"), (double)obj.get("min"), label);
        if (type == GaugeType.dial) return new DialGauge(id, obj.getDouble("Max"), obj.getDouble("Min"), label);
        if (type == GaugeType.bool) return new BoolGauge(id, label);
        return null;
    }
    
    public abstract void update(JSONObject obj);
}

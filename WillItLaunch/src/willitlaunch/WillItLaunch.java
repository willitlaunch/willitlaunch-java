/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package willitlaunch;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 *
 * @author ben
 */
public class WillItLaunch extends Application {
    
    private static Stage stage = null;
    
    public static String url;
    
    public static void load(Screen screenType){
        try {
          
//        scene.getStylesheets().clear();
//        scene.getStylesheets().add(css);
 
            Parent rootScreen = FXMLLoader.load(WillItLaunch.class.getResource(screenType.getPath()));
            Scene scene = new Scene(rootScreen);
            scene.getStylesheets().add(WillItLaunch.class.getResource("/jfxtras/styles/jmetro8/JMetroDarkTheme.css").toExternalForm());
            
            stage.setScene(scene);
            stage.show();
        } catch (IOException e){
            System.out.println("Cannot load screen "+e.getMessage());
        }
    }
    
    
    
    @Override
    public void start(Stage stage) throws Exception {
        
       // String css = WillItLaunch.class.getResource("/jarcss.css").toExternalForm();
        this.stage = stage;
        
        load(Screen.Load);
        //root.setStyle(STYLESHEET_MODENA);
//        scene.getStylesheets().clear();
//        scene.getStylesheets().add(css);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}

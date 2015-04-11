/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package willitlaunch;

/**
 *
 * @author ben
 */
public enum Screen {
    Load("LoadScreen.fxml"),FlightControl("FlightControl.fxml");
    private String path = "";
    private Screen(String path){
        this.path = path;
    }
    
    public String getPath(){
        return path;
    }
}

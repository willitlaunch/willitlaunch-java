/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package willitlaunch.controls;

import javafx.scene.layout.Region;

/**
 *
 * @author ben
 */
public abstract class BaseControl {
    
    private Region region;
    
    public abstract void create();
    public abstract void redraw();  
    
    public Region getRegion()
    {
        return region;
    }
}

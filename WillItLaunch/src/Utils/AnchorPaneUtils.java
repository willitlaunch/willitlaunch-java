/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Utils;

import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author ben
 */
public class AnchorPaneUtils {
    public static void setAnchors(Node child,Double top, Double right, Double bottom,Double left){
        AnchorPane.setBottomAnchor(child, bottom);
        AnchorPane.setLeftAnchor(child, left);
        AnchorPane.setRightAnchor(child, right);
        AnchorPane.setTopAnchor(child, top);
    }
}

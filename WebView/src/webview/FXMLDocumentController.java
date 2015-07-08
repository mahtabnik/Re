/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webview;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.web.WebView;

/**
 * FXML Controller class
 *
 * @author mahtabnik
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private WebView web1;
    @FXML
    private WebView web2;
    @FXML
    private Button next;
    
    int i = 0;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Close(web2, web1);
        
        web1.getEngine().load(getUrl(0, 0, "id", i));
        
        next.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                Close(web1, web2);
                i++;
                web2.getEngine().load(getUrl(0, 0, "id", i));
                
            }
        });
        
    }
    
    public String getUrl(int Cat, int userType, String fxId, int html) {
        String url = "";
        try {
            String path = System.getProperty("user.dir");
            path.replace("\\\\", "/");
            path += "/Recources/Cat" + Cat + "/userType" + userType + "/" + html + ".html";
            url = new URL("file:///" + path).toExternalForm();
        } catch (MalformedURLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return url;
    }
    
    public void Close(WebView web1, WebView web2) {
        web1.setVisible(false);
        web2.setVisible(true);
        
    }
    
}

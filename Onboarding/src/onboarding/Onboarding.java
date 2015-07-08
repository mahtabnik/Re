/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package onboarding;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Farzaneh
 */
public class Onboarding extends Application {

    ArrayList<Map> mapList;
    ArrayList<ArrayList<String>> categoryIds;
    Button next ;
    //Map map  = mapList.get(0) = yani category 0
    //String listofHtmls = map.get("tf1") listofHtmls yek directory ast ke dar an html haye marboot be node "tf1" rikhte shode ast .

    ArrayList<String[]> catPriority;

    @Override
    public void start(Stage primaryStage) {
        mapList = new ArrayList<>();
        catPriority = new ArrayList<>();
        categoryIds = new ArrayList<>();
        Properties properties = loadFile("config.properties");

        // CatNum = tedade kolle category ha 
        String catNumber = properties.getProperty("CatNum");
        int catNum = Integer.parseInt(catNumber);

        for (int i = 0; i < catNum; i++) {
            Map category = createategory(i, properties);
            mapList.add(i, category);

        }

        Properties properties2 = loadFile("config2.properties");
        String userTypeNumber = properties2.getProperty("userType");
        int userTypeNum = Integer.parseInt(userTypeNumber);

        for (int i = 0; i < userTypeNum; i++) {
            String property = properties2.getProperty("userType" + "" + i);
            String[] split = property.split(",");
            catPriority.add(i, split);

        }
        
        yek laye ye transparent rooye kolle scene add kon .
                webview ra dar noghte y (x,y) add kon
                        webview ra be node fx_id bind kon 
        
        
        next.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
        
            loadNextHtml() ;
            }
        });
        
        if(state of all animatedBox == 3 ){
        show cangrat message  !
        go to next Category ! 
    }
        
        StackPane root = new StackPane();

        Scene scene = new Scene(root, 300, 250);

        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public Map createategory(int i, Properties properties) {
        String catiNumber = properties.getProperty("cat" + "" + i + "Num");

        int catiNum = Integer.parseInt(catiNumber);
        Map<String, String> map = new TreeMap<>();
        ArrayList<String> list = new ArrayList<>();
        for (int j = 0; j < catiNum; j++) {
            String s = properties.getProperty("cat" + "" + i + "" + j);
            String[] splited = s.split(",");
            map.put(splited[0], splited[1]);
            list.add(splited[0]);
        }
        categoryIds.add(i, list);
        return map;
    }

    public void load(int userType) {
        String[] cat = catPriority.get(userType);

        //be tartib category ha ra load kon
        // har category yek map ast 
        //dar har category key=fx_id , value= directory_name of htmls for node with fx_id
        for (int i = 0; i < cat.length; i++) {
        int   catToload =  Integer.parseInt(cat[i]) ;
            Map map = mapList.get(catToload);
            
            for(String s : categoryIds.get(catToload)){
                //Node haye category ra faAl kon
            }
            
            

        }
    }
    
    public void loadNextHtml(){
        if(nextHtml){
            load() ;
        }
        else{
            animatedBox.state =  3 ;
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);

    }

    private Properties loadFile(String fileName) {
        Properties prop = new Properties();
        InputStream input = null;

        try {
            input = new FileInputStream(fileName);
            prop.load(input);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AnimatedBox.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AnimatedBox.class.getName()).log(Level.SEVERE, null, ex);
        }
        return prop;
    }

}

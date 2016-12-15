/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imageviewer;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author ricka
 */
public class ImageViewer extends Application {
    
    private static int currIndex;
    
    @Override
    public void start(Stage primaryStage) {
        GridPane root = new GridPane();
        
        currIndex = 0;
        
        Image image1 = new Image("A.png");
        Image image2 = new Image("B.png");
        Image image3 = new Image("K.png");
        
        ArrayList<Image> imageList = new ArrayList<Image>();
        
        imageList.add(image1);
        imageList.add(image2);
        imageList.add(image3);
        
        ImageView iv1 = new ImageView();
        iv1.setImage(image1);
        iv1.setFitWidth(200);
        iv1.setPreserveRatio(true);
        iv1.setSmooth(true);
        iv1.setCache(true);
        
        Button nextBtn = new Button();
        nextBtn.setText(">");
        nextBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                currIndex++;
                currIndex = currIndex > imageList.size() - 1 ? 0 : currIndex;
                
                iv1.setImage(imageList.get(currIndex));
            }
        });
        
        Button prevBtn = new Button();
        prevBtn.setText("<");
        prevBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                currIndex--;
                currIndex = currIndex < 0 ? imageList.size() - 1 : currIndex;
                
                iv1.setImage(imageList.get(currIndex));
            }
        });
        
        Button scaleUpBtn = new Button();
        scaleUpBtn.setText("+");
        scaleUpBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                iv1.setScaleX(iv1.getScaleX() * 1.5);
                iv1.setScaleY(iv1.getScaleY() * 1.5);
            }
        });
        
        Button scaleDownBtn = new Button();
        scaleDownBtn.setText("-");
        scaleDownBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                iv1.setScaleX(iv1.getScaleX() * 0.5);
                iv1.setScaleY(iv1.getScaleY() * 0.5);
            }
        });
        
        Button rotateRightBtn = new Button();
        rotateRightBtn.setText("+90");
        rotateRightBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                iv1.setRotate(iv1.getRotate() + 90);
            }
        });
        
        Button rotateLeftBtn = new Button();
        rotateLeftBtn.setText("-90");
        rotateLeftBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                iv1.setRotate(iv1.getRotate() - 90);
            }
        });
        
        HBox btnGroup = new HBox();
        
        btnGroup.setSpacing(0); 
        btnGroup.getChildren().addAll(prevBtn, scaleUpBtn, scaleDownBtn, rotateRightBtn, rotateLeftBtn, nextBtn);
        root.setAlignment(Pos.CENTER);
        
        root.add(iv1, 0, 0);
        root.add(btnGroup, 0, 1);
        
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("Image viewer!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}

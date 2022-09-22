package main.Utilities;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import static main.controller.Login.labels;

/**
 * Class for returning the stage where the different application popups
 */
public class PopupBox {

    private final Stage popupStage;

    /**
     * @lambda The Lambda expression here is used to assign a function to a javafx button when it is clicked
     * @param type The type of pop up to return
     */
    public PopupBox(String type){

        FlowPane dPlane = new FlowPane();

        dPlane.setPadding(new Insets(20));
        dPlane.setVgap(20);
        dPlane.setStyle("-fx-background: white;");

        Scene popupScene = new Scene(dPlane, 500, 200);

        this.popupStage = new Stage();

        //Popup for displaying error message on incorrect user login
        if (type == "login"){

            //Buttons and Label for Popup
            Button yes = new Button(labels.getString("label4"));
            yes.setTranslateX(210);

            Label errorMessage = new Label(labels.getString("label5"));
            errorMessage.setMinWidth(500);
            errorMessage.setMinHeight(50);
            errorMessage.setStyle("-fx-font: 24 arial;");

            dPlane.getChildren().addAll();

            //Action to take when the "yes" button is pressed
            yes.setOnAction(e -> popupStage.close());

            dPlane.getChildren().addAll(errorMessage,yes);

            this.popupStage.setScene(popupScene);

        }

    }

    /**
     * @return the popup
     */
    public Stage getPopupStage() {
        return popupStage;
    }
}

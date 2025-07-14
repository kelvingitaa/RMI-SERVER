package client;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class LoginScreen extends Application {

    @Override
    public void start(Stage stage) {
        Label title = new Label("Login");
        title.setStyle("-fx-font-size: 22px; -fx-text-fill: red;");

        TextField userFld = new TextField("admin");
        userFld.setPromptText("Username");

        PasswordField passFld = new PasswordField();
        passFld.setText("1234");

        TextField showPass = new TextField("1234");
        showPass.setPromptText("Password");

        // Show/hide fields via CheckBox
        CheckBox showChk = new CheckBox("Show Password");
        showChk.setOnAction(e -> {
            boolean show = showChk.isSelected();
            showPass.setVisible(show);
            showPass.setManaged(show);
            passFld.setVisible(!show);
            passFld.setManaged(!show);
        });
        // Initialize visibility
        showPass.setVisible(false);
        showPass.setManaged(false);

        Label demo = new Label("Demo: admin / 1234");
        demo.setStyle("-fx-text-fill: #888; -fx-font-size: 11px;");

        Label err = new Label();
        err.setStyle("-fx-text-fill: red;");

        Button btn = new Button("Login");
        btn.setDefaultButton(true);
        btn.setOnAction(e -> {
            String u = userFld.getText().trim();
            String p = passFld.getText().trim();
            if (u.equals("admin") && p.equals("1234")) {
                new Dashboard().start(stage);
            } else {
                err.setText("Invalid username or password.");
            }
        });

        VBox form = new VBox(8,
            title,
            userFld,
            passFld,
            showPass,
            showChk,
            btn,
            err,
            demo
        );
        form.setPadding(new Insets(30));
        form.setStyle("-fx-background-color: #f0f0f0; -fx-border-radius: 8px; -fx-background-radius: 8px;");
        form.setMaxWidth(300);

        BorderPane root = new BorderPane(form);
        root.setPadding(new Insets(20));
        stage.setScene(new Scene(root, 600, 400));
        stage.setTitle("Tenant System - Login");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

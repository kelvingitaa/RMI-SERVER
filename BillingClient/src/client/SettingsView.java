package client;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class SettingsView {

    public VBox getView() {
        Label heading = new Label("Settings");
        heading.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        // Account Info
        TextField usernameField = new TextField("admin");
        usernameField.setEditable(false);
        ComboBox<String> roleBox = new ComboBox<>(FXCollections.observableArrayList("Admin", "Clerk"));
        roleBox.setValue("Admin");

        VBox accountSection = new VBox(10,
            new Label("Account Information"),
            usernameField,
            roleBox
        );
        accountSection.setPadding(new Insets(10));
        accountSection.setStyle("-fx-background-color: #f7f7f7; -fx-border-color: #ccc;");

        // Change Password
        PasswordField oldPass = new PasswordField(); oldPass.setPromptText("Old Password");
        PasswordField newPass = new PasswordField(); newPass.setPromptText("New Password");
        PasswordField confirmPass = new PasswordField(); confirmPass.setPromptText("Confirm New Password");

        VBox passwordSection = new VBox(10,
            new Label("Change Password"),
            oldPass,
            newPass,
            confirmPass
        );
        passwordSection.setPadding(new Insets(10));
        passwordSection.setStyle("-fx-background-color: #f7f7f7; -fx-border-color: #ccc;");

        Button saveBtn = new Button("Save Changes");
        Label statusLabel = new Label();
        saveBtn.setOnAction(e -> {
            if (!newPass.getText().equals(confirmPass.getText())) {
                statusLabel.setText("New passwords do not match.");
                statusLabel.setStyle("-fx-text-fill: red;");
            } else {
                statusLabel.setText("Settings saved!");
                statusLabel.setStyle("-fx-text-fill: green;");
            }
        });

        HBox buttonBox = new HBox(saveBtn);
        buttonBox.setAlignment(Pos.CENTER);

        VBox layout = new VBox(20,
            heading,
            accountSection,
            passwordSection,
            buttonBox,
            statusLabel
        );
        layout.setPadding(new Insets(20));
        return layout;
    }
}

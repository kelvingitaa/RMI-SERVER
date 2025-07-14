package client;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import rmi.BillingInterface;
import rmi.Tenant;

import java.rmi.Naming;
import java.util.List;

public class Dashboard {

    private BorderPane root;

    public void start(Stage stage) {
        root = new BorderPane();
        root.setLeft(createSidebar());
        root.setCenter(createDashboardView());

        Scene scene = new Scene(root, 1000, 600);
        stage.setTitle("Tenant Billing Dashboard");
        stage.setScene(scene);
        stage.show();
    }

    private VBox createSidebar() {
        Button btnDashboard = new Button("Dashboard");
        Button btnTenants   = new Button("Tenants");
        Button btnSettings  = new Button("Settings");

        for (Button b : new Button[]{btnDashboard, btnTenants, btnSettings}) {
            b.setMaxWidth(Double.MAX_VALUE);
            b.setStyle("-fx-background-color: transparent; -fx-text-fill: white; -fx-font-size: 14px;");
        }

        btnDashboard.setOnAction(e -> root.setCenter(createDashboardView()));
        btnTenants  .setOnAction(e -> root.setCenter(new TenantManagerView().getView()));
        btnSettings .setOnAction(e -> root.setCenter(new SettingsView().getView()));

        VBox sidebar = new VBox(15, btnDashboard, btnTenants, btnSettings);
        sidebar.setPadding(new Insets(20));
        sidebar.setStyle("-fx-background-color: #2c3e50;");
        return sidebar;
    }

    private VBox createDashboardView() {
        VBox container = new VBox(15);
        container.setPadding(new Insets(20));

        Label title = new Label("Dashboard Summary");
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        try {
            BillingInterface service = (BillingInterface) Naming.lookup("rmi://192.168.232.57/BillingService");
            List<Tenant> tenants = service.getAllTenants();

            int totalTenants = tenants.size();
            double totalPaid = tenants.stream()
                                      .mapToDouble(t -> t.getPaidAmount())
                                      .sum();
            double totalRent = tenants.stream()
                                      .mapToDouble(t -> t.getRentAmount())
                                      .sum();
            double totalUnpaid = totalRent - totalPaid;

            HBox metrics = new HBox(20,
                createMetricCard("Total Tenants",      String.valueOf(totalTenants)),
                createMetricCard("Total Rent Collected","KES " + totalPaid),
                createMetricCard("Unpaid Balance",      "KES " + totalUnpaid)
            );

            container.getChildren().addAll(title, metrics);

        } catch (Exception e) {
            Label error = new Label("Error loading dashboard: " + e.getMessage());
            error.setStyle("-fx-text-fill: red;");
            container.getChildren().add(error);
        }

        return container;
    }

    private VBox createMetricCard(String label, String value) {
        Label lbl = new Label(label);
        lbl.setStyle("-fx-font-size: 14px; -fx-text-fill: #555;");

        Label val = new Label(value);
        val.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");

        VBox card = new VBox(5, lbl, val);
        card.setPadding(new Insets(15));
        card.setStyle("-fx-background-color: #ecf0f1; -fx-border-color: #ccc; -fx-border-radius: 8px; -fx-background-radius: 8px;");
        card.setPrefWidth(240);
        return card;
    }
}

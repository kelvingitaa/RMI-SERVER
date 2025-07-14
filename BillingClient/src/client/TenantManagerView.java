package client;

import rmi.BillingInterface;
import rmi.Tenant;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;

import java.rmi.Naming;
import java.util.List;

public class TenantManagerView {
    private TableView<Tenant> table;
    private TextField idF, nameF, houseF, rentF, paidF, dateF, balanceF;

    public VBox getView() {
        BillingInterface svc;
        try {
            svc = (BillingInterface) Naming.lookup("rmi://192.168.232.57/BillingService");
        } catch (Exception e) {
            return new VBox(new Label("Server error: "+e.getMessage()));
        }

        idF = new TextField();  idF.setPromptText("ID");
        nameF = new TextField();nameF.setPromptText("Name");
        houseF= new TextField();houseF.setPromptText("House");
        rentF = new TextField();rentF.setPromptText("Rent");
        paidF = new TextField();paidF.setPromptText("Paid");
        dateF = new TextField();dateF.setPromptText("Date YYYY-MM-DD");
        balanceF= new TextField(); balanceF.setEditable(false);

        rentF.setOnKeyReleased(e->updateBal());
        paidF.setOnKeyReleased(e->updateBal());

        Button add = new Button("Add"), upd = new Button("Update"), del = new Button("Delete"), clr = new Button("Clear");
        add.setOnAction(e->crud(svc,"add"));
        upd.setOnAction(e->crud(svc,"upd"));
        del.setOnAction(e->crud(svc,"del"));
        clr.setOnAction(e->clear());

        HBox form = new HBox(8, idF,nameF,houseF,rentF,paidF,dateF,balanceF);
        form.setPadding(new Insets(10));
        HBox btns = new HBox(8, add,upd,del,clr);
        btns.setPadding(new Insets(10));

        table = new TableView<>();
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.getColumns().addAll(
          col("ID","id"), col("Name","name"), col("House","houseNumber"),
          col("Rent","rentAmount"), col("Paid","paidAmount"),
          col("Date","paymentDate"), col("Balance","balance")
        );
        table.getSelectionModel().selectedItemProperty().addListener((o,old,t)->populate(t));

        load(svc);

        VBox root = new VBox(10, form, btns, table);
        root.setPadding(new Insets(10));
        return root;
    }

    private TableColumn<Tenant,?> col(String t,String p){
        TableColumn<Tenant,Object> c=new TableColumn<>(t);
        c.setCellValueFactory(new PropertyValueFactory<>(p));
        return c;
    }

    private void load(BillingInterface svc){
        try {
            List<Tenant> lst = svc.getAllTenants();
            ObservableList<Tenant> obs = FXCollections.observableArrayList(lst);
            table.setItems(obs);
        } catch(Exception e){}
    }

    private void crud(BillingInterface svc, String op){
        try {
            Tenant t = new Tenant(
              Integer.parseInt(idF.getText()),
              nameF.getText(), houseF.getText(),
              Double.parseDouble(rentF.getText()),
              Double.parseDouble(paidF.getText()),
              dateF.getText()
            );

            boolean ok = switch(op){
                case "add" -> svc.addTenant(t);
                case "upd" -> svc.updateTenant(t);
                case "del" -> svc.deleteTenant(t.getId());
                default   -> false;
            };

            if(ok){ load(svc); clear(); }
            else   { new Alert(Alert.AlertType.ERROR, "Operation failed").showAndWait(); }
        } catch(Exception e){
            new Alert(Alert.AlertType.ERROR, "Error: "+e.getMessage()).showAndWait();
        }
    }

    private void populate(Tenant t){
        if(t==null) return;
        idF.setText(""+t.getId());
        nameF.setText(t.getName());
        houseF.setText(t.getHouseNumber());
        rentF.setText(""+t.getRentAmount());
        paidF.setText(""+t.getPaidAmount());
        dateF.setText(t.getPaymentDate());
        balanceF.setText(""+t.getBalance());
    }

    private void clear(){
        idF.clear(); nameF.clear(); houseF.clear();
        rentF.clear(); paidF.clear(); dateF.clear(); balanceF.clear();
    }

    private void updateBal(){
        try {
            double r=Double.parseDouble(rentF.getText());
            double p=Double.parseDouble(paidF.getText());
            balanceF.setText(""+(r-p));
        } catch(Exception e){ balanceF.clear(); }
    }
}

package rmi;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.ExportException;

public class ServerApp {
    public static void main(String[] args) {
        try {
            // Try to start the RMI registry on port 1099
            LocateRegistry.createRegistry(1099);
            System.out.println("✅ RMI registry created on port 1099");
        } catch (ExportException e) {
            // Registry already exists
            System.out.println("ℹ️ RMI registry already running on port 1099");
        } catch (Exception e) {
            System.err.println("❌ Failed to create RMI registry: " + e.getMessage());
            e.printStackTrace();
            return;
        }

        try {
            BillingInterface svc = new BillingImpl();
            // Binding to the default registry on localhost:1099
            Naming.rebind("rmi://localhost:1099/BillingService", svc);
            System.out.println("✅ BillingService bound and ready");
        } catch (Exception e) {
            System.err.println("❌ Failed to bind BillingService: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

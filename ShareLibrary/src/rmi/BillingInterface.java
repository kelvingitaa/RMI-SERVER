package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface BillingInterface extends Remote {
    boolean addTenant(Tenant t) throws RemoteException;
    boolean updateTenant(Tenant t) throws RemoteException;
    boolean deleteTenant(int id) throws RemoteException;
    List<Tenant> getAllTenants() throws RemoteException;
}

package rmi;

import server.BillingOperations;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class BillingImpl extends UnicastRemoteObject implements BillingInterface {
    private final BillingOperations ops;

    public BillingImpl() throws RemoteException {
        super();
        ops = new BillingOperations();
    }

    @Override public boolean addTenant(Tenant t)      throws RemoteException { return ops.addTenant(t); }
    @Override public boolean updateTenant(Tenant t)   throws RemoteException { return ops.updateTenant(t); }
    @Override public boolean deleteTenant(int id)     throws RemoteException { return ops.deleteTenant(id); }
    @Override public List<Tenant> getAllTenants()     throws RemoteException { return ops.getAllTenants(); }
}

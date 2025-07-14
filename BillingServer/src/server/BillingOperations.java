package server;

import rmi.Tenant;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BillingOperations {
    public boolean addTenant(Tenant t) {
        String sql = "INSERT INTO tenants(id,name,house_number,rent_amount,paid_amount,payment_date) VALUES(?,?,?,?,?,?)";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, t.getId());
            ps.setString(2, t.getName());
            ps.setString(3, t.getHouseNumber());
            ps.setDouble(4, t.getRentAmount());
            ps.setDouble(5, t.getPaidAmount());
            ps.setString(6, t.getPaymentDate());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateTenant(Tenant t) {
        String sql = "UPDATE tenants SET name=?,house_number=?,rent_amount=?,paid_amount=?,payment_date=? WHERE id=?";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, t.getName());
            ps.setString(2, t.getHouseNumber());
            ps.setDouble(3, t.getRentAmount());
            ps.setDouble(4, t.getPaidAmount());
            ps.setString(5, t.getPaymentDate());
            ps.setInt(6, t.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteTenant(int id) {
        String sql = "DELETE FROM tenants WHERE id=?";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Tenant> getAllTenants() {
        List<Tenant> list = new ArrayList<>();
        String sql = "SELECT * FROM tenants ORDER BY id";
        try (Connection c = DBConnection.getConnection();
             Statement st = c.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Tenant(
                  rs.getInt("id"),
                  rs.getString("name"),
                  rs.getString("house_number"),
                  rs.getDouble("rent_amount"),
                  rs.getDouble("paid_amount"),
                  rs.getString("payment_date")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}

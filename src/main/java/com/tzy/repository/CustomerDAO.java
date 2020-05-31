package com.tzy.repository;

import com.tzy.model.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {
    static final String DBURL = "jdbc:postgresql://localhost:5432/TBucks";
    static final String USER = "test";
    static final String PASS = "test";

    private Logger logger = LoggerFactory.getLogger(getClass());

    public List<Customer> getCustomers(){
        List<Customer> customerList = new ArrayList();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            logger.debug("open connection...");
            //STEP 2: Open a connection
            conn = DriverManager.getConnection(DBURL, USER, PASS);
            //STEP 3: Execute a query
            logger.info("create statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM customers";
            rs = stmt.executeQuery(sql);
            //STEP 4: Extract data from result set
            while(rs.next()) {
                //Retrieve by column name
                Long id  = rs.getLong("id");
                String name = rs.getString("name");
                String password = rs.getString("password");
                String email = rs.getString("email");

                //Fill the object
                Customer customer = new Customer();
                customer.setId(id);
                customer.setName(name);
                customer.setPassword(password);
                customer.setEmail(email);
                customerList.add(customer);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        finally {
            //STEP 6: finally block used to close resources
            try {
                if(rs != null) rs.close();
                if(stmt != null) stmt.close();
                if(conn != null) conn.close();
            }
            catch(SQLException se) {
                se.printStackTrace();
            }
        }
        return customerList;
    }


    public void createCustomer( Customer customer){
        JdbcTemplate template = new JdbcTemplate();

        String sql = "insert into customers (id,name,password,email) values (?,?,?,?)";


        logger.info("begin updating.");
        template.update(conn -> {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setLong(1,customer.getId());
            ps.setString(2,customer.getName());
            ps.setString(3,customer.getPassword());
            ps.setString(4,customer.getEmail());

            return ps;
        });

    }

    public Customer findById(Long id) {

        JdbcTemplate template = new JdbcTemplate();
        List<Customer> list = new ArrayList<Customer>();
        String sql = "select id, name, password, email from customers where id = ?";

        template.query(conn -> {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setLong(1, id);
            return ps;
        }, rs -> CustomerList(list, rs));

        if (list.size() == 1) {
            return (list.get(0));
        }
        return null;
    }


    private void CustomerList(List<Customer> list, ResultSet rs) throws SQLException {
        Customer customer = new Customer();
        customer.setId(rs.getLong("id"));
        customer.setName(rs.getString("name"));
        customer.setPassword(rs.getString("password"));
        customer.setEmail(rs.getString("email"));

        list.add(customer);
    }




    public static void main(String[] args){
        CustomerDAO dao = new CustomerDAO();

        dao.getCustomers();

    }


}






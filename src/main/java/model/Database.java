package model;

/**
 *
 * @author juhat
 */
import java.sql.*;
import java.util.Properties;
import javafx.scene.control.Label;

public class Database {

    private final String databaseName = "settings.db";
    private Connection conn;
    private Label userMessageLabel;

    public Database() {
    }

    public Database(Label userMessageLabel) {
        this.userMessageLabel = userMessageLabel;
    }

    public void connect() {
        try {
            this.conn = DriverManager.getConnection("jdbc:sqlite:" + databaseName);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void init() {
        if (conn == null) {
            connect();
        }
        try {
            Statement s = conn.createStatement();
            s.execute("CREATE TABLE IF NOT EXISTS EmailSettings ("
                    + "id INTEGER PRIMARY KEY, "
                    + "name TEXT NOT NULL UNIQUE, "
                    + "emailaddress TEXT NOT NULL, "
                    + "imaphost TEXT NOT NULL, "
                    + "imapprotocol TEXT NOT NULL, "
                    + "mailprotocol TEXT NOT NULL, "
                    + "mailhost TEXT NOT NULL, "
                    + "mailauth TEXT NOT NULL, "
                    + "outgoinghost TEXT NOT NULL)");

            PreparedStatement p = conn.prepareStatement("SELECT id FROM EmailSettings WHERE name=?");
            p.setString(1, "gmail");
            ResultSet r = p.executeQuery();

            if (!r.next()) {
                p = conn.prepareStatement("INSERT INTO EmailSettings(name, emailaddress, imaphost, imapprotocol, mailprotocol, mailhost, mailauth, outgoinghost)"
                        + " VALUES (?,?,?,?,?,?,?,?)");
                p.setString(1, "gmail");
                p.setString(2, "default@address.com");
                p.setString(3, "imap.gmail.com");
                p.setString(4, "imaps");
                p.setString(5, "smtps");
                p.setString(6, "smtp.gmail.com");
                p.setString(7, "true");
                p.setString(8, "smtp.gmail.com");
                p.executeUpdate();

                p = conn.prepareStatement("SELECT id, name FROM EmailSettings WHERE name=?");
                p.setString(1, "gmail");
                r = p.executeQuery();
                while (r.next()) {
                    System.out.println("gmailindbtest");
                    System.out.println(r.getInt("id"));
                    System.out.println(r.getString("name"));
                }
            }

        } catch (SQLException e) {
            this.userMessageLabel.setText(e.getMessage());
        }
        try {
            conn.close();
        } catch (SQLException ex) {
            this.userMessageLabel.setText(ex.getMessage());
        }
    }

    public Properties getPropertiesByName(String name) {
        Properties properties = new Properties();
        try {
            connect();
            PreparedStatement p = conn.prepareStatement("SELECT name, emailaddress, imaphost, imapprotocol, mailprotocol, mailhost, mailauth, outgoinghost FROM EmailSettings WHERE name=?");
            p.setString(1, name);
            ResultSet r = p.executeQuery();

            if (r.next()) {
                properties.setProperty("name", r.getString("name"));
                properties.setProperty("emailaddress", r.getString("emailaddress"));
                properties.setProperty("imapHost", r.getString("imaphost"));
                properties.setProperty("mail.store.protocol", r.getString("imapprotocol"));
                properties.setProperty("mail.transport.protocol", r.getString("mailprotocol"));
                properties.setProperty("mail.smtps.host", r.getString("mailhost"));
                properties.setProperty("mail.smtps.auth", r.getString("mailauth"));
                properties.setProperty("outgoingHost", r.getString("outgoinghost"));
            }
            close();
        } catch (SQLException e) {
            this.userMessageLabel.setText(e.getMessage());
        }
        
        return properties;
    }

    public void saveProperties(String name, String emailAddress, String imapHost, String imapProtocol, String mailProtocol, String mailHost, String mailAuth, String outgoingHost) {
        try {
            connect();
            PreparedStatement p = conn.prepareStatement("UPDATE EmailSettings SET emailaddress = ? , imaphost = ? , imapprotocol = ?, mailprotocol = ?, mailhost = ? , mailauth = ? , outgoinghost = ? "
                    + " WHERE name = ?");
            p.setString(1, emailAddress);
            p.setString(2, imapHost);
            p.setString(3, imapProtocol);
            p.setString(4, mailProtocol);
            p.setString(5, mailHost);
            p.setString(6, mailAuth);
            p.setString(7, outgoingHost);
            p.setString(8, name);
            p.executeUpdate();
            close();
            this.userMessageLabel.setText("Settings saved.");
        } catch (SQLException e) {
            this.userMessageLabel.setText(e.getMessage());
        }
    }

    public String getEmailAddessByName(String name) {
        String result = "";
        try {
            connect();
            PreparedStatement p = conn.prepareStatement("SELECT emailaddress FROM EmailSettings WHERE name=?");
            p.setString(1, name);
            ResultSet r = p.executeQuery();
            if (r.next()) {
                result = r.getString("emailaddress");
            }
            close();
        } catch (SQLException e) {
            this.userMessageLabel.setText(e.getMessage());
        }
        return result;
    }

    public void resetProperties() {
        try {
            connect();            
            PreparedStatement p = conn.prepareStatement("UPDATE EmailSettings SET imaphost = ? , imapprotocol = ?, mailprotocol = ?, mailhost = ? , mailauth = ? , outgoinghost = ? "
                    + " WHERE name = ?");
            p.setString(1, "imap.gmail.com");
            p.setString(2, "imaps");
            p.setString(3, "smtps");
            p.setString(4, "smtp.gmail.com");
            p.setString(5, "true");
            p.setString(6, "smtp.gmail.com");
            p.setString(7, "gmail");
            p.executeUpdate();
            close();
            this.userMessageLabel.setText("Settings reset.");
        } catch (SQLException e) {
            this.userMessageLabel.setText(e.getMessage());
        }
    }

    public void close() {
        try {
            conn.close();
        } catch (SQLException ex) {
            this.userMessageLabel.setText(ex.getMessage());
        }
    }

}

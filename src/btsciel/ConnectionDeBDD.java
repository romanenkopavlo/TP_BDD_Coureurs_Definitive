package btsciel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDeBDD {
    private String baseDeDonnes;
    private String user;
    private String password;

    public void setBaseDeDonnes(String baseDeDonnes) {
        this.baseDeDonnes = baseDeDonnes;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private final Connection connection;

    public ConnectionDeBDD(String baseDeDonnes, String user, String password) throws ClassNotFoundException, SQLException {
        this.baseDeDonnes = baseDeDonnes;
        this.user = user;
        this.password = password;
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+ baseDeDonnes, user, password);
    }

    public Connection getConnection() {
        return connection;
    }
}

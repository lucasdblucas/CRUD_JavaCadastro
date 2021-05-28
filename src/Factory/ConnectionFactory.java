package Factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	public Connection getConnection() {
	    try {
	        return DriverManager.getConnection("jdbc:mysql://localhost/db_usuario", "root", "1234qwer");
	    } catch (SQLException excecao) {
	        throw new RuntimeException(excecao);
	    }
}
}

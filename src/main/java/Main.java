import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.LoggerFactory;

public class Main {

	private static final org.slf4j.Logger Log = LoggerFactory.getLogger(Main.class);

	public static void main(String args[]) {

		Main m = new Main();

		try {
			m.findUser();
		} catch (Exception e) {
			Log.error("Print Something.", e);
		}
	}

	void findUser() throws ClassNotFoundException, SQLException {
		Class.forName("org.postgresql.Driver");

		String query = "select first_name, last_name from public.actor";
		// + "where actor = '" + "actor_id = 4" + "'";

		try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
				"postgres", "bondstone");
				Statement state = connection.createStatement();
				ResultSet result = state.executeQuery(query)) {

			while (result.next()) {
				String firstName = result.getString("first_name");
				String lastName = result.getString("last_name");

				Log.info("Found user: " + firstName + " " + lastName);

			}
		}

	}

}

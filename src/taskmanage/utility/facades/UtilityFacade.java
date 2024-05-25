package taskmanage.utility.facades;

import taskmanage.utility.impl.DatabaseConnector;
import taskmanage.utility.impl.DataValidator;
import java.sql.Connection;

public class UtilityFacade {

    public Connection connectToDatabase() {
        DatabaseConnector dbConnector = new DatabaseConnector();
        return dbConnector.getConnection();
    }

    public boolean validateData(String data) {
        return DataValidator.validateString(data);
    }
}

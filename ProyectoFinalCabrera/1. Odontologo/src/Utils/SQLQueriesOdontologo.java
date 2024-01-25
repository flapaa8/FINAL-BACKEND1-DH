package Utils;

public class SQLQueriesOdontologo {
    public static final String CREATETABLES =
            "DROP TABLE IF EXISTS ODONTOLOGOS;" +
                    "CREATE TABLE ODONTOLOGOS (ID INT PRIMARY KEY, NUMERO_MATRICULA INT, NOMBRE VARCHAR(255), APELLIDO VARCHAR(255));";

    public static final String INSERT_CUSTOM =
            "INSERT INTO ODONTOLOGOS VALUES(?,?,?,?);";

    public static final String TRAER_ODONTOLOGOS =
            "SELECT * FROM ODONTOLOGOS;";

    public static final String TRAER_ODONTOLOGO =
            "SELECT * FROM ODONTOLOGOS WHERE ID = ?;";

    public static final String UPDATE_ODONTOLOGO=
            "UPDATE ODONTOLOGOS SET NOMBRE = ?, APELLIDO = ?, NUMERO_MATRICULA = ? WHERE ID = ?;";

    public static final String DETELE_CUSTOM =

            "DELETE FROM ODONTOLOGOS WHERE ID = ?;";

}

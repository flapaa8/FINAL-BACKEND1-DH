package Utils;

public class SQLQueriesPaciente {
    public static final String CREATETABLES =
            "DROP TABLE IF EXISTS PACIENTES;" +
                    "CREATE TABLE PACIENTES (ID INT PRIMARY KEY, NOMBRE VARCHAR(255), APELLIDO VARCHAR(255), DOMICILIO VARCHAR(255), FECHAALTA DATE);";

    public static final String INSERT_CUSTOM =
            "INSERT INTO PACIENTES VALUES(?,?,?,?,?);";

    public static final String TRAER_PACIENTES =
            "SELECT * FROM PACIENTES;";
    public static final String TRAER_PACIENTE =
            "SELECT * FROM PACIENTES WHERE ID = ?;";

    public static final String UPDATE_PACIENTE=
            "UPDATE PACIENTES SET NOMBRE = ?, APELLIDO = ?, DOMICILIO = ?, FECHAALTA = ? WHERE ID = ?;";

    public static final String DETELE_CUSTOM =

            "DELETE FROM PACIENTES WHERE ID = ?;";
}

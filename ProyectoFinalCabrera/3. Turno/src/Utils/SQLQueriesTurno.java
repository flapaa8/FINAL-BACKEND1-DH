package Utils;

public class SQLQueriesTurno {
    public static final String CREATETABLES =
            "DROP TABLE IF EXISTS TURNOS;" +
                    "CREATE TABLE TURNOS (ID INT PRIMARY KEY, ID_ODONTOLOGO INT, ID_PACIENTE INT, FECHA_HORA_TURNO DATETIME);";

    public static final String INSERT_CUSTOM =
            "INSERT INTO TURNOS VALUES(?,?,?,?);";

    public static final String TRAER_TURNOS =
            "SELECT * FROM TURNOS;";
    public static final String TRAER_TURNO =
            "SELECT * FROM TURNOS WHERE ID = ?;";

    public static final String UPDATE_TURNO=
            "UPDATE TURNOS SET ID_ODONTOLOGO = ?, ID_PACIENTE = ?, FECHA_HORA_TURNO = ? WHERE ID = ?;";

    public static final String DETELE_CUSTOM =

            "DELETE FROM TURNOS WHERE ID = ?;";
}

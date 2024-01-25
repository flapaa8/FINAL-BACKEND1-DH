package com.dh.Odontologia.Utils;

public class SQLQueries {
    //Queries Odontologo
    public static final String CREATETABLES_ODONTOLOGO =
            "DROP TABLE IF EXISTS ODONTOLOGOS;" +
                    "CREATE TABLE ODONTOLOGOS (ID INT PRIMARY KEY, NUMERO_MATRICULA INT, NOMBRE VARCHAR(255), APELLIDO VARCHAR(255));";

    public static final String INSERT_CUSTOM_ODONTOLOGO =
            "INSERT INTO ODONTOLOGOS VALUES(?,?,?,?);";

    public static final String TRAER_ODONTOLOGOS =
            "SELECT * FROM ODONTOLOGOS;";

    public static final String TRAER_ODONTOLOGO =
            "SELECT * FROM ODONTOLOGOS WHERE ID = ?;";

    public static final String UPDATE_ODONTOLOGO=
            "UPDATE ODONTOLOGOS SET NOMBRE = ?, APELLIDO = ?, NUMERO_MATRICULA = ? WHERE ID = ?;";

    public static final String DETELE_CUSTOM_ODONTOLOGO =

            "DELETE FROM ODONTOLOGOS WHERE ID = ?;";


    //queries domicilio
    public static final String CREATETABLES_DOMICILIO =
            "DROP TABLE IF EXISTS DOMICILIO;" +
                    "CREATE TABLE DOMICILIO (ID INT PRIMARY KEY, CALLE VARCHAR(255), NUMERO INT, CIUDAD VARCHAR(255), DEPARTAMENTO VARCHAR(255));";

    public static final String INSERT_CUSTOM_DOMICILIO =
            "INSERT INTO DOMICILIO VALUES(?,?,?,?,?);";

    public static final String TRAER_DOMICILIOS =
            "SELECT * FROM DOMICILIO;";

    public static final String TRAER_DOMICILIO =
            "SELECT * FROM DOMICILIO WHERE ID = ?;";

    public static final String UPDATE_DOMICILIO=
            "UPDATE DOMICILIO SET CALLE = ?, NUMERO = ?, CIUDAD = ?, DEPARTAMENTO = ?, WHERE ID = ?;";

    public static final String DETELE_CUSTOM_DOMICILIO =

            "DELETE FROM DOMICILIO WHERE ID = ?;";

    //queries paciente

    public static final String CREATETABLES_PACIENTES =
            "DROP TABLE IF EXISTS PACIENTES;" +
                    "CREATE TABLE PACIENTES (ID INT PRIMARY KEY, NOMBRE VARCHAR(255), APELLIDO VARCHAR(255), DOMICILIO VARCHAR(255), FECHAALTA DATE);";

    public static final String INSERT_CUSTOM_PACIENTES =
            "INSERT INTO PACIENTES VALUES(?,?,?,?,?);";

    public static final String TRAER_PACIENTES =
            "SELECT * FROM PACIENTES;";
    public static final String TRAER_PACIENTE =
            "SELECT * FROM PACIENTES WHERE ID = ?;";

    public static final String UPDATE_PACIENTE=
            "UPDATE PACIENTES SET NOMBRE = ?, APELLIDO = ?, DOMICILIO = ?, FECHAALTA = ? WHERE ID = ?;";

    public static final String DETELE_CUSTOM_PACIENTE =

            "DELETE FROM PACIENTES WHERE ID = ?;";


    //queries turno

    public static final String CREATETABLES_TURNOS =
            "DROP TABLE IF EXISTS TURNOS;" +
                    "CREATE TABLE TURNOS (ID INT PRIMARY KEY, ID_ODONTOLOGO INT, ID_PACIENTE INT,  FECHAHORA_TURNO DATETIME);";

    public static final String INSERT_CUSTOM_TURNOS =
            "INSERT INTO TURNOS VALUES(?,?,?,?);";

    public static final String TRAER_TURNOS =
            "SELECT * FROM TURNOS;";
    public static final String TRAER_TURNO =
            "SELECT * FROM TURNOS WHERE ID = ?;";

    public static final String UPDATE_TURNO=
            "UPDATE TURNOS SET ID_ODONTOLOGO = ?, ID_PACIENTE = ?, FECHAHORA_TURNO = ? WHERE ID = ?;";

    public static final String DETELE_CUSTOM_TURNO =

            "DELETE FROM TURNOS WHERE ID = ?;";


}

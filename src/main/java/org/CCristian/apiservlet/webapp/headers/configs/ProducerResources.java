package org.CCristian.apiservlet.webapp.headers.configs;

import jakarta.annotation.Resource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/*Conexión a la BASE DE DATOS usando C.D.I.*/
@ApplicationScoped
public class ProducerResources {

    @Resource(name = "jdbc/mysqlDB")
    private DataSource ds;

    /*Devuelve la conexión a la BD*/
    @Produces
    @RequestScoped
    @MySQLConn
    private Connection beanConnection() throws NamingException, SQLException {
        return ds.getConnection();
    }

    public void close(@Disposes @MySQLConn Connection connection) throws SQLException {
        connection.close();
        System.out.println("Cerrando la conexión a la BD MySQL");   /*Para visualizarlo en la consola de 'Tomcat'*/
    }
}

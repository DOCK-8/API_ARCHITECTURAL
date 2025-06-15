package com.store;

import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.Context;

import com.SOAPWrapperREST.Api;

public class App {
    public void init() throws Exception {
        Tomcat tomcat = new Tomcat();
        tomcat.getConnector().setProperty("address", "0.0.0.0");
        tomcat.setPort(8080);

        // Crear una aplicación web básica (necesita un contexto)
        Context ctx = tomcat.addContext("", null);

        // Registrar nuestro servlet
        Tomcat.addServlet(ctx, "SoapServlet", new Api());
        ctx.addServletMappingDecoded("/soap", "SoapServlet");

        // Iniciar
        tomcat.start();
        tomcat.getServer().await();
    }
}

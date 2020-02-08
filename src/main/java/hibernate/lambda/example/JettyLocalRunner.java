package hibernate.lambda.example;

import org.apache.log4j.Logger;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class JettyLocalRunner {

    private static final Logger LOGGER = Logger.getLogger(JettyLocalRunner.class);

    public static void main(String[] args) throws Exception {

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");

        Server jettyServer = new Server(8000);
        jettyServer.setHandler(context);

        ServletHolder jerseyServlet =
                context.addServlet(org.glassfish.jersey.servlet.ServletContainer.class, "/*");

        jerseyServlet.setInitOrder(0);
        jerseyServlet.setInitParameter("javax.ws.rs.Application", "hibernate.lambda.example.JettySettings");

        try {
            runJettyServer(jettyServer);
        } catch (Exception e) {
            LOGGER.warn("Application error : " + e);
        } finally {
            jettyServer.destroy();
        }
    }

    /**
     * Method used to Start the Jetty Server
     * @param jettyServer Jetty Server
     * @throws Exception Exception
     */
    private static void runJettyServer(Server jettyServer) throws Exception {
        // These lines can never be tested or a Jersey server will startup
        jettyServer.start();
        jettyServer.join();
    }
}

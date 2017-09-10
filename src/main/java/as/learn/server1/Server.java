package as.learn.server1;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.server.NetworkListener;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.moxy.json.MoxyJsonConfig;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ext.ContextResolver;
import java.io.IOException;
import java.net.URI;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * Date: September-10, 2017
 *
 * @author Asher Stern
 */
public class Server
{
    public static final String BASE_URI_PATH = "server1";
    public static final int PORT = 5069;

    public static void main(String[] args)
    {
        try
        {
            runServer();
        }
        catch (Throwable t)
        {
            t.printStackTrace();
        }
    }


    public static void runServer() throws IOException, InterruptedException
    {
        final HttpServer server = GrizzlyHttpServerFactory.createHttpServer(URI.create(buildURI(String.valueOf(PORT))),
                createApp(),
                false);
        server.start();

        System.out.println(String.format("Application started.%nStop the application using CTRL+C"));

        Thread.currentThread().join();
    }



    private static String buildURI(String port)
    {
        return "http://"+ NetworkListener.DEFAULT_NETWORK_HOST+":"+port+"/"+BASE_URI_PATH+"/";
    }

    // The following two methods (createApp() and createMoxyJsonResolver() are merely boilerplate code for REST server construction.
    // Don't bother yourself too much with them.
    private static ResourceConfig createApp()
    {
        return new ResourceConfig().packages(Server.class.getPackage().getName()).register(createMoxyJsonResolver());
    }

    private static ContextResolver<MoxyJsonConfig> createMoxyJsonResolver()
    {
        final MoxyJsonConfig moxyJsonConfig = new MoxyJsonConfig();
        Map<String, String> namespacePrefixMapper = new HashMap<String, String>(1);
        namespacePrefixMapper.put("http://www.w3.org/2001/XMLSchema-instance", "xsi");
        moxyJsonConfig.setNamespacePrefixMapper(namespacePrefixMapper).setNamespaceSeparator(':');
        return moxyJsonConfig.resolver();
    }

}

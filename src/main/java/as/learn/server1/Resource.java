package as.learn.server1;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * <p>
 * Date: September-10, 2017
 *
 * @author Asher Stern
 */

@Path("resource1")
public class Resource
{
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.TEXT_PLAIN)
    public String post(String input)
    {
        return "Your input: " + input;
    }
}

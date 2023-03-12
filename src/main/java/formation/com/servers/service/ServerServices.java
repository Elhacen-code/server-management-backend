package formation.com.servers.service;

import formation.com.servers.model.Server;
import java.util.Collection;
import java.io.IOException;

public interface ServerServices {

    public Server create(Server server);

    public Collection<Server> list(int limit);

    public Server get(Long id);

    public Server update(Server server);

    public Boolean delete(Long id);

    public String setServerImageUrl();

    public Server ping(String ipAdress)throws IOException;
}
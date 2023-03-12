package formation.com.servers.service.implementation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import formation.com.servers.enumeration.Status;
import formation.com.servers.model.*;
import formation.com.servers.repo.ServerRepo;
import formation.com.servers.service.ServerServices;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Collection;
import javax.transaction.Transactional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ServerImplementation implements ServerServices {

    @Autowired
    private ServerRepo serverRepo;

    @Override
    public Server create(Server server) {
        log.info("saving new server");
        server.setImageUrl(setServerImageUrl());
        return serverRepo.save(server);

    }

    @Override
    public String setServerImageUrl() {
        String [] images = {"server1.png", "server2.png", "server3.png", "server1.png"};
        return ServletUriComponentsBuilder.fromCurrentContextPath().path("/server/image/" + images[new Random().nextInt(4)]).toUriString();
    }
    
    @Override
    public Server ping(String ipAdress) throws IOException{
        log.info("pinging the server");
        Server server = serverRepo.findByIpAdress(ipAdress);
        InetAddress inetAddress = InetAddress.getByName(ipAdress);
        server.setStatus(inetAddress.isReachable(1000) ? Status.SERVER_UP : Status.SERVER_DOWN);
        serverRepo.save(server);
        return server;
    }

    @Override
    public Collection<Server> list(int limit) {
        log.info("fetching all server");
        return serverRepo.findAll();

    }

    @Override
    public Server get(Long id) {
        log.info("fetching by id server");
        return serverRepo.findById(id).get();

    }

    @Override
    public Server update(Server server) {
        log.info("update server");
        return serverRepo.save(server);
    }

    @Override
    public Boolean delete(Long id) {
        log.info("delete by id server");
        serverRepo.deleteById(id);
        return true;
    }

}
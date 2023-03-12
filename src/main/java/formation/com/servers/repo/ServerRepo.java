package formation.com.servers.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import formation.com.servers.model.Server;

@Repository
public interface ServerRepo extends JpaRepository<Server, Long> {
    Server findByIpAdress(String ipAdress);
}
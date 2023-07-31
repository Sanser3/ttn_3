package my.project.ttn_3.repository;

import my.project.ttn_3.entity.Customers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customers, Integer> {
    public Optional<Customers> findByNameAndAddressAndRoute(String name, String address, String route);
    public Customers findCustomerByNameAndAddressAndRoute(String name, String address, String route);
    public Optional<Customers> findByAccessKey(String accessKey);
    public Optional<List<Customers>> findAllByRoute(String route);

}

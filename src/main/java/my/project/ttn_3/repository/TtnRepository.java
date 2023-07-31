package my.project.ttn_3.repository;

import my.project.ttn_3.entity.Ttns;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TtnRepository extends JpaRepository<Ttns, Integer> {

    List<Ttns> findTtnsByCreateTtn(LocalDate date);

    Ttns findTtnsByAccessKey(String accessKey);
}

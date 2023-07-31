package my.project.ttn_3;

import my.project.ttn_3.entity.Customers;
import my.project.ttn_3.entity.Orders;
import my.project.ttn_3.entity.Ttns;
import my.project.ttn_3.repository.CustomerRepository;
import my.project.ttn_3.repository.OrderRepository;
import my.project.ttn_3.repository.TtnRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

@SpringBootApplication
public class Ttn3Application {

    public static void main(String[] args) {
        SpringApplication.run(Ttn3Application.class, args);
    }

    @Bean
    @Transactional
    CommandLineRunner commandLineRunner(
            // репозиторий для ролей ------------> добавить
//            UserRepository userRepository,
//            RoleRepository roleRepository,
//            BCryptPasswordEncoder bCryptPasswordEncoder,
            CustomerRepository customerRepository,
            OrderRepository orderRepository,
            TtnRepository ttnRepository){
        return args -> {
            // сохраняем роли аутентификации
//            userRepository.save(new User(1L,"admin",
//                    bCryptPasswordEncoder.encode("adminpass"), new HashSet<>(1,1)));
//            userRepository.save(new User(2L,"user",
//                    bCryptPasswordEncoder.encode("userpass"), new HashSet<>()));
//            roleRepository.save(new Role(1L, "ADMIN", new HashSet<>()));
//            roleRepository.save(new Role(2L, "USER", new HashSet<>()));
            // сохраняем заказчика
            customerRepository.save(new Customers(1, "name", "address", "route", "1320906976",
                    LocalDate.now()));
            // сохраняем заказ
            Integer[] orders = new Integer[]{1,1,0,0,2};
            ArrayList<Integer> newArray = new ArrayList<>(Arrays.asList(orders));
            orderRepository.save(new Orders(1, newArray, LocalDate.now()));
            // сохраняем накладную
            ArrayList<Integer> newArray2 = new ArrayList<>();
            Collections.addAll(newArray2,orders);
            ttnRepository.save(new Ttns(
                    1,
                    new Customers(1, "name", "address", "route", "1320906976", LocalDate.now()),
                    new Orders(1, newArray2, LocalDate.now()),
                    "-1341405593",
                    LocalDate.now()));
        };
    }
//    @Bean
//    public BCryptPasswordEncoder bCryptPasswordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
}

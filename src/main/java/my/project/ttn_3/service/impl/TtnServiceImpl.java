package my.project.ttn_3.service.impl;

import my.project.ttn_3.DTO.ttns.TtnRequest;
import my.project.ttn_3.DTO.ttns.TtnResponse;
import my.project.ttn_3.converter.TtnConverter;
import my.project.ttn_3.entity.Ttns;
import my.project.ttn_3.repository.TtnRepository;
import my.project.ttn_3.service.TtnService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Data
@AllArgsConstructor
public class TtnServiceImpl implements TtnService {
    private final TtnRepository ttnRepository;
    private final TtnConverter ttnConverter;

    @Override
    public TtnResponse createTtn(TtnRequest ttnRequest) {
        Ttns newTtn = ttnConverter.toTtn(ttnRequest);
        newTtn = ttnRepository.save(newTtn);
        return new TtnResponse(newTtn.getId(), newTtn.getCustomers(), newTtn.getOrders(),
                String.valueOf(
                        (ttnRequest.getName() + ttnRequest.getAddress() + ttnRequest.getRoute() + LocalDateTime.now())
                                .hashCode()),
                LocalDate.now());
    }

    @Override
    public List<TtnResponse> findAllByDateTtn(TtnRequest ttnRequest) {
        List<Ttns> ttns = ttnRepository.findTtnsByCreateTtn(ttnRequest.getDate());
//        List<Ttns> ttns = ttnRepository.findAll();
        return ttns.stream().map(ttnConverter::toResponse).collect(Collectors.toList());

    }

//    @Override
//    public List<TtnResponse> findAllByDateTtn(LocalDate date) {
//        List<Ttns> ttns = ttnRepository.findTtnsByCreateTtn(date);
////        List<Ttns> ttns = ttnRepository.findAll();
//        return ttns.stream().map(ttnConverter::toResponse).collect(Collectors.toList());
//
//    }

    @Override
    public List<TtnResponse> findAllByRoute(String route) {
        List<Ttns> ttns = ttnRepository.findAll().stream()
                .filter(ttns1 -> ttns1.getCustomers().getRoute().equals(route))
                .toList();
        return ttns.stream().map(ttnConverter::toResponse).collect(Collectors.toList());
    }

    @Override
    public List<TtnResponse> findAllTtn() {
        List<Ttns> ttns = ttnRepository.findAll();
        return ttns.stream().map(ttnConverter::toResponse).collect(Collectors.toList());
    }

    @Override
    public List<TtnResponse> findAllTtnByNameAndAddressAndRouteAndDate(TtnRequest ttnRequest) {
        List<Ttns> ttns = ttnRepository.findAll().stream()
                .filter(ttns1 -> ttns1.getCustomers().getName().equals(ttnRequest.getName()))
                .filter(ttns1 -> ttns1.getCustomers().getAddress().equals(ttnRequest.getAddress()))
                .filter(ttns1 -> ttns1.getCustomers().getRoute().equals(ttnRequest.getRoute()))
                .filter(ttns1 -> ttns1.getCreateTtn().equals(ttnRequest.getDate()))
                .toList();
        return ttns.stream().map(ttnConverter::toResponse).collect(Collectors.toList());
    }

    @Override
    public TtnResponse findByNameAndAddressAndRouteAndDate(TtnRequest ttnRequest) {
        Ttns ttns = ttnRepository.findAll().stream()
                .filter(ttns1 -> ttns1.getCustomers().getName().equals(ttnRequest.getName()))
                .filter(ttns1 -> ttns1.getCustomers().getAddress().equals(ttnRequest.getAddress()))
                .filter(ttns1 -> ttns1.getCustomers().getRoute().equals(ttnRequest.getRoute()))
                .filter(ttns1 -> ttns1.getCreateTtn().equals(ttnRequest.getDate()))
                .toList().stream().findFirst().get();
        TtnResponse ttnResponse = new TtnResponse(
                ttns.getId(), ttns.getCustomers(), ttns.getOrders(), ttns.getAccessKey(), ttns.getCreateTtn());
        return ttnResponse;
    }

    @Override
    public TtnResponse updateTtnByOrder(TtnRequest ttnRequest) {
        String accessKey = findByNameAndAddressAndRouteAndDate(ttnRequest).getAccessKey();
        Ttns ttnsForUpdate = ttnRepository.findTtnsByAccessKey(accessKey);

        ttnsForUpdate = ttnConverter.updateOrderByCustomer(ttnsForUpdate, ttnRequest);
        ttnRepository.save(ttnsForUpdate);

        return new TtnResponse(ttnsForUpdate.getId(), ttnsForUpdate.getCustomers(), ttnsForUpdate.getOrders(),
                ttnsForUpdate.getAccessKey(), LocalDate.now());
    }


}

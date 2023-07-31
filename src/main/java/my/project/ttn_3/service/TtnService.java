package my.project.ttn_3.service;

import my.project.ttn_3.DTO.ttns.TtnRequest;
import my.project.ttn_3.DTO.ttns.TtnResponse;

import java.time.LocalDate;
import java.util.List;


public interface TtnService {

    TtnResponse createTtn(TtnRequest ttnRequest);

    List<TtnResponse> findAllByDateTtn(TtnRequest ttnRequest);

    List<TtnResponse> findAllByRoute(String route);

    List<TtnResponse> findAllTtn();

    List<TtnResponse> findAllTtnByNameAndAddressAndRouteAndDate(TtnRequest ttnRequest);

    TtnResponse findByNameAndAddressAndRouteAndDate(TtnRequest ttnRequest);

    TtnResponse updateTtnByOrder(TtnRequest ttnRequest);
}

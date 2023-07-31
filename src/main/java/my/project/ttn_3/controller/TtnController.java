package my.project.ttn_3.controller;

import lombok.RequiredArgsConstructor;
import my.project.ttn_3.DTO.ttns.TtnRequest;
import my.project.ttn_3.DTO.ttns.TtnResponse;
import my.project.ttn_3.service.TtnService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/ttns")
//@AllArgsConstructor
@RequiredArgsConstructor
public class TtnController {

    private final TtnService ttnService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TtnResponse> getAllTtn(){
        return ttnService.findAllTtn();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TtnResponse addNewTtn(@RequestBody TtnRequest request){
        return ttnService.createTtn(request);
    }

    @GetMapping("/date")
    @ResponseStatus(HttpStatus.OK)
    public List<TtnResponse> getAllTtnByDate(@RequestBody TtnRequest request){
        return ttnService.findAllByDateTtn(request);
    }

//    @GetMapping("/date/{date}")
//    @ResponseStatus(HttpStatus.OK)
//    public List<TtnResponse> getAllTtnByDate(@PathVariable LocalDate date){
//        return ttnService.findAllByDateTtn(date);
//    }

    @GetMapping("/route")
    @ResponseStatus(HttpStatus.OK)
    public List<TtnResponse> getAllTtnByRoute(@RequestBody TtnRequest request){
        return ttnService.findAllByRoute(request.getRoute());
    }

//    @GetMapping("/route/{route}")
//    @ResponseStatus(HttpStatus.OK)
//    public List<TtnResponse> getAllTtnByRoute(@PathVariable String route){
//        return ttnService.findAllByRoute(route);
//    }

    @GetMapping("/allby")
    @ResponseStatus(HttpStatus.OK)
    public List<TtnResponse> getAllTtnByNameAndAddressAndRouteAndDate(@RequestBody TtnRequest request){
        return ttnService.findAllTtnByNameAndAddressAndRouteAndDate(request);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public TtnResponse updateTtn(@RequestBody TtnRequest request){
        return ttnService.updateTtnByOrder(request);
    }
}

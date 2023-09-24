package projetos.jonas.cloudparking.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import projetos.jonas.cloudparking.DTO.ParkingDTO;
import projetos.jonas.cloudparking.controller.mapper.ParkingMapper;
import projetos.jonas.cloudparking.model.Parking;
import projetos.jonas.cloudparking.service.ParkingService;

import java.util.List;

@RestController
@RequestMapping("/parking")
public class ParkingController {

    private final ParkingService service;
    private final ParkingMapper parkingMapper;

    public ParkingController(ParkingService service, ParkingMapper parkingMapper) {
        this.service = service;
        this.parkingMapper = parkingMapper;
    }

    @GetMapping
    public List<ParkingDTO> findall(){
        List<Parking> parkingList = service.findall();
        List<ParkingDTO> result = parkingMapper.toParkingDTOList(parkingList);
        return result;
    }
}

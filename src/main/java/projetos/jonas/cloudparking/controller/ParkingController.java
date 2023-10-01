package projetos.jonas.cloudparking.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projetos.jonas.cloudparking.DTO.ParkingCreateDTO;
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
    public ResponseEntity<List<ParkingDTO>> findall(){
        List<Parking> parkingList = service.findall();
        List<ParkingDTO> result = parkingMapper.toParkingDTOList(parkingList);
        return  ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity findById( @PathVariable String id){
        Parking parking = service.findById(id);
        ParkingDTO result = parkingMapper.parkingDTO(parking);
        return ResponseEntity.ok(parking);
    }

    @PostMapping
    public ResponseEntity create(@RequestBody ParkingCreateDTO parkingCreateDTO){
        Parking parking = parkingMapper.toParking(parkingCreateDTO);
        parking = service.create(parking);
        var result = parkingMapper.parkingDTO(parking);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById( @PathVariable String id){
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable String id,@RequestBody ParkingCreateDTO parkingUpdateDTO){
        Parking parking = parkingMapper.toParking(parkingUpdateDTO);
        parking = service.update(id,parking);
        var result = parkingMapper.parkingDTO(parking);
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("/{id}")
    public ResponseEntity exit(@PathVariable String id){
        Parking parking = parking = service.checkout(id);
        var result = parkingMapper.parkingDTO(parking);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
}

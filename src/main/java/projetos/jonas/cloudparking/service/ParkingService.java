package projetos.jonas.cloudparking.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import projetos.jonas.cloudparking.exceptions.ParkingNotFoundException;
import projetos.jonas.cloudparking.model.Parking;
import projetos.jonas.cloudparking.repository.ParkingRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ParkingService {

    private final ParkingRepository repository;

    public ParkingService(ParkingRepository repository){
        this.repository =repository;
    }

    private static String getUUID(){
        return UUID.randomUUID().toString().replace("-","");
    }

    public List<Parking> findall(){
        List<Parking> allParkings = repository.findAll();
        return  allParkings;
    }

    public Parking findById(String id){
        Parking byId = repository.findById(id).orElseThrow(() -> new ParkingNotFoundException(id));

        return byId;
    }

    @Transactional
    public Parking create(Parking parking) {

        parking.setId(getUUID());
        parking.setEntryDate(LocalDateTime.now());
        repository.save(parking);

        return parking;
    }
    @Transactional
    public void deleteById(String id) {
       Parking parking = findById(id);
       repository.deleteById(id);
    }
    @Transactional
    public Parking update(String id, Parking parkingUpdate) {
        Parking actual = findById(id);
        actual.setColor(parkingUpdate.getColor());
        actual.setState(parkingUpdate.getState());
        actual.setModel(parkingUpdate.getModel());
        actual.setLicense(parkingUpdate.getLicense());
        repository.save(actual);
        return actual;
    }
    @Transactional
    public Parking checkout(String id) {
        Parking byId = repository.findById(id).orElse(null);
        byId.setExitDate(LocalDateTime.now());
        byId.setBill(ParkingCheckout.getBil(byId));
        repository.save(byId);
        return byId;
    }
}


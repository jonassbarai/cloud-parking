package projetos.jonas.cloudparking.service;

import org.springframework.stereotype.Service;
import projetos.jonas.cloudparking.exceptions.ParkingNotFoundException;
import projetos.jonas.cloudparking.model.Parking;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ParkingService {
    private static Map<String, Parking> parkingMap = new HashMap<>();

    static{
        var id = getUUID();
        Parking parking = new Parking(id,"DFD-1515","SP","CELTA","PRETO");
        parkingMap.put(id,parking);
    }
    private static String getUUID(){
        return UUID.randomUUID().toString().replace("-","");
    }

    public List<Parking> findall(){
       return new ArrayList<Parking>(parkingMap.values());
    }

    public Parking findById(String id){
        Parking parking = parkingMap.get(id);

        if(parking == null)
             throw new ParkingNotFoundException(id);

        return parking;
    }


    public Parking create(Parking parking) {
        parking.setId(getUUID());
        parking.setEntryDate(LocalDateTime.now());
        parkingMap.put(parking.getId(),parking);
        return parking;
    }

    public void deleteById(String id) {
       Parking parking = findById(id);
       parkingMap.remove(id);
    }

    public Parking update(String id, Parking parkingUpdate) {
        Parking actual = findById(id);
        actual.setColor(parkingUpdate.getColor());
        parkingMap.replace(id,actual);
        return actual;
    }

    public Parking exit(String id) {
        Parking actual = findById(id);
        actual.setExitDate(LocalDate.now().atStartOfDay());
        update(id,actual);
        return actual;
    }
}


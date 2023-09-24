package projetos.jonas.cloudparking.service;

import org.springframework.stereotype.Service;
import projetos.jonas.cloudparking.model.Parking;

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
}


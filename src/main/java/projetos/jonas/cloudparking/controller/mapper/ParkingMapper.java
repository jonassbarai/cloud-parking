package projetos.jonas.cloudparking.controller.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import projetos.jonas.cloudparking.DTO.ParkingDTO;
import projetos.jonas.cloudparking.model.Parking;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Component
public class ParkingMapper {

    private static final ModelMapper MODEL_MAPPER = new ModelMapper();

    public ParkingDTO parkingDTO(Parking parking){
        return MODEL_MAPPER.map(parking,ParkingDTO.class);
    }
    public List<ParkingDTO> toParkingDTOList(List<Parking> parkingList) {
        return  parkingList.stream().map(this::parkingDTO).collect(Collectors.toList());
    }

}

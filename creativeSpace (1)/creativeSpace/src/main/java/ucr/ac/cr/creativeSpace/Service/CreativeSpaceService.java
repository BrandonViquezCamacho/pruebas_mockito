package ucr.ac.cr.creativeSpace.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ucr.ac.cr.creativeSpace.Model.CreativeSpace;
import ucr.ac.cr.creativeSpace.Model.DTO.CreativeSpaceDTO;
import ucr.ac.cr.creativeSpace.Model.DTO.UserDTO;
import ucr.ac.cr.creativeSpace.Model.User;
import ucr.ac.cr.creativeSpace.Repository.CreativeSpaceRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CreativeSpaceService {

    @Autowired
    private CreativeSpaceRepository creativeSpaceRepository;


    public CreativeSpaceDTO saveCreativeSpace(CreativeSpace creativeSpace) {
        return this.convertCreativeSpaceDTO(this.creativeSpaceRepository.save(creativeSpace));
    }


    public void deleteCreativeSpace(Integer id) {
        this.creativeSpaceRepository.deleteById(id);
    }

    public CreativeSpaceDTO findByIDCreativeSpace(Integer id) {

        Optional<CreativeSpace> optional = this.creativeSpaceRepository.findById(id);

        if(optional.isPresent()){
            return this.convertCreativeSpaceDTO(optional.get());
        }

        return null;

    }

    public CreativeSpaceDTO editCreativeSpace(Integer id, CreativeSpace creativeSpaceEdit) {

        Optional<CreativeSpace> creativeSpaceOp=this.creativeSpaceRepository.findById(id);
        if(creativeSpaceOp.isPresent()){
            CreativeSpace creativeSpace=creativeSpaceOp.get();
            creativeSpace=creativeSpaceEdit;
            return this.convertCreativeSpaceDTO(this.creativeSpaceRepository.save(creativeSpace));
        }
        return null;
    }

    public List<CreativeSpaceDTO> findAll() {
        return this.convertListDTO(this.creativeSpaceRepository.findAll());
    }


    // corregir con como se hace
        public List<CreativeSpaceDTO> findByType (String type) {

        List<CreativeSpaceDTO> spaces = new ArrayList<>();

        for (CreativeSpaceDTO creativeSpaceDTO : this.findAll()) {

            if (creativeSpaceDTO.getType().equalsIgnoreCase(type)) {
                spaces.add(creativeSpaceDTO);
            }
        }

        return spaces;
    }


    // corregir con como se hace
    public List<CreativeSpaceDTO> findByLocation (String location) {

        List<CreativeSpaceDTO> locations = new ArrayList<>();

        for (CreativeSpaceDTO creativeSpaceDTO : this.findAll()) {

            if (creativeSpaceDTO.getLocation().equalsIgnoreCase(location)) {
                locations.add(creativeSpaceDTO);
            }
        }

        return locations;
    }



    public CreativeSpaceDTO convertCreativeSpaceDTO(CreativeSpace creativeSpace){
        CreativeSpaceDTO dto =new CreativeSpaceDTO();
        dto.setId(creativeSpace.getId());
        dto.setName(creativeSpace.getName());
        dto.setLocation(creativeSpace.getLocation());
        dto.setPrice(creativeSpace.getPrice());
        dto.setType(creativeSpace.getType());

        return dto;
    }

    public List<CreativeSpaceDTO> convertListDTO(List<CreativeSpace> listCreativeSpace){
        List<CreativeSpaceDTO> listDTO=new ArrayList<>();
        for (CreativeSpace creativeSpace : listCreativeSpace){
            listDTO.add(this.convertCreativeSpaceDTO(creativeSpace));
        }
        return listDTO;
    }
}

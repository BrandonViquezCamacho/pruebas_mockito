package ucr.ac.cr.creativeSpace.Service;


import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ucr.ac.cr.creativeSpace.Model.CreativeSpace;
import ucr.ac.cr.creativeSpace.Model.DTO.CreativeSpaceDTO;
import ucr.ac.cr.creativeSpace.Model.DTO.ReservationDTO;
import ucr.ac.cr.creativeSpace.Model.DTO.UserDTO;
import ucr.ac.cr.creativeSpace.Model.Reservation;
import ucr.ac.cr.creativeSpace.Repository.CreativeSpaceRepository;
import ucr.ac.cr.creativeSpace.Repository.ReservationRepository;
import ucr.ac.cr.creativeSpace.Repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CreativeSpaceRepository creativeSpaceRepository;

    public List<ReservationDTO> findAll(){

        List<ReservationDTO> list = new ArrayList<>();

        for(Reservation reservation : reservationRepository.findAll()){
            list.add(convertDTO(reservation));
        }

        return list;
    }

    public ReservationDTO findById(Integer id){

        Optional<Reservation> optional =
                reservationRepository.findById(id);

        if(optional.isEmpty()){
            return null;
        }

        return convertDTO(optional.get());
    }

    public ReservationDTO saveReservation(Reservation reservation){

        if(!userRepository.existsById(
                reservation.getUser().getId())){
            return null;
        }

        if(!creativeSpaceRepository.existsById(
                reservation.getCreativeSpace().getId())){
            return null;
        }

        Reservation saved = reservationRepository.save(reservation);

        return convertDTO(saved);
    }

    public ReservationDTO editReservation(Integer id,
                                          Reservation reservation){

        Optional<Reservation> optional =
                reservationRepository.findById(id);

        if(optional.isEmpty()){
            return null;
        }

        Reservation reservationDB = optional.get();

        reservationDB.setDateReserved(
                reservation.getDateReserved());

        reservationDB.setStatus(
                reservation.getStatus());

        reservationDB.setUser(
                reservation.getUser());

        reservationDB.setCreativeSpace(
                reservation.getCreativeSpace());

        return convertDTO(
                reservationRepository.save(reservationDB)
        );
    }

    public void deleteReservation(Integer id){
        reservationRepository.deleteById(id);
    }

    public List<ReservationDTO> getReservationByUser(Integer id){

        List<ReservationDTO> list = new ArrayList<>();

        for(Reservation reservation :
                reservationRepository.findByUserId(id)){

            list.add(convertDTO(reservation));
        }

        return list;
    }

    public List<ReservationDTO> getReservationByCreativeSpace(Integer id){

        List<ReservationDTO> list = new ArrayList<>();

        for(Reservation reservation :
                reservationRepository.findByCreativeSpaceId(id)){

            list.add(convertDTO(reservation));
        }

        return list;
    }

    public ReservationDTO convertDTO(Reservation reservation){

        return new ReservationDTO(
                reservation.getId(),
                reservation.getCreativeSpace().getId(),
                reservation.getCreativeSpace().getName(),
                reservation.getUser().getEmail(),
                reservation.getUser().getName(),
                reservation.getDateReserved(),
                reservation.getStatus()
        );
    }
}
package ucr.ac.cr.creativeSpace.Model.DTO;

import java.time.LocalDate;

public class ReservationDTO {

    private Integer id;
    private Integer spaceId;
    private String spaceName;
    private String userEmail;
    private String userName;
    private LocalDate dateReserved;
    private String status;

    public ReservationDTO(Integer id, Integer spaceId, String spaceName, String userEmail, String userName, LocalDate dateReserved, String status) {
        this.id = id;
        this.spaceId = spaceId;
        this.spaceName = spaceName;
        this.userEmail = userEmail;
        this.userName = userName;
        this.dateReserved = dateReserved;
        this.status = status;
    }
}

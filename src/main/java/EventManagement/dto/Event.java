package EventManagement.dto;

import EventManagement.enums.EventType;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Event {
    private Integer id;
    private Integer startDate;
    private Integer endDate;
    private Integer roomId;
    private User owner;
    private List<User> invitedUsers;
    private EventType type;
}

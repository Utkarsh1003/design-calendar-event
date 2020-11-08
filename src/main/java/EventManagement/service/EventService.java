package EventManagement.service;

import EventManagement.dto.Event;
import EventManagement.dto.User;
import EventManagement.enums.InviteResponse;

import java.util.Map;

public interface EventService {
    Event createEvent(Event event, User user);
    Event updateEvent(Event event, User user);
    boolean deleteEvent(Event event, User user);
    Event getEvent(Integer eventId);
    Map<User, InviteResponse> getInviteResponse(Integer eventId);
}

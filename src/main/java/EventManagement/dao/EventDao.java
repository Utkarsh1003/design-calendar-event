package EventManagement.dao;

import EventManagement.dto.Event;

public interface EventDao {
    Event getEvent(Integer eventId);
    Event addEvent(Event event);
    Event deleteEvent(Event event);
}

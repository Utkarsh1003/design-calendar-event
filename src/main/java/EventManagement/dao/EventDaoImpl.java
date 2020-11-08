package EventManagement.dao;

import EventManagement.dto.Event;

import java.util.HashMap;
import java.util.Map;

//making this class singleton
public class EventDaoImpl implements EventDao{
    private static EventDaoImpl instance;
    private Map<Integer, Event> eventMap;

    public static EventDaoImpl getInstance(){
        if(instance == null)
            instance = new EventDaoImpl();

        return instance;
    }

    public EventDaoImpl() {
        this.eventMap = new HashMap<Integer, Event>();
    }

    public Event getEvent(Integer eventId) {
        return eventMap.get(eventId);
    }

    public Event addEvent(Event event) {
        eventMap.put(event.getId(), event);
        return event;
    }

    public Event deleteEvent(Event event) {
        return eventMap.remove(event.getId());
    }
}

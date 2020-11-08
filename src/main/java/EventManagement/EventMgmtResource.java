package EventManagement;

import EventManagement.dto.Event;
import EventManagement.dto.User;
import EventManagement.enums.EventServiceType;
import EventManagement.enums.InviteResponse;
import EventManagement.service.EventService;

import java.util.Map;

//making this class singleton
public class EventMgmtResource {
    private static EventMgmtResource instance;
    private EventService eventService;
    private EventServiceFactory eventServiceFactory;

    public static EventMgmtResource getInstance(){
        if(instance == null)
            instance = new EventMgmtResource();

        return instance;
    }

    EventMgmtResource(){
        this.eventServiceFactory = new EventServiceFactory();
        this.eventService = eventServiceFactory.getEvengetEventMgmtService(EventServiceType.inmemory);
    }

    //API to create/update event
    public Event createUpdateEvent(Event event, User user){
        return eventService.createEvent(event, user);
    }

    //API to create/update event
    public boolean cancelEvent(Event event, User user){
        return eventService.deleteEvent(event, user);
    }

    //API to give details of invities with their responses
    public Map<User, InviteResponse> getInviteResponse(Integer eventId){
        return eventService.getInviteResponse(eventId);
    }
}

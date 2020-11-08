package EventManagement.service;

import EventManagement.dao.EventDao;
import EventManagement.dao.EventDaoImpl;
import EventManagement.dto.Building;
import EventManagement.dto.Event;
import EventManagement.dto.Room;
import EventManagement.dto.User;
import EventManagement.enums.EventType;
import EventManagement.enums.InviteResponse;

import java.util.HashMap;
import java.util.Map;

public class EventServiceImpl implements EventService{
    private EventDao eventDao;

    public EventServiceImpl() {
        eventDao = EventDaoImpl.getInstance();
    }

    public Event createEvent(Event event, User user) {
        if(eventDao.getEvent(event.getId()) != null)
            return updateEvent(event, user);

        //We can throw an exception here
        if(validateRestrictions(user, event))
            return eventDao.addEvent(event);

        return null;
    }

    public Event updateEvent(Event event, User user) {
        if(validateRestrictions(user, event))
            eventDao.addEvent(event);

        return null;
    }

    //validating restriction here
    //1. Is slot available for the location
    //2. validation for creator
    //3. if event type is meeting location and guest list mandatory
    private boolean validateRestrictions(User user, Event event) {
        if(!user.getId().equals(event.getOwner().getId()))
            return false;

        if(event.getType().equals(EventType.meeting)){
            if(event.getRoomId() == null || event.getInvitedUsers() == null || event.getInvitedUsers().isEmpty())
                return false;
        }

        Room room = Building.getInstance().getRoomMap().get(event.getRoomId());
        boolean slotAvailable = room.isSlotAvailable(event.getStartDate(), event.getEndDate());
        if(slotAvailable)
            return true;

        return false;
    }

    //validation for owner
    public boolean deleteEvent(Event event, User user) {
        if(!user.getId().equals(event.getOwner().getId()))
            return false;

        if(eventDao.deleteEvent(event) != null)
            return true;

        return false;
    }

    public Event getEvent(Integer eventId) {
        return eventDao.getEvent(eventId);
    }

    public Map<User, InviteResponse> getInviteResponse(Integer eventId) {
        Event event = getEvent(eventId);
        //we can throw an exception here
        if(event == null)
            return new HashMap<User, InviteResponse>();

        Map<User, InviteResponse> inviteResponseMap = new HashMap<User, InviteResponse>();
        for (User user : event.getInvitedUsers()) {
            InviteResponse userResponse = user.getUserResponse(eventId);
            inviteResponseMap.put(user, userResponse);
        }

        return inviteResponseMap;
    }
}

package EventManagement;

import EventManagement.dto.Building;
import EventManagement.dto.Event;
import EventManagement.dto.Room;
import EventManagement.dto.User;
import EventManagement.enums.EventType;
import EventManagement.enums.InviteResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainDriver {
    /**
     * Assumptions and declarations
     * Using lombok getter and setter to save some time for coding
     * For the sake of simplicity i'm considering date as an epoch and treating it as string for now
     * Considering location to be a building that will have rooms in them where the events will be scheduled
     * assuming all of the id in every DTO (like room, user, building etc) to be unique, hence no validation while creating those objects
     **/
    public static void main(String[] args) {
        EventMgmtResource eventMgmtResource = EventMgmtResource.getInstance();
        Building building = Building.getInstance();

        User user1 = new User(1, "A", "a@gmail.com");
        User user2 = new User(2, "B", "b@gmail.com");
        User user3 = new User(3, "C", "c@gmail.com");

        Room room1 = new Room(1);
        Room room2 = new Room(1);
        Room room3 = new Room(1);
        Room room4 = new Room(1);

        building.addRoom(room1);
        building.addRoom(room2);
        building.addRoom(room3);
        building.addRoom(room4);

        List<User> guest = new ArrayList<User>();
        guest.add(user2);
        guest.add(user3);

        Event event = new Event();
        event.setId(1);
        event.setOwner(user1);
        event.setRoomId(room1.getId());
        event.setType(EventType.meeting);
        event.setStartDate(1);
        event.setEndDate(2);
        event.setInvitedUsers(guest);

        Event createdEvent = eventMgmtResource.createUpdateEvent(event, user1);
        if(createdEvent == null){
            System.out.println("Some validation error in creating event");
        }else {
            System.out.println("Event created: " + createdEvent.getId());
        }

        Map<User, InviteResponse> inviteResponse = eventMgmtResource.getInviteResponse(event.getId());

        if(inviteResponse != null && !inviteResponse.isEmpty()){
            for(User user : inviteResponse.keySet()){
                System.out.println("User: " + user.getName() + " has " + inviteResponse.get(user) + " response");
            }
        }

        user2.setInviteResponse(event.getId(), InviteResponse.accept);
        System.out.println("User: " + user2.getName() + " accepted envite for: " + event.getId());

        inviteResponse = eventMgmtResource.getInviteResponse(event.getId());
        if(inviteResponse != null && !inviteResponse.isEmpty()){
            for(User user : inviteResponse.keySet()){
                System.out.println("User: " + user.getName() + " has " + inviteResponse.get(user) + " response");
            }
        }

        boolean cancelled = eventMgmtResource.cancelEvent(event, user1);

        System.out.println("Event is cancelled: " + cancelled);
    }
}

package EventManagement;

import EventManagement.enums.EventServiceType;
import EventManagement.service.EventService;
import EventManagement.service.EventServiceImpl;

public class EventServiceFactory {
    public EventService getEvengetEventMgmtService(EventServiceType eventServiceType){
        if(eventServiceType.equals(EventServiceType.inmemory))
            new EventServiceImpl();

        return new EventServiceImpl();
    }
}

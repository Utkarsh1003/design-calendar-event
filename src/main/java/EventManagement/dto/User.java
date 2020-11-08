package EventManagement.dto;

import EventManagement.enums.InviteResponse;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public class User {
    @Getter
    private Integer id;

    @Getter
    private String name;

    @Getter
    private String email;

    private Map<Integer, InviteResponse> inviteResponseMap;

    public User(Integer id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.inviteResponseMap = new HashMap<Integer, InviteResponse>();
    }

    public InviteResponse getUserResponse(Integer eventId){
        if(inviteResponseMap.get(eventId) == null)
            inviteResponseMap.put(eventId, InviteResponse.neutral);

        return inviteResponseMap.get(eventId);
    }

    public void setInviteResponse(Integer eventId, InviteResponse response){
        inviteResponseMap.put(eventId, response);
    }
}

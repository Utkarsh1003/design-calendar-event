package EventManagement.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class Building {
    private static Building instance;

    public static Building getInstance(){
        if(instance == null)
            instance = new Building();

        return instance;
    }

    private Map<Integer, Room> roomMap;
    private Integer id;
    private Double lat;
    private Double lng;
    private String address;

    public Building() {
        this.id = 1;
        this.lat = 1.0;
        this.lng = 1.0;
        this.address = "abc";
        this.roomMap = new HashMap<Integer, Room>();
    }

    public void addRoom(Room room){
        if(roomMap.get(room.getId()) == null)
            roomMap.put(room.getId(), room);
    }
}

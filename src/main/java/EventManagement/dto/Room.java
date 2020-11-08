package EventManagement.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
public class Room {
    private Integer id;
    private List<int[]> slots;

    public Room(Integer id) {
        this.id = id;
        this.slots = new ArrayList<int[]>();
    }

    public void addSlot(int startTime, int endTime){
        int[] slot = {startTime, endTime};
        slots.add(slot);
    }

    //this is the logic to figure out a overlapping slot
    //if slot overlap return false
    //else return true
    public boolean isSlotAvailable(int startTime, int endTime){
        Collections.sort(slots, new Comparator<int[]>() {
            public int compare(int[] range1, int[] range2) {
                if(range1[0] == range2[0]){
                    if(range1[1] == range2[1])
                        return 0;

                    if(range1[1] > range2[1])
                        return 1;

                    return -1;
                }

                if(range1[0] > range2[0])
                    return 1;

                return -1;
            }
        });

        for (int[] slot : slots) {
            if(slot[0] < startTime && startTime < slot[1])
                return false;

            if(slot[0] < endTime && endTime < slot[1])
                return false;
        }

        return true;
    }
}

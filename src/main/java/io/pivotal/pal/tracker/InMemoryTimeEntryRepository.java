package io.pivotal.pal.tracker;

import org.springframework.context.annotation.Bean;

import java.util.*;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {
    private HashMap<Long, TimeEntry> timeEntries = new HashMap<Long, TimeEntry>();
    private Long currentId=1L;

    @Override
    public TimeEntry create(TimeEntry timeEntry) {
        Long id = currentId++;
        timeEntry.setId(id);
        timeEntries.put(id, timeEntry);
        return timeEntries.get(id);
    }
    @Override
    public TimeEntry find(long id){
        return timeEntries.get(id);
    }
    @Override
    public List<TimeEntry> list(){
        return new ArrayList<>(timeEntries.values());
    }
    @Override
    public TimeEntry update(long id,TimeEntry timeEntry){
        if(find(id) != null){
            timeEntry.setId(id);
            timeEntries.replace(id,timeEntry);
            return timeEntry;
        }else{
            return null;
        }
    }
    @Override
    public void delete(long id){
        timeEntries.remove(id);
    }
}
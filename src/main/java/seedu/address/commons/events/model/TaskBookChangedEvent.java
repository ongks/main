package seedu.address.commons.events.model;

import seedu.address.commons.events.BaseEvent;
import seedu.address.model.ReadOnlyTaskBook;

/** Indicates the AddressBook in the model has changed*/
public class TaskBookChangedEvent extends BaseEvent {

    public final ReadOnlyTaskBook data;

    public TaskBookChangedEvent(ReadOnlyTaskBook data){
        this.data = data;
    }

    @Override
    public String toString() {
        return "number of persons " + data.getTaskList().size() + ", number of tags " + data.getTagList().size();
    }
}

package mvc;

import java.util.Collection;
import java.util.List;

public class Contacts {
    private Collection<Contact> contacts;

    public Contacts(Collection<Contact> contacts) {
        this.contacts = contacts;
    }

    public Collection<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

}

package com.moyu;

public class MyContacts {
    private String name;
    private String phoneno;
    private String note;

    MyContacts() {
    }
    MyContacts(String name_, String phoneno_, String note_) {
        this.name = name_;
        this.phoneno = phoneno_;
        this.note = note_;
    }

    public String getName() {
        return name;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public String getNote() {
        return note;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }
}

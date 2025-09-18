package com.inventar.InventoryManagement.dto;

import java.util.Date;

public class CreateOrderDTO {
    private Date deadlineDate;
    private String notes;

    public CreateOrderDTO() {
    }

    public Date getDeadlineDate() {
        return deadlineDate;
    }

    public void setDeadlineDate(Date deadlineDate) {
        this.deadlineDate = deadlineDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}

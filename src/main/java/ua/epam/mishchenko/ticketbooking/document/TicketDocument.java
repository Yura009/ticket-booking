package ua.epam.mishchenko.ticketbooking.document;

import lombok.Data;
import org.bson.types.ObjectId;
import ua.epam.mishchenko.ticketbooking.model.Category;

@Data
public class TicketDocument {

    private ObjectId eventId;

    private int place;

    private Category category;
}

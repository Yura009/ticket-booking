package ua.epam.mishchenko.ticketbooking.document;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "users")
public class UserDocument {

    @Id
    private ObjectId id;

    private String name;

    private String email;

    private List<TicketDocument> tickets;

    private UserAccountDocument userAccount;
}

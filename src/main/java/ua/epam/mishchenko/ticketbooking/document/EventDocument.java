package ua.epam.mishchenko.ticketbooking.document;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Document(collection = "events")
public class EventDocument {

    @Id
    private ObjectId id;

    private String title;

    private Date date;

    private BigDecimal ticketPrice;
}

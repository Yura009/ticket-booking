package ua.epam.mishchenko.ticketbooking.document;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class UserAccountDocument {

    private BigDecimal balance;
}

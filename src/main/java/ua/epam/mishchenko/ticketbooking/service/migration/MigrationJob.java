package ua.epam.mishchenko.ticketbooking.service.migration;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.epam.mishchenko.ticketbooking.document.TicketDocument;
import ua.epam.mishchenko.ticketbooking.document.UserAccountDocument;
import ua.epam.mishchenko.ticketbooking.document.UserDocument;
import ua.epam.mishchenko.ticketbooking.repository.UserMongoRepository;
import ua.epam.mishchenko.ticketbooking.repository.UserRepository;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class MigrationJob {
    private final UserRepository userRepository;
    private final UserMongoRepository userMongoRepository;

    @PostConstruct
    public void init() {
        migrateUsers();
    }

    @Transactional(readOnly = true)
    public void migrateUsers() {
        userRepository.findAll().forEach(user -> {
            List<TicketDocument> ticketDocs = user.getTickets().stream().map(ticket -> {
                TicketDocument td = new TicketDocument();
                td.setEventId(new ObjectId(ticket.getEvent().getId().toString()));
                td.setPlace(ticket.getPlace());
                td.setCategory(ticket.getCategory());
                return td;
            }).collect(Collectors.toList());

            UserAccountDocument userAccountDoc = new UserAccountDocument();
            if (user.getUserAccount() != null) {
                userAccountDoc.setBalance(user.getUserAccount().getMoney());
            }

            UserDocument userDoc = new UserDocument();
            userDoc.setName(user.getName());
            userDoc.setEmail(user.getEmail());
            userDoc.setTickets(ticketDocs);
            userDoc.setUserAccount(userAccountDoc);

            userMongoRepository.save(userDoc);
            log.info(String.valueOf(userMongoRepository.countUsersByEmailDomain()));

        });
    }
}

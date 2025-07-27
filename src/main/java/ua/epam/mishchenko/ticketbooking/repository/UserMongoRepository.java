package ua.epam.mishchenko.ticketbooking.repository;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import ua.epam.mishchenko.ticketbooking.document.UserDocument;

import java.util.List;

public interface UserMongoRepository extends MongoRepository<UserDocument, String> {
    @Aggregation(pipeline = {
            "{ '$group': { '_id': { $substrCP: ['$email', { $indexOfBytes: ['$email', '@'] }, -1] }, count: { $sum: 1 } } }"
    })
    List<UserDocument> countUsersByEmailDomain();
}

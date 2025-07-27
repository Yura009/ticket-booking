package taskmanager.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import taskmanager.model.Task;

import java.time.LocalDateTime;
import java.util.List;

public interface TaskRepository extends MongoRepository<Task, String> {
    @Query("{ $text: { $search: ?0 } }")
    List<Task> searchByDescription(String keyword);

    @Query("{ 'subtasks.name': { $regex: ?0, $options: 'i' } }")
    List<Task> searchBySubtaskName(String keyword);

    List<Task> findByDeadlineBefore(LocalDateTime now);

    List<Task> findByCategory(String category);

    @Query(value = "{ 'category': ?0 }", fields = "{ 'subtasks': 1, '_id': 0 }")
    List<Task> findSubtasksByCategory(String category);
}

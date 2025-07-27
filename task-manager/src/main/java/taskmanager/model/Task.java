package taskmanager.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Document(collection = "tasks")
public class Task {
    @Id
    private String id;
    private LocalDateTime createdAt;
    private LocalDateTime deadline;
    private String name;
    private String description;
    private List<SubTask> subtasks;
    private String category;
}

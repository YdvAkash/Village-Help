package Clarify.demo.model;
import Clarify.demo.model.Enum.PostStatus;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Getter
@Setter
@Document(collection = "posts")
public class PostModel {

    @Id
    private String id;
    private Integer userId;
    private Integer villageCode;
    private String title;
    private String description;
    private String imageUrl;
    private PostStatus status; // PENDING, RESOLVED
    private Instant createdAt = Instant.now();
}

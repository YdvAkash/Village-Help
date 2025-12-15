package Clarify.demo.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class CreatePostRequest {

    private Integer userId;
    private Integer villageCode;
    private String title;
    private String description;

    private MultipartFile image; // ✅ file comes here
}

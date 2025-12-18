package Clarify.demo.controller;
import Clarify.demo.dto.ApiResponse;
import Clarify.demo.dto.CreatePostRequest;
import Clarify.demo.model.PostModel;
import Clarify.demo.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping(
            value = "/create",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<ApiResponse<PostModel>> createPost(
            @ModelAttribute CreatePostRequest request
    )
    {
        PostModel post = postService.createPost(request);

        ApiResponse<PostModel> response =  ApiResponse.<PostModel>builder()
                .success(true)
                .message("Post Created Successfully")
                .data(post)
                .statusCode(200)
                .timestamp(System.currentTimeMillis())
                .build();

        return ResponseEntity.ok(response);

    }
}

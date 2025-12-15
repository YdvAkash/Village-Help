package Clarify.demo.controller;
import Clarify.demo.dto.CreatePostRequest;
import Clarify.demo.model.PostModel;
import Clarify.demo.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
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
    public PostModel createPost(
            @ModelAttribute CreatePostRequest request
    ) {
        return postService.createPost(request);
    }
}

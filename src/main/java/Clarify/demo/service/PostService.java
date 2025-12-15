package Clarify.demo.service;
import Clarify.demo.model.VillageModel;
import Clarify.demo.service.PostUploadService;
import Clarify.demo.dto.CreatePostRequest;
import Clarify.demo.model.PostModel;
import Clarify.demo.model.UserModel;
import Clarify.demo.repository.PostRepository;
import Clarify.demo.repository.UserRepository;
import Clarify.demo.repository.VillageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.UUID;

import static Clarify.demo.model.Enum.PostStatus.PENDING;
@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final VillageRepository villageRepository;
    private final PostUploadService postUploadService;

    public PostModel createPost(CreatePostRequest request) {

        // ✅ Validate User
        UserModel user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // ✅ Validate Village (single DB hit)
        VillageModel village = villageRepository
                .findByvillageCode(request.getVillageCode());

        if (village == null) {
            throw new RuntimeException("Village not found");
        }

        // ✅ Generate postId FIRST
        String postId = java.util.UUID.randomUUID().toString();

        // ✅ Upload Image
        String imageUrl = null;
        try {
            if (request.getImage() != null && !request.getImage().isEmpty()) {
                imageUrl = postUploadService.uploadPost(
                        request.getImage(),
                        village.getState(),
                        village.getDistrict(),
                        village.getTehsil(),
                        String.valueOf(request.getVillageCode()),
                        postId
                );
            }
        } catch (Exception e) {
            throw new RuntimeException("Image upload failed");
        }

        // ✅ Save Post
        PostModel post = new PostModel();
        post.setId(postId);                 // important
        post.setUserId(user.getId());
        post.setVillageCode(request.getVillageCode());
        post.setTitle(request.getTitle());
        post.setDescription(request.getDescription());
        post.setImageUrl(imageUrl);
        post.setStatus(PENDING);

        return postRepository.save(post);
    }
}

package Clarify.demo.repository;
import java.util.*;
import Clarify.demo.model.PostModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PostRepository
        extends MongoRepository<PostModel, String> {

    List<PostModel> findByVillageCode(Integer villageCode);
    List<PostModel> findByUserId(Integer userId);
}

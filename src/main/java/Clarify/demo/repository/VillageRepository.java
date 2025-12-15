package Clarify.demo.repository;

import Clarify.demo.model.VillageModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface VillageRepository extends JpaRepository<VillageModel,Integer> {
    boolean existsByvillageCode(Integer villageCode);
    VillageModel findByvillageCode(Integer villageCode);
}

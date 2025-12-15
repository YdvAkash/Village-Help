package Clarify.demo.service;

import Clarify.demo.dto.CreateVillageRequest;
import Clarify.demo.model.VillageModel;
import Clarify.demo.repository.VillageRepository;
import org.springframework.stereotype.Service;

@Service
public class VillageService {
    private final VillageRepository villageRepository;

    public VillageService(VillageRepository villageRepository) {
        this.villageRepository = villageRepository;
    }

    public VillageModel create(CreateVillageRequest request){
        if(villageRepository.existsByvillageCode(request.villageCode)){
            throw new RuntimeException("Village already exists");
        }

        VillageModel villageModel = new VillageModel();
        villageModel.setVillageName(request.villageName);
        villageModel.setVillageCode(request.villageCode);
        villageModel.setState(request.state);
        villageModel.setDistrict(request.district);
        villageModel.setTehsil(request.tehsil);

        VillageModel savedVillage = villageRepository.save(villageModel);

        return savedVillage;
    }
}

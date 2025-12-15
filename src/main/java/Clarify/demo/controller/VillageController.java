package Clarify.demo.controller;


import Clarify.demo.dto.CreateVillageRequest;
import Clarify.demo.model.VillageModel;
import Clarify.demo.service.VillageService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/village")
public class VillageController {
    private final VillageService villageService;

    public VillageController(VillageService villageService) {
        this.villageService = villageService;
    }

    @PostMapping("/create")
    public VillageModel create(@RequestBody CreateVillageRequest request) {
        return villageService.create(request);
    }
}

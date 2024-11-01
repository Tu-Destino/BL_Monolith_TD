package com.TD.BL_Monolith_TD.infrastructure.services;

import com.TD.BL_Monolith_TD.api.dto.requests.PlaceRequest;
import com.TD.BL_Monolith_TD.api.dto.response.PlaceResponse;
import com.TD.BL_Monolith_TD.api.dto.response.SearchListPlaceResponse;
import com.TD.BL_Monolith_TD.domain.entities.Place;
import com.TD.BL_Monolith_TD.domain.repositories.PlaceRepository;
import com.TD.BL_Monolith_TD.infrastructure.abstract_services.IPlaceService;
import com.TD.BL_Monolith_TD.infrastructure.helpers.SupportService;
import com.TD.BL_Monolith_TD.infrastructure.helpers.mappers.PlaceMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@AllArgsConstructor
public class PlaceService implements IPlaceService {

    @Autowired
    private final PlaceRepository placeRepository;

    @Autowired
    private final PlaceMapper placeMapper;

    @Autowired
    private final SupportService<Place> placeSupportService;

    /**
     * Creates a new Place entity from the given PlaceRequest and saves it to the repository.
     * Uses PlaceMapper to convert the request to an entity and then map the saved entity back to a response.
     */
    @Override
    public PlaceResponse create(PlaceRequest request) {
        return this.placeMapper.toResponse(this.placeRepository.save(this.placeMapper.toEntity(request)));
    }

    /**
     * Updates an existing Place entity identified by its ID with new data from PlaceRequest.
     * Finds the entity using placeSupportService, copies properties from the request to the entity, and saves it back.
     */
    @Override
    public PlaceResponse update(Long aLong, PlaceRequest request) {
        Place place = this.placeSupportService.findByID(this.placeRepository, aLong, "Place");
        BeanUtils.copyProperties(request, place);
        return this.placeMapper.toResponse(this.placeRepository.save(place));
    }

    /**
     * Retrieves all Place entities from the repository and maps them to a list of PlaceResponse.
     */
    @Override
    public List<PlaceResponse> getAll() {
        return this.placeMapper.toListResponse(this.placeRepository.findAll());
    }

    /**
     * Finds a specific Place entity by its ID and maps it to a PlaceResponse.
     */
    @Override
    public PlaceResponse find(Long id) {
        return this.placeMapper.toResponse(this.placeSupportService.findByID(this.placeRepository, id, "Place"));
    }

    /**
     * Deletes a Place entity identified by its ID.
     * Finds the entity using placeSupportService and then deletes it from the repository.
     */
    @Override
    public void delete(Long aLong) {
        this.placeRepository.delete(this.placeSupportService.findByID(this.placeRepository, aLong, "Place"));
    }

    /**
     * Retrieves a list of all place names from the repository and returns them wrapped in a SearchListPlaceResponse.
     */
    @Override
    public SearchListPlaceResponse getListNamePlace() {
        return new SearchListPlaceResponse(this.placeRepository.findAllTitles());
    }

    /**
     * Finds a Place entity by its name and maps it to a PlaceResponse.
     */
    @Override
    public PlaceResponse findByPlaceName(String placeName) {
        return this.placeMapper.toResponse(this.placeRepository.findByTitle(placeName));
    }
    @Override
    public List<PlaceResponse> CreateList(List<PlaceRequest> requests){
        List<PlaceResponse> responses = new ArrayList<>();

        requests.forEach(placeRequest -> {
            responses.add(this.create(placeRequest));
        });
        return  responses;
    }
}

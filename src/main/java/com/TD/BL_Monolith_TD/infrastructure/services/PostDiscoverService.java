package com.TD.BL_Monolith_TD.infrastructure.services;

import com.TD.BL_Monolith_TD.api.dto.requests.LabelsRequest;
import com.TD.BL_Monolith_TD.api.dto.requests.PostDiscoverRequest;
import com.TD.BL_Monolith_TD.api.dto.response.PostDiscoverResponse;
import com.TD.BL_Monolith_TD.domain.entities.Place;
import com.TD.BL_Monolith_TD.domain.entities.PostDiscover;
import com.TD.BL_Monolith_TD.domain.entities.User;
import com.TD.BL_Monolith_TD.domain.repositories.PlaceRepository;
import com.TD.BL_Monolith_TD.domain.repositories.PostDiscoverRepository;
import com.TD.BL_Monolith_TD.domain.repositories.UserRepository;
import com.TD.BL_Monolith_TD.infrastructure.abstract_services.IPostDiscoverService;
import com.TD.BL_Monolith_TD.infrastructure.helpers.SupportService;
import com.TD.BL_Monolith_TD.infrastructure.helpers.mappers.PostDiscoverMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PostDiscoverService implements IPostDiscoverService {
    @Autowired
    private final PostDiscoverRepository postDiscoverRepository;

    @Autowired
    private final PlaceRepository placeRepository;

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final SupportService<User> userSupport;

    @Autowired
    private final SupportService<PostDiscover> postDiscoverSupport;

    @Autowired
    private final SupportService<Place> placeSupport;

    @Autowired
    private final PostDiscoverMapper postDiscoverMapper;

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Creates a new PostDiscover entity from the given PostDiscoverRequest and saves it to the repository.
     * Uses Place and User services to find related entities and set them in the PostDiscover entity.
     */
    @Override
    public PostDiscoverResponse create(PostDiscoverRequest request) {
        Place place = this.placeSupport.findByID(this.placeRepository, request.getPlace_id(), "Place");
        User user = this.userSupport.findByUUID(this.userRepository, request.getUser_id(), "User");
        PostDiscover postDiscover = this.postDiscoverMapper.toEntity(request);
        postDiscover.setPlace(place);
        postDiscover.setUser(user);
        return this.postDiscoverMapper.toResponse(this.postDiscoverRepository.save(postDiscover));
    }

    /**
     * Updates an existing PostDiscover entity identified by its ID with new data from PostDiscoverRequest.
     * Finds the entity using postDiscoverSupport, updates User and Place if necessary, and saves it back.
     */
    @Override
    public PostDiscoverResponse update(String id, PostDiscoverRequest request) {
        PostDiscover postDiscover = this.postDiscoverSupport.findByUUID(this.postDiscoverRepository, id, "PostDiscover");
        if (!postDiscover.getUser().getId().equals(request.getUser_id())) {
            User user = this.userSupport.findByUUID(this.userRepository, request.getUser_id(), "User");
            postDiscover.setUser(user);
        }
        if (!postDiscover.getPlace().getId().equals(request.getPlace_id())) {
            Place place = this.placeSupport.findByID(this.placeRepository, request.getPlace_id(), "Place");
            postDiscover.setPlace(place);
        }
        BeanUtils.copyProperties(request, postDiscover);
        return this.postDiscoverMapper.toResponse(this.postDiscoverRepository.save(postDiscover));
    }

    /**
     * Retrieves all PostDiscover entities from the repository and maps them to a list of PostDiscoverResponse.
     */
    @Override
    public List<PostDiscoverResponse> getAll() {
        return this.postDiscoverMapper.toListResponse(this.postDiscoverRepository.findAll());
    }

    /**
     * Finds a specific PostDiscover entity by its ID and maps it to a PostDiscoverResponse.
     */
    @Override
    public PostDiscoverResponse find(String id) {
        return this.postDiscoverMapper.toResponse(this.postDiscoverSupport.findByUUID(this.postDiscoverRepository, id, "PostDiscover"));
    }

    /**
     * Deletes a PostDiscover entity identified by its ID.
     * Finds the entity using postDiscoverSupport and then deletes it from the repository.
     */
    @Override
    public void delete(String id) {
        this.postDiscoverRepository.delete(this.postDiscoverSupport.findByUUID(this.postDiscoverRepository, id, "PostDiscover"));
    }

    /**
     * Finds PostDiscover entities by tags provided in LabelsRequest.
     * Constructs a dynamic JPQL query to search for matches and calculates relevance based on the number of matching tags.
     */
    @Override
    public List<PostDiscoverResponse> findByTags(LabelsRequest labelsRequest) {
        String tagsString = labelsRequest.getArray();
        String[] tagsArray = tagsString.split(",");
        List<String> tags = Arrays.stream(tagsArray).map(String::trim).collect(Collectors.toList());

        // Dynamic search
        StringBuilder jpql = new StringBuilder("SELECT p, ");
        // Calculate the number of matches
        jpql.append("(CASE ");
        // All labels matched
        jpql.append("WHEN ");
        for (int i = 0; i < tags.size(); i++) {
            jpql.append("p.tags LIKE :tag").append(i);
            if (i < tags.size() - 1) jpql.append(" AND ");
        }
        jpql.append(" THEN ").append(tags.size()).append(" ");
        // Combinations of [n-1 . 1]
        for (int matchCount = tags.size() - 1; matchCount > 0; matchCount--) {
            List<List<Integer>> combinations = getCombinations(tags.size(), matchCount);
            for (List<Integer> combination : combinations) {
                jpql.append("WHEN (");
                for (int i = 0; i < combination.size(); i++) {
                    jpql.append("p.tags LIKE :tag").append(combination.get(i));
                    if (i < combination.size() - 1) jpql.append(" AND ");
                }
                jpql.append(") THEN ").append(matchCount).append(" ");
            }
        }
        jpql.append("ELSE 0 END) as relevance FROM postDiscover p WHERE ");
        // Condition for at least one match
        jpql.append("(");
        for (int i = 0; i < tags.size(); i++) {
            if (i > 0) jpql.append(" OR ");
            jpql.append("p.tags LIKE :tag").append(i);
        }
        jpql.append(") ORDER BY relevance DESC");

        // Create Query
        Query query = entityManager.createQuery(jpql.toString(), Object[].class);
        // Assign dynamic parameters
        for (int i = 0; i < tags.size(); i++) {
            query.setParameter("tag" + i, "%" + tags.get(i) + "%");
        }
        // Execute query
        List<Object[]> results = query.getResultList();
        // Convert the results to PostDiscoverResponse
        List<PostDiscoverResponse> response = new ArrayList<>();
        for (Object[] result : results) {
            PostDiscover postDiscover = (PostDiscover) result[0];
            response.add(this.postDiscoverMapper.toResponse(postDiscover));
        }
        return response;
    }

    // Helper function to generate combinations
    private List<List<Integer>> getCombinations(int n, int k) {
        List<List<Integer>> combinations = new ArrayList<>();
        generateCombinations(combinations, new ArrayList<>(), 0, n, k);
        return combinations;
    }

    // Recursive function to generate combinations
    private void generateCombinations(List<List<Integer>> combinations, List<Integer> current, int start, int n, int k) {
        if (k == 0) {
            combinations.add(new ArrayList<>(current));
            return;
        }
        for (int i = start; i < n; i++) {
            current.add(i);
            generateCombinations(combinations, current, i + 1, n, k - 1);
            current.remove(current.size() - 1);
        }
    }

    @Override
    public List<String> getTags() {
        List<String> listFull = this.postDiscoverRepository.findAllTags();
        List<String> listLabel = new ArrayList<>();
        listFull.forEach(label -> {
            String[] tags = label.split(",");
            Arrays.stream(tags).forEach(tag -> {
                if (!listLabel.contains(tag)) {
                    listLabel.add(tag);
                }
            });
        });

        return  listLabel;
    }

    @Override
    public List<String> getUrlImgByTitle(String title) {
        return this.postDiscoverRepository.findAllUrlImgOrderByUserId(title,"0d215f49-a3f4-4165-bf52-b42649bc85c3");
    }
}

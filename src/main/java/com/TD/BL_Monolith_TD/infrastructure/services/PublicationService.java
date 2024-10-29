package com.TD.BL_Monolith_TD.infrastructure.services;

import com.TD.BL_Monolith_TD.util.enums.StatusPublication;
import com.TD.BL_Monolith_TD.api.dto.requests.PublicationRequest;
import com.TD.BL_Monolith_TD.api.dto.response.PublicationResponse;
import com.TD.BL_Monolith_TD.domain.entities.Publication;
import com.TD.BL_Monolith_TD.domain.entities.User;
import com.TD.BL_Monolith_TD.domain.repositories.PublicationRepository;
import com.TD.BL_Monolith_TD.domain.repositories.UserRepository;
import com.TD.BL_Monolith_TD.infrastructure.abstract_services.IPublicationService;
import com.TD.BL_Monolith_TD.infrastructure.helpers.SupportService;
import com.TD.BL_Monolith_TD.infrastructure.helpers.mappers.PublicationMapper;

import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class PublicationService implements IPublicationService {
    @Autowired
    private final PublicationRepository publicacionRepository;
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final PublicationMapper publicationMapper;
    @Autowired
    private final SupportService<Publication> publicationSupport;
    @Autowired
    private final SupportService<User> UserSupport;

    @Override
    public PublicationResponse create(PublicationRequest request) {
        User user = this.UserSupport.findByUUID(this.userRepository,request.getUser_id(),"User");

        Publication publication = this.publicationMapper.toEntity(request);
        publication.setUser(user);
        publication.setEnum_status(StatusPublication.PENDIENTE);
        publication.setPublication_date(LocalDateTime.now());
        return this.publicationMapper.toResponse(this.publicacionRepository.save(publication));
    }

    @Override
    public PublicationResponse update(Long aLong, PublicationRequest request) {

        Publication publication =this.publicationSupport.findByID(this.publicacionRepository,aLong,"Publication");
        if (!publication.getUser().getId().equals(request.getUser_id())){
            User user =this.UserSupport.findByUUID(this.userRepository,request.getUser_id(),"User");
            publication.setUser(user);

        }
        BeanUtils.copyProperties(request,publication);


        return this.publicationMapper.toResponse(this.publicacionRepository.save(publication));

    }

    @Override
    public List<PublicationResponse> getAll() {
        return this.publicationMapper.toListResponse(this.publicacionRepository.findAll());
    }

    @Override
    public PublicationResponse find(Long id){
       return this.publicationMapper.toResponse(this.publicationSupport.findByID(this.publicacionRepository,id,"Publication"));
    }
    @Override
    public void delete(Long aLong) {
        this.publicacionRepository.delete(this.publicationSupport.findByID(this.publicacionRepository,aLong,"Publication"));
    }

}

package com.TD.BL_Monolith_TD.infrastructure.services;

import com.TD.BL_Monolith_TD.api.dto.requests.CommentRequest;
import com.TD.BL_Monolith_TD.api.dto.response.CommentResponse;
import com.TD.BL_Monolith_TD.domain.entities.Comment;
import com.TD.BL_Monolith_TD.domain.entities.Place;
import com.TD.BL_Monolith_TD.domain.entities.User;
import com.TD.BL_Monolith_TD.domain.repositories.CommentRepository;
import com.TD.BL_Monolith_TD.domain.repositories.PlaceRepository;
import com.TD.BL_Monolith_TD.domain.repositories.UserRepository;
import com.TD.BL_Monolith_TD.infrastructure.abstract_services.ICommentService;
import com.TD.BL_Monolith_TD.infrastructure.helpers.SupportService;
import com.TD.BL_Monolith_TD.infrastructure.helpers.mappers.CommentMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Service
@AllArgsConstructor
public class CommentService implements ICommentService {

    @Autowired
    private final CommentRepository commentRepository;
    @Autowired
    private final PlaceRepository placeRepository;
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final CommentMapper commentMapper;
    @Autowired
    private final SupportService<Comment> commentSupport;
    @Autowired
    private final SupportService<Place> placeSupport;
    @Autowired
    private final SupportService<User> userSupport;



    @Override
    public CommentResponse create(CommentRequest request) {
        User user =this.userSupport.findByUUID(this.userRepository,request.getUser_id(),"User");
        Place place = this.placeSupport.findByID(this.placeRepository,request.getPlace_id(),"Place");

        Comment comment =this.commentMapper.toEntity(request);
        comment.setPlace(place);
        comment.setUser(user);
        comment.setDate(LocalDate.now());
        return this.commentMapper.toResponse(this.commentRepository.save(comment));
    }


    @Override
    public CommentResponse update(Long id, CommentRequest request) {
        Comment comment = this.commentSupport.findByID(this.commentRepository,id,"Comment");
        if (!comment.getUser().getId().equals(request.getUser_id())){
            User user =this.userSupport.findByUUID(this.userRepository,request.getUser_id(),"User");
            comment.setUser(user);
        }
        if (comment.getPlace().getId() != request.getPlace_id() ){
            Place place = this.placeSupport.findByID(this.placeRepository,request.getPlace_id(),"Place");
            comment.setPlace(place);
        }
        BeanUtils.copyProperties(request,comment);
        return this.commentMapper.toResponse(this.commentRepository.save(comment));
    }


    @Override
    public List<CommentResponse> getAll() {
        return this.commentMapper.toListResponse(this.commentRepository.findAll());
    }

    @Override
    public List<CommentResponse> getAllByIdPlace(Long idPLace) {
        return this.commentMapper.toListResponse(this.commentRepository.findAllByPlace_id(idPLace));
    }
    @Override
    public CommentResponse find(Long id){
        return this.commentMapper.toResponse(this.commentSupport.findByID(this.commentRepository,id,"Comment"));
    }

    @Override
    public void delete(Long id) {
     this.commentRepository.delete(this.commentSupport.findByID(this.commentRepository,id,"Comment"));
    }


}

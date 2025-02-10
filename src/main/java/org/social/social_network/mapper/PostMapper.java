package org.social.social_network.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.social.social_network.dto.PostRqDto;
import org.social.social_network.dto.PostUpdateRqDto;
import org.social.social_network.entity.Post;

@Mapper(componentModel = "spring")
public interface PostMapper {

    @Mapping(source = "authorId", target = "authorId")
    @Mapping(source = "description", target = "description")
    Post postRqDtoToPost(PostRqDto postRqDto);

    @Mapping(source = "likes", target = "likes")
    @Mapping(source = "description", target = "description")
    Post postUpdateRqDtoToPost(PostUpdateRqDto postUpdateRqDto);


}

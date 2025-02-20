package org.social.social_network.mapper;

import org.mapstruct.Mapper;
import org.social.model.PostRqDto;
import org.social.model.PostRsDto;
import org.social.model.PostUpdateRqDto;
import org.social.social_network.entity.Post;
import org.social.social_network.dto.PostRqModel;
import org.social.social_network.dto.PostUpdateRqModel;

@Mapper(componentModel = "spring")
public interface PostMapper {

    PostRqModel postRqDtoToPostModel(PostRqDto dto);

    PostUpdateRqModel postUpdateRqDtoToPostUpdateModel(PostUpdateRqDto dto);
    PostRsDto postToPostRsDto(Post post);
    PostUpdateRqModel postUpdateRqModelToPostUpdateRqDto(PostUpdateRqDto postUpdateRqDto);
}

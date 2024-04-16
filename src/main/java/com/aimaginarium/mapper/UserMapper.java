package com.aimaginarium.mapper;

import com.aimaginarium.dto.UserDto;
import com.aimaginarium.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper extends Mappable<User, UserDto> {
}

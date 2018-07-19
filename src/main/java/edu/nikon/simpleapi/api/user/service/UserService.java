package edu.nikon.simpleapi.api.user.service;

import edu.nikon.simpleapi.api.common.exception.DataNotFoundException;
import edu.nikon.simpleapi.api.user.domain.User;
import edu.nikon.simpleapi.api.user.dto.FilterUserDto;
import edu.nikon.simpleapi.api.user.dto.SaveUserDto;
import edu.nikon.simpleapi.api.user.dto.UpdateUserDto;
import edu.nikon.simpleapi.api.user.dto.UserDetailedDto;
import edu.nikon.simpleapi.api.user.dto.UserItemDto;

import java.util.List;

/**
 * Service used to get user data from the database
 */
public interface UserService {

    /**
     * Filter users by parameters defined in {@link FilterUserDto}
     *
     * @param dto user filter data access object
     * @return List of filtered users
     */
    List<UserItemDto> filter(FilterUserDto dto);

    /**
     * Finds office with requested id. If not found throws {@link DataNotFoundException}
     *
     * @param id of requested office
     * @return dto of requested office
     */
    UserDetailedDto findById(long id);

    /**
     * Save user to db
     *
     * @param dto for save user in db
     * @return saved user entity
     * @see User
     * @see SaveUserDto
     */
    User save(SaveUserDto dto);

    /**
     * Updates user in db
     *
     * @param dto for update user in db
     * @return updated user entity
     * @see User
     * @see UpdateUserDto
     */
    User update(UpdateUserDto dto);
}

package edu.nikon.simpleapi.api.user.dto.mapper;

import edu.nikon.simpleapi.api.common.embeddable.Name;
import edu.nikon.simpleapi.api.user.domain.User;
import edu.nikon.simpleapi.api.user.dto.SaveUserDto;
import edu.nikon.simpleapi.api.user.dto.UserDetailedDto;
import edu.nikon.simpleapi.api.user.dto.UserItemDto;

import java.util.function.Function;

/**
 * Util for mapping from entity to dto and in the opposite direction
 */
public class UserMapper {

    /**
     * Maps user entity to dto which is a list item
     *
     * @return function returning office mapped to {@link UserItemDto}
     * @see User
     * @see UserItemDto
     */
    public static Function<User, UserItemDto> mapEntityToItem() {
        return u -> new UserItemDto(
                u.getOfficeId(),
                u.getName().getFirst(),
                u.getName().getSecond(),
                u.getName().getMiddle(),
                u.getPosition(),
                u.getDocumentCode(),
                u.getCitizenshipCode()
        );
    }

    /**
     * Maps user entity to detailed dto
     *
     * @return function returning user entity mapped to {@link UserDetailedDto}
     * @see User
     * @see UserDetailedDto
     */
    public static Function<User, UserDetailedDto> mapEntityToDetailed() {
        return u -> new UserDetailedDto(
                u.getId(),
                u.getName().getFirst(),
                u.getName().getSecond(),
                u.getName().getMiddle(),
                u.getPosition(),
                u.getPhone(),
                u.getDocumentType().getName(),
                u.getDocumentData().getNumber(),
                u.getDocumentData().getDate(),
                u.getCitizenshipCountry().getName(),
                u.getCitizenshipCountry().getCode(),
                u.getIdentified()
        );
    }

    /**
     * Maps user dto to entity for saving in db
     *
     * @return function returning user dto mapped to {@link User}
     * @see User
     * @see SaveUserDto
     */
    public static Function<SaveUserDto, User> mapSaveDtoToEntity() {
        return dto -> new User.Builder()
                .setName(new Name(dto.getFirstName(), dto.getSecondName(), dto.getMiddleName()))
                .setPosition(dto.getPosition())
                .setPhone(dto.getPhone())
                .setIdentified(dto.getIdentified())
                .build();
    }
}

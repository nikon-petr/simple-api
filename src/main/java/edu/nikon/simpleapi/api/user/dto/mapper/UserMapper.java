package edu.nikon.simpleapi.api.user.dto.mapper;

import edu.nikon.simpleapi.api.common.embeddable.Name;
import edu.nikon.simpleapi.api.user.domain.User;
import edu.nikon.simpleapi.api.user.dto.SaveUserDto;
import edu.nikon.simpleapi.api.user.dto.UpdateUserDto;
import edu.nikon.simpleapi.api.user.dto.UserDetailedDto;
import edu.nikon.simpleapi.api.user.dto.UserItemDto;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Component;

import java.util.function.Function;

/**
 * Class for configure user mapper factory
 */
@Component
public class UserMapper extends ConfigurableMapper {

    @Override
    protected void configure(MapperFactory factory) {
        factory.classMap(User.class, UserItemDto.class)
                .field("name.first", "firstName")
                .field("name.second", "secondName")
                .field("name.middle", "middleName")
                .field("documentCode", "docCode")
                .byDefault()
                .register();

        factory.classMap(User.class, UserDetailedDto.class)
                .field("name.first", "firstName")
                .field("name.second", "secondName")
                .field("name.middle", "middleName")
                .field("documentType.name", "docName")
                .field("documentData.number", "docNumber")
                .field("documentData.date", "docDate")
                .field("citizenshipCountry.name", "citizenshipName")
                .byDefault()
                .register();

        factory.classMap(User.class, SaveUserDto.class)
                .field("name.first", "firstName")
                .field("name.second", "secondName")
                .field("name.middle", "middleName")
                .exclude("documentCode")
                .exclude("citizenshipCode")
                .exclude("officeId")
                .byDefault()
                .register();

        factory.classMap(User.class, UpdateUserDto.class)
                .field("name.first", "firstName")
                .field("name.second", "secondName")
                .field("name.middle", "middleName")
                .exclude("documentCode")
                .exclude("citizenshipCode")
                .exclude("officeId")
                .byDefault()
                .register();
    }
}

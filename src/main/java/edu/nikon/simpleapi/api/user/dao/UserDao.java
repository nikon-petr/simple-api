package edu.nikon.simpleapi.api.user.dao;

import edu.nikon.simpleapi.api.common.dao.BaseDao;
import edu.nikon.simpleapi.api.user.domain.User;

import java.util.List;
import java.util.Optional;

/**
 * Provide an abstract interface to the database user table
 */
public interface UserDao extends BaseDao<User, Long> {

    /**
     * Find entity by id with fetch eager for documentType, documentData, citizenshipCountry
     *
     * @param id entity id
     * @return found entity object
     */
    Optional<User> findByIdEager(Long id);

    /**
     * @param officeId id of office related to the user
     * @param first first name
     * @param second second name
     * @param middle middle name
     * @param position user position
     * @param docCode document code
     * @param citizenshipCode country ISO code of citizenship
     * @return list of users with requested parameters
     * @see User
     */
    List<User> filter(Long officeId, String first, String second, String middle, String position, String docCode,
                      String citizenshipCode);
}

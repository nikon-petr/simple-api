package edu.nikon.simpleapi.api.user.dao;

import edu.nikon.simpleapi.api.common.dao.BaseDao;
import edu.nikon.simpleapi.api.user.domain.DocumentData;

/**
 * Provide an abstract interface to the database document_data table
 */
public interface DocumentDataDao extends BaseDao<DocumentData, Long> {

    /**
     * Returns true if number is unique
     *
     * @param number unique document number
     * @return is unique
     * @see DocumentData
     */
    boolean isUnique(String number);

    /**
     * Returns true if number is unique excluding id
     *
     * @param number unique document number
     * @param userId document data id for exclude
     * @return is unique
     * @see DocumentData
     */
    boolean isUnique(Long userId, String number);
}

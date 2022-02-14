/**
 * 
 */
package com.jumia.phone.book.services;

import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This class provide basic common implementation for all service classes it is
 * responsible for providing Mapping between Entity , Source API implementation
 * logic
 * 
 *
 */
@Service
public class BasicServiceImpl<D, E> {

	// adding logging support
	private static final Logger logger = Logger.getLogger(BasicServiceImpl.class);

	@Autowired
	protected ModelMapper modelMapper;
	

	/**
	 * Convert from Entity to Client Api Model Object
	 * 
	 * @param entity to be converted
	 * @param source client Api model Object
	 * @return Client api Model object
	 */
	@SuppressWarnings("unchecked")
	public D convertToDTO(E entity, D source) {
		D convertedDtoObj = null;
		try {

			// Perform Mapping between Entity and Source APi Client
			convertedDtoObj = (D) modelMapper.map(entity, source.getClass());

		} catch (Exception ex) {
			logger.error("Error when converting to DTO ", ex);

		}
		return convertedDtoObj;
	}

	/**
	 * Convert from Client Api Model Object to Entity
	 * 
	 * @param entity to be converted
	 * @param source client Api model Object
	 * @return Entity Object
	 */
	@SuppressWarnings("unchecked")
	public E convertToEntity(E entity, D source) {
		E convertedEntity = null;
		try {

			// Perform Mapping between Entity and Source APi Client
			convertedEntity = (E) modelMapper.map(source, entity.getClass());

		} catch (Exception ex) {
			logger.error("Error when converting to Entity ", ex);

		}
		return convertedEntity;
	}

}

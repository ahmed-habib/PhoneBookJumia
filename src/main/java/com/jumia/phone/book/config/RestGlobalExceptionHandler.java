package com.jumia.phone.book.config;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.jumia.phone.book.dtos.ResponseError;
import com.jumia.phone.book.dtos.StatusCode;


/**
 * Helper Class that Handle all controller exceptions
 * 
 *
 */
@ControllerAdvice
public class RestGlobalExceptionHandler {

	// adding logging support
	private static Logger logger = LoggerFactory.getLogger(RestGlobalExceptionHandler.class);

	/**
	 * Handle Custom Application Exception
	 * 
	 * @param ex
	 *            custom application Exception
	 * @return Custom Response Error
	 * 
	 */
	@ExceptionHandler
	public ResponseEntity<ResponseError> handleException(PhoneBookApplicationException ex) {
		logger.error("RestGlobalExceptionHandler - handleException -    " + ex);
		ResponseError resError = new ResponseError(ex.getCode(), ex.getMessage(), System.currentTimeMillis());
		return new ResponseEntity<>(resError, HttpStatus.BAD_REQUEST);
	}

	/**
	 * Handle General Exceptions for Controllers
	 * 
	 * @param ex
	 *            custom application Exception
	 * @return Custom Response Error
	 */
	@ExceptionHandler
	public ResponseEntity<ResponseError> handleException(JpaObjectRetrievalFailureException ex) {
		logger.error(
				"RestGlobalExceptionHandler - handleException - JpaObjectRetrievalFailureException -This Object Not Found    "
						+ ex);
		ResponseError resError = new ResponseError(StatusCode.NOTFOUND, "This Object Not Found",
				System.currentTimeMillis());
		return new ResponseEntity<>(resError, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler
	public ResponseEntity<ResponseError> handleException(SQLIntegrityConstraintViolationException ex) {
		logger.error(
				"RestGlobalExceptionHandler - handleException -SQLIntegrityConstraintViolationException -This Object Already Exist. Or Invalid Data   "
						+ ex);
		ResponseError resError = new ResponseError(StatusCode.UNIQUE, "This Object Already Exist. Or Invalid Data",
				System.currentTimeMillis());

		return new ResponseEntity<>(resError, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler
	public ResponseEntity<ResponseError> handleException(DataIntegrityViolationException ex) {

		ResponseError resError = null;
		logger.error(
				"RestGlobalExceptionHandler -- handleException- DataIntegrityViolationException - This Object Already Exist. Or Invalid Data   "
						+ ex);
		if (ex.getLocalizedMessage().toString().toLowerCase().contains("uk")) {

			resError = new ResponseError(StatusCode.UNIQUE, "This Object Already Exist. Or Invalid Data ",
					System.currentTimeMillis());
		}

		if (ex.getLocalizedMessage().toString().toLowerCase().contains("fk")) {

			resError = new ResponseError(StatusCode.BADREQUEST,
					"This Object Integrated With Other Objects or Check Unique Values", System.currentTimeMillis());
		} else {
			resError = new ResponseError(StatusCode.BADREQUEST,
					"This Object Integrated With Other Objects or Check Unique Values", System.currentTimeMillis());
		}

		return new ResponseEntity<>(resError, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler
	public ResponseEntity<ResponseError> handleException(MethodArgumentNotValidException ex) {
		logger.error("RestGlobalExceptionHandler -- handleException- MethodArgumentNotValidException   " + ex);
		// Get all errors default messages
		List<String> errorList = ex.getBindingResult().getFieldErrors().stream().map(x ->((FieldError) x).getField()+" -> "+x.getDefaultMessage())
				.collect(Collectors.toList());

		// Call static method to concatenate error string values
		String errorMessage = join(errorList, " , ");
		
		ResponseError resError = new ResponseError(StatusCode.REQUIRED, errorMessage, System.currentTimeMillis());

		return new ResponseEntity<>(resError, HttpStatus.BAD_REQUEST);
	}

	public static String join(Collection<?> collection, String delimiter) {
		String concatList = (String) collection.stream().map(Object::toString).collect(Collectors.joining(delimiter));

		return concatList;

	}

	@ExceptionHandler
	public ResponseEntity<ResponseError> handleException(EntityNotFoundException ex) {
		logger.error(
				"RestGlobalExceptionHandler -- handleException- EntityNotFoundException -This Object Not Found  " + ex);
		ResponseError resError = new ResponseError(StatusCode.NOTFOUND, "This Object Not Found",
				System.currentTimeMillis());
		return new ResponseEntity<>(resError, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler
	public ResponseEntity<ResponseError> handleException(Exception ex) {
		ex.printStackTrace();
		ResponseError resError = new ResponseError(StatusCode.BADREQUEST, "Bad Request", System.currentTimeMillis());
		return new ResponseEntity<>(resError, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler
	public ResponseEntity<ResponseError> handleException(EmptyResultDataAccessException ex) {
		logger.error(
				"RestGlobalExceptionHandler -- handleException- EmptyResultDataAccessException -This Object Can't be Deleted becouse it related with other objects or not founded  "
						+ ex);
		ResponseError resError = new ResponseError(StatusCode.NOTFOUND,
				"This Object Can't be Deleted becouse it related with other objects or not founded",
				System.currentTimeMillis());
		return new ResponseEntity<>(resError, HttpStatus.BAD_REQUEST);
	}

}

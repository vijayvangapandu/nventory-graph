/*
 * This software is the confidential and proprietary information of
 * eharmony.com and may not be used, reproduced, modified, distributed,
 * publicly displayed or otherwise disclosed without the express written
 * consent of eharmony.com.
 *
 * This software is a work of authorship by eharmony.com and protected by
 * the copyright laws of the United States and foreign jurisdictions.
 *
 * Copyright 2000-2017 eharmony.com, Inc. All rights reserved.
 *
 */
package ops.inventory.exception;

/**
 * Exception class for any exception that occur while processing.
 *
 */
public class ProcessingException extends RuntimeException {
	
  private static final long serialVersionUID = 9102231833233901088L;

	public ProcessingException(String message, Throwable cause) {
        super(message, cause);
    }
}

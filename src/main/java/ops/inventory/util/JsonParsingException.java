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


package ops.inventory.util;

/**
 * Exception thrown when we cannot serialize / deserialize using object mapper.
 * Did not want checked exceptions and hence the need.
 * @author ssharma
 *
 */
public class JsonParsingException extends RuntimeException {
    /**
     * 
     */
    private static final long serialVersionUID = -8269414488833257781L;

    public JsonParsingException(String message, Throwable cause) {
        super(message, cause);
    }

}

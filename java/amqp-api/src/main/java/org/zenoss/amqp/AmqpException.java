/*****************************************************************************
 * 
 * Copyright (C) Zenoss, Inc. 2010-2011, all rights reserved.
 * 
 * This content is made available according to terms specified in
 * License.zenoss under the directory where your Zenoss product is installed.
 * 
 ****************************************************************************/


package org.zenoss.amqp;

/**
 * Exception thrown by AMQP operations.
 */
public class AmqpException extends Exception {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    /**
     * Creates an AMQP exception with no message or cause.
     */
    public AmqpException() {
        super();
    }

    /**
     * Creates an AMQP exception with the specified message and cause.
     * 
     * @param message
     *            Exception message.
     * @param cause
     *            Exception cause.
     */
    public AmqpException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Creates an AMQP exception with the specified message.
     * 
     * @param message
     *            Exception message.
     */
    public AmqpException(String message) {
        super(message);
    }

    /**
     * Creates an AMQP exception with the specified cause.
     * 
     * @param cause
     *            Exception cause.
     */
    public AmqpException(Throwable cause) {
        super(cause);
    }

}

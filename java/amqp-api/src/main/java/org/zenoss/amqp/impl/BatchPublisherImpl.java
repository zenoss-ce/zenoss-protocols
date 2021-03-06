/*****************************************************************************
 * 
 * Copyright (C) Zenoss, Inc. 2010-2011, all rights reserved.
 * 
 * This content is made available according to terms specified in
 * License.zenoss under the directory where your Zenoss product is installed.
 * 
 ****************************************************************************/


package org.zenoss.amqp.impl;

import java.io.IOException;

import org.zenoss.amqp.AmqpException;
import org.zenoss.amqp.BatchPublisher;
import org.zenoss.amqp.Exchange;
import org.zenoss.amqp.MessageConverter;
import org.zenoss.amqp.MessagePropertiesBuilder;

public class BatchPublisherImpl<T> extends PublisherImpl<T> implements
        BatchPublisher<T> {
    BatchPublisherImpl(ChannelImpl channel, Exchange exchange) {
        super(channel, exchange, null);
    }

    BatchPublisherImpl(ChannelImpl channel, Exchange exchange,
            MessageConverter<T> converter) {
        super(channel, exchange, converter);
    }

    @Override
    public void publish(T body, MessagePropertiesBuilder propertiesBuilder,
            String routingKey) throws AmqpException {
        if (!this.channel.isTransactionsEnabled()) {
            this.channel.enableTransactions();
        }
        super.publish(body, propertiesBuilder, routingKey);
    }

    @Override
    public void close() throws IOException {
        this.channel.close();
    }

    @Override
    public void commit() throws AmqpException {
        this.channel.commitTransaction();
    }

    @Override
    public void rollback() throws AmqpException {
        this.channel.rollbackTransaction();
    }
}

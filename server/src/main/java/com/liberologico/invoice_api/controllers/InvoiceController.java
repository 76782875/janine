package com.liberologico.invoice_api.controllers;

import com.liberologico.invoice_api.entities.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import javax.validation.Valid;

@RestController
public class InvoiceController
{
    private final Jedis jedis;

    @Autowired
    public InvoiceController( JedisConnectionFactory connectionFactory )
    {
        this.jedis = new Jedis( connectionFactory.getShardInfo() );
    }

    @RequestMapping( value = "/{prefix}/validate", method = RequestMethod.POST )
    Invoice validate( @PathVariable String prefix, @RequestBody @Valid Invoice invoice )
    {
        Long id = Long.parseLong( jedis.get( prefix ) );

        return invoice.setNumber( prefix + String.valueOf( id + 1 ) );
    }

    @RequestMapping( value = "/{prefix}", method = RequestMethod.POST )
    Invoice generate( @PathVariable String prefix, @RequestBody @Valid Invoice invoice )
    {
        Long id = jedis.incr( prefix );

        return invoice.setNumber( prefix + id.toString() );
    }
}

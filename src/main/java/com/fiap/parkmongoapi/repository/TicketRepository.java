package com.fiap.parkmongoapi.repository;

import com.fiap.parkmongoapi.model.Ticket;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends MongoRepository<Ticket,String> {





}

package com.example.quinBooksolrSearch.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@SolrDocument(collection = "user")
public class QuinBookUser {

    @Id
    @org.springframework.data.annotation.Id
    @GenericGenerator(name = "user_id_seq", strategy = "increment")
    @GeneratedValue(generator = "user_id_seq", strategy = GenerationType.AUTO)

    @Indexed(name = "userId", type = "long")
    private Long userId;
    @Indexed(name = "userName", type = "string")
    private String userName;
    @Indexed(name = "email", type = "string")
    private String email;
    @Indexed(name = "location", type = "string")
    private String location;
}

package com.example.quinBooksolrSearch.entity;

import lombok.Data;
import org.apache.solr.client.solrj.beans.Field;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

import javax.persistence.*;

@Entity
@Data
@SolrDocument(solrCoreName = "quinbook")
public class QuinBookUser {

    @Id
    @org.springframework.data.annotation.Id

    @Indexed(name = "userId", type = "long")
    private long userId;

    @Indexed(name = "userName", type = "string")
    private String userName;

    @Indexed(name = "firstName", type = "string")
    private String firstName;

    @Indexed(name = "lastName", type = "string")
    private String lastName;

    @Column(columnDefinition = "TEXT")
    private String img;
    private String relationshipStatus;
    private String education10;
    private String education12;
    private String educationUni;

    @Indexed(name = "jobProfile", type = "string")
    private String jobProfile;

    @Indexed(name = "companyName", type = "string")
    private String companyName;

    private String Hobbies;
    private java.sql.Date marriageAnniversary;
    private java.sql.Date quinbookJoinDate;
    private java.sql.Date jobStartDate;
    private java.sql.Date jobEndDate;
    @Column(nullable = true)
    private Long yearsOfExp;

    @Indexed(name = "jobLocation", type = "string")
    private String jobLocation;
    private String address;

}

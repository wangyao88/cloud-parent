package com.sxkl.project.cloudgateway.user.entity;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "user")
@Data
public class User {

    @Id
    private String id;
    private String name;
    private String password;
}

//package org.social.social_network.entity;
//
//import lombok.Data;
//import org.springframework.data.annotation.Id;
//import org.springframework.data.relational.core.mapping.MappedCollection;
//import org.springframework.data.relational.core.mapping.Table;
//
//import java.util.Set;
//import java.util.UUID;
//
//@Data
//@Table(name = "users")
//public class User {
//
//    @Id
//    private UUID id;
//
//    private String name;
//
//    @MappedCollection(idColumn = "author_id")
//    private Post posts;
//
//    @MappedCollection(idColumn = "author_id")
//    private Set<Comment> comments;
//}
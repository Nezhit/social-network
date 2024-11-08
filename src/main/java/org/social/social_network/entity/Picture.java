package org.social.social_network.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "pictures")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Picture {
    @Id
    @GeneratedValue
    private UUID id;

    private byte[] content;

    private Instant created;


}

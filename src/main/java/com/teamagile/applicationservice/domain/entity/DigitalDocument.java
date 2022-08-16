package com.teamagile.applicationservice.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "DigitalDocument")
public class DigitalDocument implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "type")
    private String type;

    @Column(name = "is_required")
    private boolean is_required;

    @Column(name = "path")
    private String path;

    @Column(name = "description")
    private String description;

    @Column(name = "title")
    private String title;

}

package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Project")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long projectId;

    @Column(name = "area")
    private String area;

    @Column(name = "project_name")
    private String projectName;

    @ManyToOne
    @JoinColumn(name = "managedDepartment")
    private Department department;

    @OneToMany(mappedBy = "project")
    @JsonIgnore
    private Collection<Assignment> assignments;
}

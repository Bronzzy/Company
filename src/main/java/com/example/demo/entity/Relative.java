package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Relative")
public class Relative {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long relativeId;

    @Column(name = "full_name")
    private String fullName;

    @Column(name ="gender")
    @Enumerated(EnumType.ORDINAL)
    private Gender gender;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "relationship")
    private String relationship;

    @ManyToOne
    @JoinColumn(name = "employeeId")
    private Employee employee;

    private int dao;
    private int hoa;
    private int binh;
    private int loserpool;
    private int steven;
    private int gerrard;
    private int truotChanMatCup;
    private int karius;
    private int sadioMane;
}

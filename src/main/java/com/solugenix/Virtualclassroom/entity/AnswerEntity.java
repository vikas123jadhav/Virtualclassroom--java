package com.solugenix.Virtualclassroom.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name="ANSWERS")
@NoArgsConstructor
@AllArgsConstructor
public class AnswerEntity {

    @Id
    @GeneratedValue( strategy= GenerationType.AUTO )
    private Long  ansId;

    private String  answer;

    private Long  givenId;
}

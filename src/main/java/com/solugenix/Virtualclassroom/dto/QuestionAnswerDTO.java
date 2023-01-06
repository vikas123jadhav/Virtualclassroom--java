package com.solugenix.Virtualclassroom.dto;

import com.solugenix.Virtualclassroom.entity.QuestionEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class QuestionAnswerDTO {

    private QuestionEntity questionEntity;
}

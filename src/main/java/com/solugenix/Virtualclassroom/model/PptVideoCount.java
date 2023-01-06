package com.solugenix.Virtualclassroom.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PptVideoCount {
     private Integer month;
     private  Long   toatal;

     public Integer getMonth() {
          return month;
     }

     public void setMonth(Integer month) {
          this.month = month;
     }

     public Long getToatal() {
          return toatal;
     }

     public void setToatal(Long toatal) {
          this.toatal = toatal;
     }
}

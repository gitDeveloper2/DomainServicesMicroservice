package com.example.processor;

import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@RequiredArgsConstructor

@ToString
@EqualsAndHashCode
public class Domain implements Serializable {
    String domain;
    String create_date;
    String update_date;
    String country;
    Boolean isDead;
    String A;
    String NS;
    String CNAME;
    String MX;
    String TXT;
}

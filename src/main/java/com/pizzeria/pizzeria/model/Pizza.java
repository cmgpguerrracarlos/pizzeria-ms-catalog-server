package com.pizzeria.pizzeria.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "catalog_table")
//@JsonPropertyOrder("uid","price","style","size");
public class Pizza {
    @Id
    private String uid;
    private Float price;
    private String style;
    private String size;
    private String topping;



}

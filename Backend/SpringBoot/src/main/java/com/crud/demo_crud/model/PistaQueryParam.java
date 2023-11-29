package com.crud.demo_crud.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PistaQueryParam {
    private Integer capacity;
    private String[] sports;
    private Integer order;
    private String name_mesa;
    private Integer page;
    private Integer limit;
}
package com.fdzc.springboot01.entity.dto;

import lombok.Data;

import java.util.List;

@Data
public class PageDTO<T> {
    List<T> records;
    Integer total;
}

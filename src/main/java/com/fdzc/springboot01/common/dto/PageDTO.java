package com.fdzc.springboot01.common.dto;

import lombok.Data;

import java.util.List;

@Data
public class PageDTO<T> {
    List<T> records;
    Integer total;
}

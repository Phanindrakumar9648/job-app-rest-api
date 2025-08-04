package com.build.jobapp.restapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Job {
    private Integer id;
    private String title;
    private String description;
}
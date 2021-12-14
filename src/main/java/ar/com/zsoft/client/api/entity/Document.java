package ar.com.zsoft.client.api.entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Document {
    private String name;
    private Integer pages;
    private Integer year;
}

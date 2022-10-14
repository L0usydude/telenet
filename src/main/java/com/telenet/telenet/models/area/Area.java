package com.telenet.telenet.models.area;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.telenet.telenet.models.area.impl.AreaImpl;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, property = "type")
@JsonSubTypes(@JsonSubTypes.Type(value = AreaImpl.class, name = "AreaImpl"))
public interface Area {
    void setId(int newId);
    void setName(String newName);
    void setDescription(String newDescription);
    int getId();
    String getName();
    String getDescription();
}

package com.telenet.telenet.models.area;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.telenet.telenet.models.area.impl.AreaImpl;

@JsonSubTypes(@JsonSubTypes.Type(value = AreaImpl.class, name = "AreaImplJson"))
public interface Area {
    void setId(int newId);
    void setName(String newName);
    void setDescription(String newDescription);
    int getId();
    String getName();
    String getDescription();
}

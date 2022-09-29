package com.telenet.telenet.models.template;

import com.telenet.telenet.models.area.Area;

public interface Template {
    int getId();
    String getName();
    String getDescription();
    double getPrice();
    Area getArea();
    void setId(int newId);
    void setName(String newName);
    void setDescription(String newDescription);
    void setPrice(double newPrice);
    void setArea(Area newArea);
}

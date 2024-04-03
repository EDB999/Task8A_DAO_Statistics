package com.example.task8a_dao_statistics.Components;

import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor

public class Task {
    public int id;
    public String place;
    public String name;
    public String surname;
    public String patronymic;
    public String role;
    public String time;

    @Override
    public String toString(){
        return id + " " + place + " " + name + " " + surname + " " + patronymic + " " + role + " " + time + " ";
    }
}


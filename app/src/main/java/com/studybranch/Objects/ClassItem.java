package com.studybranch.Objects;

import android.app.LauncherActivity;

import com.orm.SugarRecord;
import com.studybranch.AssignmentCreator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ArnoldB on 1/5/2015.
 */
public class ClassItem extends SugarRecord<ClassItem> {

    String name;
    String teacher;
    String location;
    boolean isExpandable = false;

    public ClassItem(){

    }

    public ClassItem(String name, String teacher, String location){
        this.name = name;
        this.teacher = teacher;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setExpandable(boolean isExpandable) {
        this.isExpandable = isExpandable;
    }

    public boolean isExpandable(){
        return this.isExpandable;
    }
}

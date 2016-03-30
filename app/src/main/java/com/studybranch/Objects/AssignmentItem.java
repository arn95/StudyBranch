package com.studybranch.Objects;

import com.orm.SugarRecord;

/**
 * Created by ArnoldB on 12/30/2014.
 */
public class AssignmentItem extends SugarRecord<AssignmentItem> {

    String assignmentName;
    String assignmentDescription;
    String assignmentClass;

    public AssignmentItem(){

    }


    public AssignmentItem(String name,String description){

        assignmentName = name;
        assignmentDescription = description;
    }

    public String getAssignmentClass() {
        return assignmentClass;
    }

    public void setAssignmentClass(String assignmentClass) {
        this.assignmentClass = assignmentClass;
    }

    public void setAssignmentDescription(String assignmentDescription) {
        this.assignmentDescription = assignmentDescription;
    }
    public String getAssignmentDescription() {
        return assignmentDescription;
    }
    public void setAssignmentName(String assignmentName) {
        this.assignmentName = assignmentName;
    }
    public String getAssignmentName() {
        return assignmentName;
    }

}

package com.studybranch.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.TextView;

import com.studybranch.AssignmentCreator;
import com.studybranch.Objects.AssignmentItem;
import com.studybranch.Objects.ClassItem;
import com.studybranch.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ArnoldB on 12/30/2014.
 */
public class AssignmentsExpandableListAdapter extends BaseExpandableListAdapter {
    List<ClassItem> classes;
    Context mContext;
    LayoutInflater inflater;

    public AssignmentsExpandableListAdapter(Context context, List<ClassItem> classes) {
        this.classes = classes;
        mContext = context;
    }

    @Override
    public int getGroupCount() {
        return classes.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        if (classes.get(groupPosition).isExpandable()) {
            String className = classes.get(groupPosition).getName();
            List<AssignmentItem> childList = AssignmentItem.find(AssignmentItem.class, "className = ?", className);
            return childList.size();
        }
        else
            return 0;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return classes.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        String className =  classes.get(groupPosition).getName();
        List<AssignmentItem> childList = AssignmentItem.find(AssignmentItem.class, "className = ?", className);
        return childList.get(childPosition);
    }
    @Override//dont use, inaccurate
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override//dont use, inaccurate
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    @Override
    public View getGroupView(final int groupPosition, final boolean isExpanded, View convertView, final ViewGroup parent) {
        inflater= (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.assignments_group_list,null);
        //setting the name of the group
        TextView groupName = (TextView) convertView.findViewById(R.id.groupName);
        groupName.setText(classes.get(groupPosition).getName());
        //setting the expand icon if expandable
        if (classes.get(groupPosition).isExpandable()){
            ImageButton btnExpand = (ImageButton) convertView.findViewById(R.id.btnExpand);
            btnExpand.setVisibility(View.VISIBLE);
            btnExpand.setBackgroundResource(R.drawable.ic_action_expand);
            btnExpand.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(isExpanded) ((ExpandableListView) v).collapseGroup(groupPosition);
                    else ((ExpandableListView) v).expandGroup(groupPosition, true);
                }
            });
        }
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.assigments_item_list,null);
        TextView childNameView = (TextView) convertView.findViewById(R.id.listItemText);
        String className =  classes.get(groupPosition).getName();
        List<AssignmentItem> childList = AssignmentItem.find(AssignmentItem.class, "className = ?", className);
        String childName = childList.get(childPosition).getAssignmentName();
        childNameView.setText(childName);

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}

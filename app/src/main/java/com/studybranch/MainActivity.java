package com.studybranch;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.ContentObservable;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.studybranch.Adapters.AssignmentsExpandableListAdapter;
import com.studybranch.MainFragments.AssignmentsFragment;
import com.studybranch.MainFragments.NotesFragment;
import com.studybranch.MainFragments.ScheduleFragment;
import com.studybranch.NestedFragments.AllListFragment;
import com.studybranch.NestedFragments.ClassExpandableListFragment;
import com.studybranch.NestedFragments.DateExpandableListFragment;
import com.studybranch.NestedFragments.PriorityExpandableListFragment;
import com.studybranch.Objects.ClassItem;
import com.studybranch.ResideMenu.ResideMenu;
import com.studybranch.ResideMenu.ResideMenuItem;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity implements NotesFragment.OnFragmentInteractionListener,
        ScheduleFragment.OnFragmentInteractionListener,
        AssignmentsFragment.OnFragmentInteractionListener,
        BlankFragment.OnFragmentInteractionListener,
        ClassExpandableListFragment.OnFragmentInteractionListener,
        AllListFragment.OnFragmentInteractionListener,
        DateExpandableListFragment.OnFragmentInteractionListener,
        PriorityExpandableListFragment.OnFragmentInteractionListener

{

    static ResideMenu resideMenu;
    String whichFragment = null;
    Bundle SIS;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resideMenu = new ResideMenu(this);
        resideMenu.setBackground(R.drawable.img_background_material);
        resideMenu.attachToActivity(this);
        //disables the right resideMenu
        resideMenu.setSwipeDirectionDisable(ResideMenu.DIRECTION_RIGHT);
        //creates the resideMenu items
        final ResideMenuItem itemSchedule = new ResideMenuItem(this, R.drawable.ic_calendar, "Schedule");
        final ResideMenuItem itemAssignments = new ResideMenuItem(this, R.drawable.ic_file, "Assignments");
        final ResideMenuItem itemNotes = new ResideMenuItem(this, R.drawable.ic_notes, "Notes");
        final ArrayList<ResideMenuItem> menuItemsList = new ArrayList<>();
        //setting listeners for the resideMenu items
        itemNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (savedInstanceState == null) {
                    changeFragment(new NotesFragment());
                    whichFragment = "notes";
                    resideMenu.closeMenu();
                }
            }
        });
        itemSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (savedInstanceState == null) {
                    changeFragment(new ScheduleFragment());
                    whichFragment = "schedule";
                    resideMenu.closeMenu();
                }

            }
        });
        itemAssignments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (savedInstanceState == null) {
                    changeFragment(new AssignmentsFragment());
                    whichFragment = "assignments";
                    resideMenu.closeMenu();
                }
            }
        });
        //create an arraylist of items to pass into the resideMenu
        menuItemsList.add(itemSchedule);
        menuItemsList.add(itemAssignments);
        menuItemsList.add(itemNotes);
        //adds the resideMenu items on the resideMenu on the left
        resideMenu.setMenuItems(menuItemsList, ResideMenu.DIRECTION_LEFT);

        //To change the color of the status bar when the resideMenu opens and closes
        resideMenu.setMenuListener(new ResideMenu.OnMenuListener() {
            @Override
            public void openMenu() {
                if (Build.VERSION.SDK_INT == 21) {
                    getWindow().setStatusBarColor(getResources().getColor(R.color.PrimaryBlue));
                }
            }

            @Override
            public void closeMenu() {
                if (Build.VERSION.SDK_INT == 21) {
                    getWindow().setStatusBarColor(getResources().getColor(R.color.PrimaryBlueDark));
                }
            }
        });

        //first run, changes from blank frag to notes frag
        changeFragment(new NotesFragment());
        whichFragment = "notes";

    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return resideMenu.dispatchTouchEvent(ev);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        if (whichFragment.equals("schedule")) {
            menu.findItem(R.id.action_new_item).setVisible(false);
            menu.findItem(R.id.action_new_class).setVisible(false);
        }
        if (whichFragment.equals("notes")) {
            menu.findItem(R.id.action_new_class).setVisible(false);
        }
        if (whichFragment.equals("assignments")) {

        }
        return true;
    }

        @Override
        public boolean onOptionsItemSelected (MenuItem item){
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            final int id = item.getItemId();

            if (whichFragment.equals("assignments")){
                if (id == R.id.action_new_item){
                    startActivity(new Intent(this, AssignmentCreator.class));
                }
                if (id == R.id.action_new_class){
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    final View v = getLayoutInflater().inflate(R.layout.activity_class_creator,null);
                    builder.setView(v);
                    builder.setTitle("Create a class");
                    builder.setPositiveButton("Create", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            TextView className = (TextView) v.findViewById(R.id.cName);
                            TextView teacherName = (TextView) v.findViewById(R.id.cTeacher);
                            TextView classLocation = (TextView) v.findViewById(R.id.cLocation);
                            ClassItem createdClass = new ClassItem(className.getText().toString(),teacherName.getText().toString(),classLocation.getText().toString());
                            createdClass.save();
                            changeFragment(new AssignmentsFragment());
                        }
                    });
                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();

                        }
                    });
                    builder.create();
                    builder.show();
                }
            }
            return super.onOptionsItemSelected(item);
        }

    private void changeFragment(Fragment targetFragment) {
        resideMenu.clearIgnoredViewList();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment, targetFragment, "fragment")
                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public ResideMenu getResideMenu() {
        return resideMenu;
    }


}


package com.onehundredtwo.signaly.adapters;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.onehundredtwo.signaly.HangulWritingActivity;
import com.onehundredtwo.signaly.HanjaWritingActivity;
import com.onehundredtwo.signaly.R;


import java.util.LinkedHashMap;
import java.util.List;

public class CharactersExpandableListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> expandableListGroupTitle;
    private LinkedHashMap<String, List<String>> expandableListDetails;
    private String extraStringType;

    public CharactersExpandableListAdapter(Context context, List<String> expandableListGroupTitle, LinkedHashMap<String, List<String>> expandableListDetails, String extraStringType) {
        this.context = context;
        this.expandableListGroupTitle = expandableListGroupTitle;
        this.expandableListDetails = expandableListDetails;
        this.extraStringType = extraStringType;
    }

    @Override
    public int getGroupCount() {
        return this.expandableListGroupTitle.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.expandableListDetails.get(this.expandableListGroupTitle.get(groupPosition))
                .size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.expandableListGroupTitle.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this.expandableListDetails.get(this.expandableListGroupTitle.get(groupPosition))
                .get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String listTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_group, null);
        }
        TextView listTitleTextView = convertView.findViewById(R.id.listTitle);
        //listTitleTextView.setTypeface(null, Typeface.BOLD);
        listTitleTextView.setText(listTitle);

        ImageView arrowImageView = convertView.findViewById(R.id.groupArrowImage);
        if (isExpanded) {
            arrowImageView.setImageResource(R.drawable.ic_arrow_up);
        } else {
            arrowImageView.setImageResource(R.drawable.ic_arrow_down);
        }

        ImageView testButton = convertView.findViewById(R.id.groupPenButton);
        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity activity = (Activity) context;
                if(extraStringType.equals("hanja")) {
                    Intent intent = new Intent(context, HanjaWritingActivity.class);
                    intent.putExtra("groupNumber", groupPosition);
                    intent.putExtra("testMode", true);
                    context.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(activity).toBundle());
                }
                else {
                    Intent intent = new Intent(context, HangulWritingActivity.class);
                    intent.putExtra("groupNumber", groupPosition);
                    intent.putExtra("letterType", extraStringType);
                    intent.putExtra("testMode", true);
                    context.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(activity).toBundle());
                }

            }
        });

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        String expandedListText = (String) getChild(groupPosition, childPosition);
        String[] expandedListTextArray = expandedListText.split(";");


        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_item_hangul_hanja, null);
        }

        TextView expandedListTextView1 = convertView.findViewById(R.id.expandedListItemKoreanLetter);
        expandedListTextView1.setText(expandedListTextArray[0]);

        TextView expandedListTextView2 = convertView.findViewById(R.id.expandedListItemWords);
        if(expandedListTextArray.length == 3) {
            expandedListTextView2.setText(expandedListTextArray[1]);
        }
        else if(expandedListTextArray.length == 2) {
            expandedListTextView2.setText(expandedListTextArray[1]);
        }



        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }


}

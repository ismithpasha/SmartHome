package com.smarthomesystem.ju.smarthomesystemapplication.adaptermodels;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.smarthomesystem.ju.smarthomesystemapplication.R;

import java.util.ArrayList;

public class MemberInfoAdapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;

    ArrayList<MemberInfo> memberInfoArrayList;

    int transitionCount = 1;

    public MemberInfoAdapter(Activity activity, ArrayList<MemberInfo> memberInfoArrayList) {
        this.activity = activity;
        this.memberInfoArrayList = memberInfoArrayList;
    }

    public static class MemberInfoHolder {

        public TextView MemberId;
        public TextView MemberName;
        public TextView MemberEmail;
        public TextView MemberMobileNo;
    }

    @Override
    public int getCount() {
        return memberInfoArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return memberInfoArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        MemberInfoHolder holder;
        if (inflater == null)
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            vi = inflater.inflate(R.layout.member_info_list, null);
            holder = new MemberInfoHolder();

            //  holder.SureTipsId = (TextView) vi.findViewById(R.id.SureTipsId);
            holder.MemberId = (TextView) vi.findViewById(R.id.memberId);

            holder.MemberName = (TextView) vi.findViewById(R.id.memberName);
            holder.MemberEmail = (TextView) vi.findViewById(R.id.memberEmail);
            holder.MemberMobileNo = (TextView) vi.findViewById(R.id.memberMobileNo);


            holder.MemberId.setText(memberInfoArrayList.get(position).getMemberId());
            holder.MemberName.setText(memberInfoArrayList.get(position).getMemberName());
            holder.MemberEmail.setText(memberInfoArrayList.get(position).getEmail());
            holder.MemberMobileNo.setText(memberInfoArrayList.get(position).getMemberMobileNo());

        }
        return vi;

    }
}

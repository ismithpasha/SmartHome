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

public class ContactInfoAdapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;

    ArrayList<ContactInfo> contactInfoArrayList;

    int transitionCount = 1;

    public ContactInfoAdapter(Activity activity, ArrayList<ContactInfo> contactInfoArrayList) {
        this.activity = activity;
        this.contactInfoArrayList = contactInfoArrayList;
    }

    public static class ContactInfoHolder {

        //  public TextView SureTipsId;
        public TextView ContactId;
        public TextView ContactName;
        public TextView ContactMobile;
        public TextView ContactEmail;
        public TextView ContactAddress;
    }

    @Override
    public int getCount() {
        return contactInfoArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return contactInfoArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        ContactInfoHolder holder;
        if (inflater == null)
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            vi = inflater.inflate(R.layout.contact_info_list, null);
            holder = new ContactInfoHolder();

            //  holder.SureTipsId = (TextView) vi.findViewById(R.id.SureTipsId);
            holder.ContactId = (TextView) vi.findViewById(R.id.contactId);

            holder.ContactName = (TextView) vi.findViewById(R.id.contactName);
            holder.ContactEmail = (TextView) vi.findViewById(R.id.contactEmail);
            holder.ContactMobile = (TextView) vi.findViewById(R.id.contactMobile);
            holder.ContactAddress = (TextView) vi.findViewById(R.id.contactAddress);


            holder.ContactId.setText(contactInfoArrayList.get(position).getContactId());
            holder.ContactName.setText(contactInfoArrayList.get(position).getContactName());
            holder.ContactEmail.setText(contactInfoArrayList.get(position).getEmail());
            holder.ContactMobile.setText(contactInfoArrayList.get(position).getMobileNo());
            holder.ContactAddress.setText(contactInfoArrayList.get(position).getAddress());

        }
        return vi;

    }
}

/*
 * Copyright 2020 FAIZAL.DEV. All rights reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.eso.covid19.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Filter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.eso.covid19.R;
import com.eso.covid19.model.ListItem;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

     private List<ListItem> listItems;
     private List<ListItem> listAll;
     private Context context;

    public MyAdapter(List<ListItem> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    //    listAll = new ArrayList<>(listItems);

    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_country, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ListItem listItem = listItems.get(position);
        holder.country.setText(listItem.getCountry());
        holder.casesItemstr.setText(Integer.toString(listItem.getCases()));
        holder.tcasesItemstr.setText(Integer.toString(listItem.getTodayCases()));
        holder.deathItemstr.setText(Integer.toString(listItem.getDeath()));
        holder.tdeathItemstr.setText(Integer.toString(listItem.getTodayDeath()));
        holder.recoverItemstr.setText(Integer.toString(listItem.getRecover()));
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public Filter getMyfilter() {
        return myfilter;
    }

   private Filter myfilter = new Filter() {
       @Override
       protected FilterResults performFiltering(CharSequence constraint) {
           List<ListItem> filteredList = new ArrayList<>();

            if(constraint == null || constraint.length() == 0){
              filteredList.addAll(listAll);

           }else{
              String filterPattern = constraint.toString().toLowerCase().trim();
               for(ListItem data: listAll){
                  if(data.getCountry().toLowerCase().contains(filterPattern)){
                      filteredList.add(data);
                    }
               }
         }

           FilterResults filterResults = new FilterResults();
           filterResults.values = filteredList;
           return filterResults;
       }

       @Override
       protected void publishResults(CharSequence constraint, FilterResults results) {

           listItems.clear();
          listItems.addAll((List)results.values);
           notifyDataSetChanged();
       }
    };

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView casesItemstr, tcasesItemstr, deathItemstr, tdeathItemstr, recoverItemstr, country;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            casesItemstr = itemView.findViewById(R.id.casesItem);
            tcasesItemstr = itemView.findViewById(R.id.tcasesItem);
            deathItemstr = itemView.findViewById(R.id.deathItem);
            tdeathItemstr = itemView.findViewById(R.id.tdeathItem);
            recoverItemstr = itemView.findViewById(R.id.recoverItem);
            country = itemView.findViewById(R.id.country_name);
        }
    }

    public void updateList(List<ListItem> newList){
        listItems = new ArrayList<>();
        listItems.addAll(newList);
        notifyDataSetChanged();
    }
}

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

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.eso.covid19.ui.fragment.Corona_Overview;
import com.eso.covid19.ui.fragment.Prevention;

public class PagerAdapter extends FragmentStatePagerAdapter {

    int tabs;

    public PagerAdapter(@NonNull FragmentManager fm, int tabs) {
        super(fm);
        this.tabs = tabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        if(position==0){
            Corona_Overview corona_overview = new Corona_Overview();
            return corona_overview;
        }else if(position ==1){
            Prevention prevention = new Prevention();
            return prevention;
        }else{
            return null;

        }
    }

    @Override
    public int getCount() {
        return tabs;
    }
}

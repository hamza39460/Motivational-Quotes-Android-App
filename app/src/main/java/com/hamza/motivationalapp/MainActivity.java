package com.hamza.motivationalapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences=getSharedPreferences("Qoutes", Context.MODE_PRIVATE);
        boolean isFt=isFirstTime();
        if (isFt==true)
        {
            saveDate();
        }
        ViewPager vp=(ViewPager)findViewById(R.id.vp);
        vp.setAdapter(new MyPagesAdapter());
    }
    boolean isFirstTime() // return true if first run else false
    {
        boolean ft=sharedPreferences.getBoolean("First Time",true);//gets value of 'first time' if 'first time' doesn't exist gets true
        if(ft==true) //if 'first time dont exist add it in shared pref'
        {
            SharedPreferences.Editor editor=sharedPreferences.edit();
            editor.putBoolean("First Time",false);
            editor.commit();
        }
        return ft;
    }
    void saveDate() //save qoutes
    {
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("1","Our greatest glory is not in never falling, but in rising every time we fall.~Confucius");
        editor.putString("2","All our dreams can come true, if we have the courage to pursue them.~Walt Disney");
        editor.putString("3","It does not matter how slowly you go as long as you do not stop.~Confucius");
        editor.putString("4","Everything you’ve ever wanted is on the other side of fear.~George Addair");
        editor.putString("5","Success is not final, failure is not fatal: it is the courage to continue that counts.~Winston Churchill");
        editor.commit();
    }

    class MyPagesAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            //Return total pages, here one for each data item
            return 5;
        }
        //Create the given page (indicated by position)
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View page = inflater.inflate(R.layout.pager_layout, null);
            String str=sharedPreferences.getString(Integer.toString(position+1),null);
            if (str!=null) {
                String[] s=str.split("~");
                TextView textView=(TextView) page.findViewById(R.id.text);
                TextView textView1=(TextView) page.findViewById(R.id.by);
                textView.setText(s[0]);
                textView1.setText(s[1]);
                ((ViewPager) container).addView(page, 0);
            }
            //Add the page to the front of the queue
            return page;
        }
        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            //See if object from instantiateItem is related to the given view
            //required by API
            return arg0==(View)arg1;
        }
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ((ViewPager) container).removeView((View) object);
            object=null;
        }
    }
}

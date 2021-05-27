package com.kyalo.neighboursdish.AddDish;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kyalo.neighboursdish.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterCategories extends BaseAdapter {
    public List<ObjectDishCategory> list= new ArrayList();
    Context mContext;
    private LayoutInflater mInflater;
    ViewHolder holder;

    public AdapterCategories(Context context) {
        mInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mContext =  context;

    }

    public void add(ObjectDishCategory object) {
        list.add(object);
    }

    public void change(ObjectDishCategory object, int position) {
        list.remove(position);
        list.add(position,object);
    }

    public void remove(int object) {
        list.remove(object);
    }


    public static class ViewHolder
    {
        ImageView IMG;
        TextView NAME;
        RelativeLayout REL;

    }
    @Override
    public int getCount() {
        return this.list.size();
    }


    @Override
    public ObjectDishCategory getItem(int position) {
        return this.list.get(position);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }


    @SuppressLint("InflateParams")
    @Override
    public View getView(final int position,  View convertView, ViewGroup parent) {

        holder = null;
        if(convertView == null)
        {

            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.list_item_categories, null);

            holder.NAME =  convertView.findViewById(R.id.textViewName);
            holder.IMG =  convertView.findViewById(R.id.item_image);
            holder.REL = convertView.findViewById(R.id.rel_back);
            convertView.setTag(holder);

        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }

        ObjectDishCategory data =  getItem(position);

        holder.NAME.setText(data.name);
        holder.IMG.setImageResource(data.image);

//		holder.IMG.setOnClickListener(v -> {
//		Log.d("checkAdapter" , listBooleans[position]+"");
//		if(!listBooleans[position]){
//			holder.REL.setBackground(ContextCompat.getDrawable(mContext, R.drawable.rec_background_with_orange_border));
//
//			listBooleans[position] = true;
//			Log.d("checkAdapter 1" , listBooleans[position]+"");
//
//		}else{
//			holder.REL.setBackgroundResource(R.color.white);
//			listBooleans[position] = false;
//			Log.d("checkAdapter 2" , listBooleans[position]+"");
//
//		}
//	});

        if (!data.state) {
            holder.REL.setBackgroundResource(R.color.white);
        }else {
            holder.REL.setBackgroundResource(R.drawable.rec_background_with_orange_border);
        }



        return convertView;
    }




}

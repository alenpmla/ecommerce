package in.codme.ecommerceapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * Created by Alen on 19-Aug-16.
 */
public class CustomProductAdapter extends BaseAdapter {

    ArrayList<ProductClass>arrayList=new ArrayList<>();
    Context context;
    LayoutInflater layoutInflater;


       public CustomProductAdapter(ArrayList<ProductClass>arrayList, Context context)
       {
           this.arrayList=arrayList;
           this.context=context;
           layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

       }


    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

       View rootview= layoutInflater.inflate(R.layout.singe_product,null);


        return rootview;
    }
}

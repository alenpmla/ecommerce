package in.codme.ecommerceapp;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;

import java.util.ArrayList;

/**
 * Created by Alen on 17-Aug-16.
 */
public class AllProductsUnderCategoryFragment extends android.support.v4.app.Fragment {
    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;
    ArrayList<ProductClass>arrayList=new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootview=inflater.inflate(R.layout.products_category_detail_fragment,null);
        ProductClass productClass=new ProductClass();
        arrayList.add(productClass);
        ProductClass productClass1=new ProductClass();
        arrayList.add(productClass1);
        arrayList.add(productClass1);
        arrayList.add(productClass1);
        arrayList.add(productClass1);
        ListView listView=(ListView)rootview.findViewById(R.id.listview);


      final  ScrollView scrollView=(ScrollView)rootview.findViewById(R.id.container);
        scrollView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                int scrollY = scrollView.getScrollY();
                int scrollX = scrollView.getScrollX();

                Log.d("scrollY",scrollY+"");
                Log.d("scrollX",scrollX+"");

                if(scrollY==0)
                {

                }
                else
                {

                }
            }


        });
        CustomProductAdapter adapter=new CustomProductAdapter(arrayList,getContext());
        listView.setAdapter(adapter);
        setListViewHeightBasedOnChildren(listView);
        return rootview;


    }
    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null)
            return;

        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.UNSPECIFIED);
        int totalHeight = 0;
        View view = null;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            view = listAdapter.getView(i, view, listView);
            if (i == 0)
                view.setLayoutParams(new ViewGroup.LayoutParams(desiredWidth, ViewPager.LayoutParams.WRAP_CONTENT));

            view.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += view.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }

}

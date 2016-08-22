package in.codme.ecommerceapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * Created by Alen on 17-Aug-16.
 */
public class CategoryDetailFragment extends android.support.v4.app.Fragment {
    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

View rootview=inflater.inflate(R.layout.category_detail_fragment,null);

        mPager = (ViewPager)rootview. findViewById(R.id.pager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getFragmentManager());
        mPager.setAdapter(mPagerAdapter);

        ListView listView=(ListView)rootview.findViewById(R.id.listview) ;
        ArrayAdapter<CharSequence> aa = ArrayAdapter.createFromResource(getActivity(), R.array.sub_category_array, android.R.layout.simple_list_item_1);
        listView.setAdapter(aa);
        setListViewHeightBasedOnChildren(listView);


        GridView brands=(GridView)rootview.findViewById(R.id.brands) ;
        ArrayAdapter<CharSequence> aagv = ArrayAdapter.createFromResource(getActivity(), R.array.sub_category_brands, android.R.layout.simple_list_item_1);
        brands.setAdapter(aagv);
        setGridViewHeightBasedOnChildren(brands,3);

        GridView quicklinks=(GridView)rootview.findViewById(R.id.quicklinks) ;
        quicklinks.setAdapter(aagv);
        setGridViewHeightBasedOnChildren(quicklinks,3);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                android.support.v4.app.FragmentManager fragmentManager = getFragmentManager();
                android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                AllProductsUnderCategoryFragment fragment = new AllProductsUnderCategoryFragment();
                fragmentTransaction.addToBackStack("");
                fragmentTransaction.replace(R.id.container, fragment);
                fragmentTransaction.commit();
            }
        });

        return rootview;
    }

    public void setGridViewHeightBasedOnChildren(GridView gridView, int columns) {
        ListAdapter listAdapter = gridView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;
        int items = listAdapter.getCount();
        int rows = 0;

        View listItem = listAdapter.getView(0, null, gridView);
        listItem.measure(0, 0);
        totalHeight = listItem.getMeasuredHeight();

        float x = 1;
        if( items > columns ){
            x = items/columns;
            rows = (int) (x + 1);
            totalHeight *= rows;
        }

        ViewGroup.LayoutParams params = gridView.getLayoutParams();
        params.height = totalHeight;
        gridView.setLayoutParams(params);

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

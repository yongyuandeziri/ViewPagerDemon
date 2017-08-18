package cn.viewpager.ziri.viewpager;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by ward on 2017/8/16.
 */

public class viewpageradapter extends PagerAdapter {
    List<ImageView> mImageArray;
    public viewpageradapter(List<ImageView> mImageArray) {
        this.mImageArray=mImageArray;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    /**
     * 判断View和object是否相等
     * @param view
     * @param object
     * @return
     */
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
        object=null;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(mImageArray.get(position%mImageArray.size()));
        return mImageArray.get(position%mImageArray.size());
    }
}

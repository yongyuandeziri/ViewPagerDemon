package cn.viewpager.ziri.viewpager;

import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private final int[] pictureid={R.drawable.a,R.drawable.b,R.drawable.c,R.drawable.d,R.drawable.e};
    private final String[] picturedes={"哆啦A梦a","哆啦A梦b","哆啦A梦c","哆啦A梦d","哆啦A梦e"};
    private List<ImageView> imagelist=new ArrayList<ImageView>();
    private int lastposition;/*最后一次滑动的位置*/
    ViewPager mviewpager;
    Boolean isrunning=false;/*handler是否监听*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mviewpager= (ViewPager) this.findViewById(R.id.advertisement);
        final TextView mtextview= (TextView) this.findViewById(R.id.advertisementtext);
        final LinearLayout mpointgroup= (LinearLayout) this.findViewById(R.id.pointgroup);

        for(int i=0;i<pictureid.length;i++){
            ImageView mimageview=new ImageView(this);
            mimageview.setBackgroundResource(pictureid[i]);
            imagelist.add(mimageview);

            ImageView point=new ImageView(this);
            point.setBackgroundResource(R.drawable.point_bg);/*图片切换器，负责圆点空白和选中状态的切换*/
            LinearLayout.LayoutParams mlayoutpara=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            mlayoutpara.rightMargin=20;
            point.setLayoutParams(mlayoutpara);
            if(i==0){
                point.setEnabled(true);
            }else{
                point.setEnabled(false);
            }
            mpointgroup.addView(point);
        }

        viewpageradapter mAdapter=new viewpageradapter(imagelist);
        mviewpager.setAdapter(mAdapter);
        /*让viewpager在中间位置，这样左右都可以滚动*/
        mviewpager.setCurrentItem(Integer.MAX_VALUE/2 -((Integer.MAX_VALUE/2)%imagelist.size()));
        mviewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                position=position%pictureid.length;
                mtextview.setText(picturedes[position]);
                mpointgroup.getChildAt(position).setEnabled(true);
                mpointgroup.getChildAt(lastposition).setEnabled(false);
                lastposition=position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mhandler.sendEmptyMessage(0);
        isrunning=true;
    }
    private Handler mhandler=new Handler(){
    @Override
    public void handleMessage(Message msg) {
        if(isrunning){
            Message mMessage=new Message();
            mviewpager.setCurrentItem(mviewpager.getCurrentItem()+1);
            mhandler.sendMessageDelayed(mMessage,1000);
        }
    }
};

    @Override
    protected void onDestroy(){
        super.onDestroy();
        isrunning=false;
        mhandler=null;
    }

}

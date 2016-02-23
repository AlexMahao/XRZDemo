package com.mahao.xrzdemo.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;

/**  
 * 滑动显示标题
 */
public class OrderView extends LinearLayout{

    private View stayView; 
    //显示隐藏
    private StayViewListener stayViewListener;  
    private ScrollView scrollView;  
      
    public void setStayView(View stayview,ScrollView scrollview,StayViewListener stayViewListener){  
        this.stayView = stayview;  
        this.scrollView = scrollview;  
        this.stayViewListener = stayViewListener;  
    }  
      
    public OrderView(Context context) {  
        super(context);  
          
    }  
    public OrderView(Context context, AttributeSet attrs) {  
        super(context, attrs);  
        init();  
          
    }  
    private void init() {  
        setOrientation(LinearLayout.VERTICAL);  
    }  
  
      
    /** 
     *  
     */  
    boolean up = true;  
    @Override  
    public void computeScroll() {  
        if(stayView!=null&&scrollView!=null&&stayViewListener!=null){ 
        	//就是这个view相对于“坐标系统原点”在Y轴上的偏移量.(getScrollX同理)
            int y = scrollView.getScrollY();
            if(up){  
            	//相对于父视图，及scrollView
                int top = stayView.getTop();
                if(y>=top){  
                    stayViewListener.onStayViewShow();  
                    up = false;  
                }  
            }  
            if(!up){  
                int bottom = stayView.getBottom();  
                if(y<=bottom-stayView.getHeight()){  
                    stayViewListener.onStayViewGone();  
                    up = true;  
                }  
            }  
        }  
    }  
      
      
    public interface StayViewListener{  
        public void onStayViewShow();  
        public void onStayViewGone();  
    }  
	
}

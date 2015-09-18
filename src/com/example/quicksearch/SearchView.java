package com.example.quicksearch;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;

public class SearchView extends FrameLayout {
	public static String[] indexArr = { "A", "B", "C", "D", "E", "F", "G", "H", "I",
			"J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
			"W", "X", "Y", "Z" };
	private Paint paint;
	private int measuredWidth;
	private int measuredHeight;
	private int y;
	private int touchWord=-1;
	private OnTouchWordLinstener linstener;
	public SearchView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public SearchView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public SearchView(Context context) {
		super(context);
		init();
	}

	private void init() {
		float text_size = getResources().getDimension(R.dimen.text_size);
		paint = new Paint();
		paint.setFlags(Paint.ANTI_ALIAS_FLAG);
		paint.setColor(Color.WHITE);
		paint.setTextSize(text_size);
		paint.setTextAlign(Align.CENTER);
	}
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		// TODO Auto-generated method stub
		super.onSizeChanged(w, h, oldw, oldh);
		measuredWidth = getMeasuredWidth();
		measuredHeight = getMeasuredHeight();
	}
	@Override
	protected void onDraw(Canvas canvas) {
		for (int i = 0; i < indexArr.length; i++) {
			String text = indexArr[i];
			int x=measuredWidth/2;
			if (indexArr.length!=0) {
				y = measuredHeight/indexArr.length;
			}
			if (i==touchWord) {
				paint.setColor(Color.BLACK);
			}
			else {
				paint.setColor(Color.WHITE);
			}
			int newy=y/2+getTextHeight(text)/2+i*y;
			float[] pos={x,newy};
			canvas.drawPosText(text, pos, paint);
		}
	}
	private int getTextHeight(String text) {
		Rect bounds = new Rect();//得到矩形对象
		paint.getTextBounds(text, 0, text.length(), bounds);
		return bounds.height();
	}
	@Override
	protected void onLayout(boolean changed, int left, int top, int right,
			int bottom) {
		// TODO Auto-generated method stub
		super.onLayout(changed, left, top, right, bottom);
	}
//	@Override
//	public boolean onInterceptTouchEvent(MotionEvent ev) {
//		// TODO Auto-generated method stub
//		return true;
//	}
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
		case MotionEvent.ACTION_MOVE:
			float touchY = event.getY();
			int	index = (int) (touchY/y);
			if (index!=touchWord) {
				if (index>0&&index<indexArr.length) {
					if (linstener!=null) {
						linstener.getWord(indexArr[index]);
					}
				}
			}
			touchWord=index;
			break;
		case MotionEvent.ACTION_UP:
			touchWord=-1;
			break;
		}
		invalidate();
		return true;
	}
	public void setOnTouchWordLinstener(OnTouchWordLinstener linstener){
		this.linstener=linstener;	
	}
	public interface OnTouchWordLinstener{
		void getWord(String indexArr);
	}
}

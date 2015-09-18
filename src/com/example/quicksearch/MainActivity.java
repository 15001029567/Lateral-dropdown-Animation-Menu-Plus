package com.example.quicksearch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import com.example.quicksearch.SearchView.OnTouchWordLinstener;
import com.nineoldandroids.view.ViewHelper;
import com.nineoldandroids.view.ViewPropertyAnimator;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {
	int position;
	public ListView listview;
	private SearchView serchview;
	private ListViewAdapter mAdapter;
	private List<Friend> friends=new ArrayList<Friend>();
	private HashMap<String, Integer> wordPosition=new HashMap<String, Integer>();
	private TextView tv_show_word;
	private Handler mHandler=new Handler(){
		
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		fillList();
		Collections.sort(friends);
		listview = (ListView) findViewById(R.id.listview);
		serchview = (SearchView) findViewById(R.id.serchview);
		tv_show_word = (TextView) findViewById(R.id.tv_show_word);
		mAdapter = new ListViewAdapter();
		listview.setAdapter(mAdapter);
		serchview.setOnTouchWordLinstener(new OnTouchWordLinstener() {
			
			@Override
			public void getWord(String indexArr) {
				tv_show_word.setText(indexArr);
				tv_show_word.setVisibility(View.VISIBLE);
				mHandler.removeCallbacksAndMessages(null);
				mHandler.postDelayed(new Runnable() {
					@Override
					public void run() {
						tv_show_word.setVisibility(View.GONE);
					}
				}, 2000);
				if (wordPosition.containsKey(indexArr)) {
					position= wordPosition.get(indexArr);
					listview.setSelection(position);
				}
			}
		});
	}
	private void fillList() {
		
		friends.add(new Friend("张三"));
		friends.add(new Friend("李四"));
		friends.add(new Friend("王五"));
		friends.add(new Friend("赵六"));
		friends.add(new Friend("谢霆锋"));
		friends.add(new Friend("刘德华"));
		friends.add(new Friend("张柏芝"));
		friends.add(new Friend("谢楠"));
		friends.add(new Friend("吴京"));
		friends.add(new Friend("李晨"));
		friends.add(new Friend("范冰冰"));
		friends.add(new Friend("朱茵"));
		friends.add(new Friend("高圆圆"));
		friends.add(new Friend("陈奕迅"));
		friends.add(new Friend("白百合"));
		friends.add(new Friend("陈羽凡"));
		friends.add(new Friend("陈翔"));
		friends.add(new Friend("胡巴"));
		friends.add(new Friend("郑凯"));
		friends.add(new Friend("王祖蓝"));
		friends.add(new Friend("杨颖"));
		friends.add(new Friend("王宝强"));
		friends.add(new Friend("邓超"));
		friends.add(new Friend("孙俪"));
	}
	class ListViewAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return friends.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView==null) {
				convertView=View.inflate(getApplicationContext(), R.layout.adapter_list_view, null);
			}
			ViewHolder mHolder=new ViewHolder(convertView).getViewHolder();
			com.example.quicksearch.Friend friend = friends.get(position);
			String pinyin = friend.getPinyin();
			String firstName = pinyin.substring(0, 1);
			if (position!=0){
				Friend friend2 = friends.get(position-1);
				String pinyin2 = friend2.getPinyin();
				String frontName = pinyin2.substring(0, 1);
				if (firstName.trim().equals(frontName.trim())) {
					mHolder.textview1.setVisibility(View.GONE);
					mHolder.view.setVisibility(View.GONE);
				}
				else {
					wordPosition.put(firstName, position);
					mHolder.textview1.setVisibility(View.VISIBLE);
					mHolder.view.setVisibility(View.VISIBLE);
					mHolder.textview1.setText(firstName);
				}
			}else {
				mHolder.textview1.setVisibility(View.VISIBLE);
				mHolder.view.setVisibility(View.VISIBLE);
				mHolder.textview1.setText(firstName);
				wordPosition.put(firstName, position);
			}
			String name = friend.getName();
			mHolder.textview2.setText(name);
			ViewHelper.setRotationX(convertView, 60);
			ViewPropertyAnimator.animate(convertView).rotationX(0).setDuration(600).setInterpolator(new OvershootInterpolator(8)).start();
			ViewHelper.setScaleX(convertView, 0.4f);
			ViewPropertyAnimator.animate(convertView).scaleX(1f).setDuration(600).setInterpolator(new OvershootInterpolator(8)).start();
			return convertView;
		}

	}
	
	class ViewHolder{
		TextView textview1;
		TextView textview2;
		View view;
		View convertView;
		public ViewHolder(View convertView) {
			super();
			this.convertView=convertView;
		}
		ViewHolder mHolder;
		public  ViewHolder getViewHolder(){
			mHolder=(ViewHolder) convertView.getTag();
			if (mHolder==null) {
				mHolder=new ViewHolder(convertView);
				convertView.setTag(mHolder);
			}
			mHolder.textview1=(TextView) convertView.findViewById(R.id.textview1);
			mHolder.textview2=(TextView) convertView.findViewById(R.id.textview2);
			mHolder.view=convertView.findViewById(R.id.view);
			return mHolder;
		}
	}
}

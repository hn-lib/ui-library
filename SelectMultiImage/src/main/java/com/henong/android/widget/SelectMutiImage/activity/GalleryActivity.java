package com.henong.android.widget.SelectMutiImage.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.henong.android.widget.SelectMultiImage.R;
import com.henong.android.widget.SelectMutiImage.SelectBitmapUtil;
import com.henong.android.widget.SelectMutiImage.adapter.MyPageAdapter;
import com.henong.android.widget.SelectMutiImage.view.ViewPagerFixed;
import com.henong.android.widget.SelectMutiImage.view.ZoomableImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by EasyShare005 on 2015/7/1.
 */
public class GalleryActivity extends Activity {

	private Intent intent;
	// 返回按钮
	private Button back_bt;
	// 发送按钮
	private Button send_bt;
	// 删除按钮
	private Button del_bt;
	// 顶部显示预览图片位置的textview
	private TextView positionTextView;
	// 获取前一个activity传过来的position
	private int position;
	// 当前的位置
	private int location = 0;

	private ArrayList<View> listViews = null;
	private ViewPagerFixed pager;
	private MyPageAdapter adapter;

	public List<Bitmap> bmp = new ArrayList<Bitmap>();
	public List<String> drr = new ArrayList<String>();
	public List<String> del = new ArrayList<String>();

	private Context mContext;

	private RelativeLayout rlBottom;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gallery);

		mContext = this;
		back_bt = (Button) findViewById(R.id.gallery_back);
		send_bt = (Button) findViewById(R.id.send_button);
		del_bt = (Button) findViewById(R.id.gallery_del);
		rlBottom = (RelativeLayout) findViewById(R.id.bottom_layout);
		back_bt.setOnClickListener(new BackListener());
		send_bt.setOnClickListener(new GallerySendListener());
		del_bt.setOnClickListener(new DelListener());
		intent = getIntent();
		Bundle bundle = intent.getExtras();
		location = intent.getIntExtra("ID", 0);
		del_bt.setVisibility(View.VISIBLE);
		rlBottom.setVisibility(View.GONE);
		isShowOkBt();
		// 为发送按钮设置文字

		pager = (ViewPagerFixed) findViewById(R.id.gallery01);
		pager.setOnPageChangeListener(pageChangeListener);
		for (int i = 0; i < SelectBitmapUtil.tempSelectBitmap.size(); i++) {
			initListViews(SelectBitmapUtil.tempSelectBitmap.get(i).getBitmap());
		}
		adapter = new MyPageAdapter(listViews, GalleryActivity.this);
		pager.setAdapter(adapter);
		pager.setPageMargin(getResources().getDimensionPixelOffset(
				R.dimen.ui_10_dip));
		int id = intent.getIntExtra("ID", 0);
		pager.setCurrentItem(id);

	}

	private ViewPager.OnPageChangeListener pageChangeListener = new ViewPager.OnPageChangeListener() {

		public void onPageSelected(int arg0) {
			location = arg0;
		}

		public void onPageScrolled(int arg0, float arg1, int arg2) {

		}

		public void onPageScrollStateChanged(int arg0) {

		}
	};

	// 返回按钮添加的监听器
	private class BackListener implements View.OnClickListener {

		public void onClick(View v) {
			finish();
		}
	}

	// 删除按钮添加的监听器
	private class DelListener implements View.OnClickListener {

		public void onClick(View v) {
			if (listViews.size() == 1) {
				SelectBitmapUtil.tempSelectBitmap.clear();
				SelectBitmapUtil.max = 0;
				send_bt.setText("完成" + "(" + SelectBitmapUtil.tempSelectBitmap.size() + "/"
						+ 6 + ")");
				Intent intent = new Intent("data.broadcast.action");
				sendBroadcast(intent);
				finish();
			} else {
				SelectBitmapUtil.tempSelectBitmap.remove(location);
				SelectBitmapUtil.max--;
				pager.removeAllViews();
				listViews.remove(location);
				adapter.setListViews(listViews);
				send_bt.setText("完成" + "(" + SelectBitmapUtil.tempSelectBitmap.size() + "/"
						+ 6 + ")");
				adapter.notifyDataSetChanged();
			}
		}
	}

	private void initListViews(Bitmap bm) {
		if (listViews == null)
			listViews = new ArrayList<View>();
		ZoomableImageView img = new ZoomableImageView(this);
		img.setBackgroundColor(0xff000000);
		img.setImageBitmap(bm);
		img.setLayoutParams(new ViewGroup.LayoutParams(
				ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.MATCH_PARENT));
		listViews.add(img);
	}

	// 完成按钮的监听
	private class GallerySendListener implements View.OnClickListener {
		public void onClick(View v) {
			finish();
		}

	}

	public void isShowOkBt() {
		if (SelectBitmapUtil.tempSelectBitmap.size() > 0) {
			send_bt.setText("完成" + "(" + SelectBitmapUtil.tempSelectBitmap.size() + "/" + 6
					+ ")");
			send_bt.setPressed(true);
			send_bt.setClickable(true);
			send_bt.setTextColor(Color.WHITE);
		} else {
			send_bt.setPressed(false);
			send_bt.setClickable(false);
			send_bt.setTextColor(Color.parseColor("#E1E0DE"));
		}
	}

	/**
	 * 监听返回按钮
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		if (keyCode == KeyEvent.KEYCODE_BACK) {
			finish();
		}
		return true;
	}
}
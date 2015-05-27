package com.datian.uppopupdemo;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

public class ExitPopup extends PopupWindow {

	private String mMsg;
	private View mView;
	private TextView mMsgTv;
	private TextView mConfirmTv, mCancelTv,mNeutralityTv;
	public interface OnClickListener{
		public void onClick(ExitPopup exitPopup);
	}
	private OnClickListener confirmListener,neutralityListener,cancelListener;
	
	public ExitPopup(final Activity act,String msg) {
		mMsg = msg;
		mView = View.inflate(act, R.layout.popup_exit, null);
		mConfirmTv = (TextView) mView.findViewById(R.id.popup_confirm);
		mNeutralityTv = (TextView) mView.findViewById(R.id.popup_neutrality);
		mCancelTv = (TextView) mView.findViewById(R.id.popup_cancel);
		mMsgTv = (TextView) mView.findViewById(R.id.popup_msg);
		// 设置SelectPicPopupWindow的View
		this.setContentView(mView);
		// 设置SelectPicPopupWindow弹出窗体的宽
		this.setWidth(LayoutParams.MATCH_PARENT);
		// 设置SelectPicPopupWindow弹出窗体的高
		this.setHeight(LayoutParams.WRAP_CONTENT);
		this.setFocusable(true);
		this.setOutsideTouchable(false);
		// 实例化一个ColorDrawable颜色为半透明
		ColorDrawable dw = new ColorDrawable(0xb0000000);
		// 设置SelectPicPopupWindow弹出窗体的背景
		this.setBackgroundDrawable(dw);
		// 设置SelectPicPopupWindow弹出窗体动画效果
		this.setAnimationStyle(R.style.AnimBottom);
		WindowManager.LayoutParams att = act.getWindow().getAttributes();
		att.alpha = 0.4f;
		act.getWindow().setAttributes(att);
		this.setOnDismissListener(new PopupWindow.OnDismissListener() {
			@Override
			public void onDismiss() {
				WindowManager.LayoutParams att = act.getWindow()
						.getAttributes();
				att.alpha = 1f;
				act.getWindow().setAttributes(att);
			}
		});
	}

	public void setMsg(String msg){
		if(msg==null){
			mMsgTv.setVisibility(View.GONE);
		}else{
			mMsgTv.setVisibility(View.VISIBLE);
			mMsgTv.setText(msg);
		}
	}
	/**
	 * 设置确定按钮监听
	 * @param btnName 按钮的名字
	 * @param onClickListener 点击监听
	 */
	public void setConfirmListener(String btnName,OnClickListener onClickListener){
		if(onClickListener==null){
			mConfirmTv.setVisibility(View.GONE);
		}else{
			mConfirmTv.setVisibility(View.VISIBLE);
		}
		if(btnName!=null)
			mConfirmTv.setText(btnName);
		this.confirmListener = onClickListener;
	}
	
	/**
	 * 设置中立按钮的监听
	 * @param btnName   按钮的名字
	 * @param onClickListener 点击监听
	 */
	public void setNeutralityListener(String btnName,OnClickListener onClickListener){
		if(onClickListener==null){
			mNeutralityTv.setVisibility(View.GONE);
		}else{
			mNeutralityTv.setVisibility(View.VISIBLE);
		}
		if(btnName!=null)
			mNeutralityTv.setText(btnName);
		this.neutralityListener = onClickListener;
	}
	/**
	 * 设置取消按钮的监听
	 * @param btnName   按钮的名字
	 * @param onClickListener 点击监听
	 */
	public void setCancelListener(String btnName,OnClickListener onClickListener){
		if(onClickListener==null){
			mCancelTv.setVisibility(View.GONE);
		}else{
			mCancelTv.setVisibility(View.VISIBLE);
		}
		if(btnName!=null)
			mCancelTv.setText(btnName);
		this.cancelListener = onClickListener;
	}
	
	public void show(View parent) {
		mMsgTv.setText(mMsg);
		if(confirmListener != null){
			mConfirmTv.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					confirmListener.onClick(ExitPopup.this);
				}
			});
		}else{
			mConfirmTv.setVisibility(View.GONE);
		}
		if(neutralityListener != null){
			mNeutralityTv.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					neutralityListener.onClick(ExitPopup.this);
				}
			});
		}else{
			mNeutralityTv.setVisibility(View.GONE);
		}
		if(cancelListener != null){
			mCancelTv.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					cancelListener.onClick(ExitPopup.this);
				}
			});
		}else{
			mCancelTv.setVisibility(View.GONE);
		}
		super.showAtLocation(parent, Gravity.BOTTOM, 0, 0);
	}
	
}
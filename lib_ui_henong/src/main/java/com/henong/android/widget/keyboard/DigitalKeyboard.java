package com.henong.android.widget.keyboard;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import android.annotation.SuppressLint;
import android.content.Context;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.os.Build;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.InputType;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.henong.library_base.R;

/**
 * 
 * @author Created by xuejinwei from Github.
 * @author Modified as single library by Ralken, 2016-10-27.
 */
@SuppressLint("NewApi")
public class DigitalKeyboard {
    private FragmentActivity mActivity;
    private boolean  mIfRandom;

    private DigitalKeyBoardView mKeyboardView;
    private Keyboard       mKeyboardNumber;
    private EditText       mEditText;
    private KeyboardInterface keyboardInterface;

    public DigitalKeyboard(FragmentActivity activity) {
        this(activity, false);
    }

    public DigitalKeyboard(FragmentActivity activity, boolean ifRandom) {
        this.mActivity = activity;
        this.mIfRandom = ifRandom;
        mKeyboardNumber = new Keyboard(mActivity, R.xml.keyboard_number);
        mKeyboardView = (DigitalKeyBoardView) mActivity.findViewById(R.id.keyboard_view);
    }
    
    /** Attach specific custimized keyboard to given EditTexts. */
    public void attach(EditText... editText){
    	final OnFocusChangeListener l = new OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (hasFocus) {
					attachTo((EditText) v);
				}
			}
		};
		
		final OnClickListener clickListener = new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(v.hasFocus()){
					attachTo((EditText) v);
				}
			}
		};
		
    	for (EditText edit : editText) {
			edit.setOnFocusChangeListener(l);
			edit.setOnClickListener(clickListener);
		}
    }

    /** Attach specific custimized keyboard to given editText. */
    public void attachTo(EditText editText) {
        this.mEditText = editText;
        if(keyboardInterface!=null){
            keyboardInterface.showKey();
        }
        hideSystemSofeKeyboard(mActivity.getApplicationContext(), mEditText);
        showSoftKeyboard();
    }

    public void showSoftKeyboard() {
        if (mKeyboardNumber == null) {
            mKeyboardNumber = new Keyboard(mActivity, R.xml.keyboard_number);
        }
        if (mKeyboardView == null) {
            mKeyboardView = (DigitalKeyBoardView) mActivity.findViewById(R.id.keyboard_view);
        }
        if (mIfRandom) {
            randomKeyboardNumber();
        } else {
            mKeyboardView.setKeyboard(mKeyboardNumber);
        }
        mKeyboardView.setEnabled(true);
        mKeyboardView.setPreviewEnabled(false);
        mKeyboardView.setVisibility(View.VISIBLE);
        mKeyboardView.setOnKeyboardActionListener(mOnKeyboardActionListener);
    }

    private KeyboardView.OnKeyboardActionListener mOnKeyboardActionListener = new KeyboardView.OnKeyboardActionListener() {
        @Override
        public void onPress(int primaryCode) {
        }

        @Override
        public void onRelease(int primaryCode) {
        }

        @Override
        public void onKey(int primaryCode, int[] keyCodes) {
            Editable editable = mEditText.getText();
            
            int start = mEditText.getSelectionStart();
            if (primaryCode == Keyboard.KEYCODE_DELETE) {	// Delete forward
                if (editable != null && editable.length() > 0) {
                    if (start > 0) {
                        editable.delete(start - 1, start);
                    }
                }
            } else if (primaryCode == Keyboard.KEYCODE_CANCEL) {// Hide/Cancel
                hideKeyboard();
                if (mOnCancelClick != null) {
                    mOnCancelClick.onCancellClick();
                }
            } else if (primaryCode == Keyboard.KEYCODE_DONE) {// Confirm/Done
                hideKeyboard();
                if (mOnOkClick != null) {
                    mOnOkClick.onOkClick();
                }
            } else if (primaryCode == -6) {		// Clear
            	//editable.delete(0, start);
            	mEditText.setText(null);
            } else {
                editable.insert(start, Character.toString((char) primaryCode));
            }
        }

        @Override
        public void onText(CharSequence text) {
        }

        @Override
        public void swipeLeft() {
        }

        @Override
        public void swipeRight() {
        }

        @Override
        public void swipeDown() {
        }

        @Override
        public void swipeUp() {
        }
    };


    /**
     * hide the system soft keyboard
     */
    private static void hideSystemSofeKeyboard(Context context, EditText editText) {
        int sdkInt = Build.VERSION.SDK_INT;
        if (sdkInt >= 11) {
            try {
                Class<EditText> cls = EditText.class;
                Method setShowSoftInputOnFocus;
                setShowSoftInputOnFocus = cls.getMethod("setShowSoftInputOnFocus", boolean.class);
                setShowSoftInputOnFocus.setAccessible(true);
                setShowSoftInputOnFocus.invoke(editText, false);

            } catch (SecurityException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            editText.setInputType(InputType.TYPE_NULL);
        }
        // 如果软键盘已经显示，则隐藏
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }

    public void setKeyboardInterface(KeyboardInterface keyboardInterface) {
        this.keyboardInterface = keyboardInterface;
    }

    public interface OnOkClick {
        void onOkClick();
    }

    public interface onCancelClick {
        void onCancellClick();
    }

    public OnOkClick mOnOkClick = null;
    public onCancelClick mOnCancelClick;

    public void setOnOkClick(OnOkClick onOkClick) {
        mOnOkClick = onOkClick;
    }

    public void setOnCancelClick(onCancelClick onCancelClick) {
        mOnCancelClick = onCancelClick;
    }


    private boolean isNumber(String str) {
        String wordstr = "0123456789";
        return wordstr.contains(str);
    }

    private void randomKeyboardNumber() {
        List<Keyboard.Key> keyList = mKeyboardNumber.getKeys();
        // 查找出0-9的数字键
        List<Keyboard.Key> newkeyList = new ArrayList<Keyboard.Key>();
        for (int i = 0; i < keyList.size(); i++) {
            if (keyList.get(i).label != null
                    && isNumber(keyList.get(i).label.toString())) {
                newkeyList.add(keyList.get(i));
            }
        }
        // 数组长度
        int count = newkeyList.size();
        // 结果集
        List<KeyModel> resultList = new ArrayList<KeyModel>();
        // 用一个LinkedList作为中介
        LinkedList<KeyModel> temp = new LinkedList<KeyModel>();
        // 初始化temp
        for (int i = 0; i < count; i++) {
            temp.add(new KeyModel(48 + i, i + ""));
        }
        // 取数
        Random rand = new Random();
        for (int i = 0; i < count; i++) {
            int num = rand.nextInt(count - i);
            resultList.add(new KeyModel(temp.get(num).getCode(),
                    temp.get(num).getLable()));
            temp.remove(num);
        }
        for (int i = 0; i < newkeyList.size(); i++) {
            newkeyList.get(i).label = resultList.get(i).getLable();
            newkeyList.get(i).codes[0] = resultList.get(i)
                    .getCode();
        }

        mKeyboardView.setKeyboard(mKeyboardNumber);
    }

    public void showKeyboard() {
        int visibility = mKeyboardView.getVisibility();
        if (visibility == View.GONE || visibility == View.INVISIBLE) {
            mKeyboardView.setVisibility(View.VISIBLE);
        }
    }

    public void hideKeyboard() {
        if(keyboardInterface!=null){
            keyboardInterface.hintKey();
        }
        int visibility = mKeyboardView.getVisibility();
        if (visibility == View.VISIBLE) {
            mKeyboardView.setVisibility(View.GONE);
        }
    }

    public interface KeyboardInterface{
        void showKey();
        void hintKey();
    }
}
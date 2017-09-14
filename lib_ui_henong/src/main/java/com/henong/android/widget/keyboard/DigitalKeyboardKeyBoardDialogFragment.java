package com.henong.android.widget.keyboard;

import java.lang.reflect.Method;

import android.annotation.SuppressLint;
import android.content.Context;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.henong.android.widget.keyboard.DigitalKeyboard.OnOkClick;
import com.henong.android.widget.keyboard.DigitalKeyboard.onCancelClick;
import com.henong.library_base.R;

/**
 * 
 * @author Created by xuejinwei from Github.
 * @author Modified as single library by Ralken, 2016-10-27.
 */
@SuppressLint({"NewApi", "ValidFragment"})
public class DigitalKeyboardKeyBoardDialogFragment extends BaseKeyBoardDialogFragment {
    private FragmentActivity mActivity;

    private DigitalKeyBoardView mKeyboardView;
    private Keyboard       mKeyboardNumber;
    private EditText       mEditText;

    public DigitalKeyboardKeyBoardDialogFragment(FragmentActivity activity, EditText editText) {
        this.mActivity = activity;
        this.mEditText = editText;
        mKeyboardNumber = new Keyboard(mActivity, R.xml.keyboard_number);
    }

    @Override
    public void show(FragmentManager manager, String tag) {
    	super.show(manager, tag);
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	View root = inflater.inflate(R.layout.dialog_keyboard, null);
    	mKeyboardView = (DigitalKeyBoardView) root.findViewById(R.id.keyboard_view);
    	
    	return root;
    }
    
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    	super.onViewCreated(view, savedInstanceState);
    	hideSystemSofeKeyboard(mActivity.getApplicationContext(), mEditText);
        showSoftKeyboard();
    }
    
    private void showSoftKeyboard() {
        mKeyboardView.setKeyboard(mKeyboardNumber);
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
                //hideKeyboard();
            	dismissAllowingStateLoss();
                if (mOnCancelClick != null) {
                    mOnCancelClick.onCancellClick();
                }
            } else if (primaryCode == Keyboard.KEYCODE_DONE) {// Confirm/Done
                //hideKeyboard();
            	dismissAllowingStateLoss();
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

    public OnOkClick mOnOkClick = null;
    public onCancelClick mOnCancelClick;

    public void setOnOkClick(OnOkClick onOkClick) {
        mOnOkClick = onOkClick;
    }

    public void setOnCancelClick(onCancelClick onCancelClick) {
        mOnCancelClick = onCancelClick;
    }

    public void showKeyboard() {
        int visibility = mKeyboardView.getVisibility();
        if (visibility == View.GONE || visibility == View.INVISIBLE) {
            mKeyboardView.setVisibility(View.VISIBLE);
        }
    }

    public void hideKeyboard() {
        int visibility = mKeyboardView.getVisibility();
        if (visibility == View.VISIBLE) {
            mKeyboardView.setVisibility(View.GONE);
        }
    }
}
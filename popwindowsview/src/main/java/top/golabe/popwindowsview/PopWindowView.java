package top.golabe.popwindowsview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PopWindowView {
    private View mAnchorView;
    private PopupWindow  mPopupWindow;
    private Context mCtx;
    private List<String> mItemList;
    private ListView mListView;
    private int mBgColor;
    private ListAdapter mAdapter;
    private OnItemClickListener mOnItemClickListener = null;
    private int mDeviceW;
    private int mDeviceH;
    private int mPopupWindowHeight;
    private int mPopupWindowWidth;
    private int mPopAnimStyle;
    private boolean mModal;
    private Drawable mDivider;

    private PopWindowView(Builder builder) {
        this.mCtx = builder.ctx;
        this.mBgColor = builder.bgColor;
        this.mAdapter = builder.listAdapter;
        this.mItemList = builder.itemData;
        this.mOnItemClickListener = builder.onItemClickListener;
        this.mAnchorView = builder.anchorView;
        this.mPopAnimStyle = builder.popAnimStyle;
        this.mModal = builder.modal;
        this.mPopupWindowWidth = builder.popupWindowWidth;
        this.mPopupWindowHeight=builder.popupWindowHeight;
        this.mDivider=builder.divider;
        setWH();

    }


    public interface OnItemClickListener {
        void onItemClick(PopWindowView pop, AdapterView<?> parent, View v, int position, long id);
    }

    private void show() {

        if (mAnchorView == null) {
            throw new IllegalArgumentException("PopupWindow show location view can  not be null");
        }
        if (mItemList == null) {
            throw new IllegalArgumentException("please fill ListView Data");
        }
        mListView = new ListView(mCtx);

        if (mDivider!=null){
            mListView.setDivider(mDivider);
        }
        mListView.setBackgroundColor(mBgColor);
        mListView.setVerticalScrollBarEnabled(false);
        if (mAdapter == null) {
            mListView.setAdapter(new ArrayAdapter<>(mCtx, android.R.layout.simple_list_item_1,mItemList));
        } else {
            mListView.setAdapter(mAdapter);
        }

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(PopWindowView.this, parent, view, position, id);
                }
            }
        });
        mListView.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        if (mPopupWindowWidth == 0) {
            mPopupWindowWidth = mDeviceW / 3;
        }
        if (mPopupWindowHeight == 0) {
            mPopupWindowHeight = mItemList.size() * mListView.getMeasuredHeight();
            if (mPopupWindowHeight > mDeviceH / 2) {
                mPopupWindowHeight = mDeviceH / 2;
            }
        }
        mPopupWindow = new PopupWindow(mListView, mPopupWindowWidth, mPopupWindowHeight);
        if (mPopAnimStyle != 0) {
            mPopupWindow.setAnimationStyle(mPopAnimStyle);
        }
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setFocusable(mModal);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable(mCtx.getResources(), (Bitmap) null));


        Rect location = locateView(mAnchorView);
        if (location != null) {
            int x;
            //view中心点X坐标
            int xMiddle = location.left + mAnchorView.getWidth() / 2;
            if (xMiddle > mDeviceW / 2) {
                //在右边
                x = xMiddle - mPopupWindowWidth;
            } else {
                x = xMiddle;
            }
            int y;
            //view中心点Y坐标
            int yMiddle = location.top + mAnchorView.getHeight() / 2;
            if (yMiddle > mDeviceH / 2) {
                //在下方
                y = yMiddle - mPopupWindowHeight;
            } else {
                //在上方
                y = yMiddle;
            }
            mPopupWindow.showAtLocation(mAnchorView, Gravity.NO_GRAVITY, x, y);
        }
    }

    public void dismiss() {
        if (mPopupWindow != null && mPopupWindow.isShowing()) {
            mPopupWindow.dismiss();
        }
    }

    private Rect locateView(View view) {

        if (view == null) return null;
        int[] loc_int = new int[2];

        try {
            view.getLocationOnScreen(loc_int);

        } catch (NullPointerException e) {
            e.printStackTrace();
            return null;
        }
        Rect location = new Rect();
        location.left = loc_int[0];
        location.top = loc_int[1];
        location.right = location.left + view.getWidth();
        location.bottom = location.top + view.getHeight();
        return location;
    }

    private void setWH() {
        WindowManager wm = (WindowManager) mCtx.getSystemService(Context.WINDOW_SERVICE);
        //API 13才允许使用新方法
        Point outSize = new Point();
        wm.getDefaultDisplay().getSize(outSize);
        if (outSize.x != 0) {
            mDeviceW = outSize.x;
        }
        if (outSize.y != 0) {
            mDeviceH = outSize.y;
        }
    }


    public static class Builder {
        private int popupWindowHeight=0;
        private int popAnimStyle = 0;
        private boolean modal = true;
        private int popupWindowWidth = 0;
        private Context ctx;
        private int bgColor = Color.WHITE;
        private ListAdapter listAdapter = null;
        private List<String> itemData;
        private OnItemClickListener onItemClickListener = null;
        private View anchorView = null;
        private Drawable divider=null;

        public Builder(Context ctx,View view) {
            this.ctx = ctx;
            this.anchorView = view;
        }
        public Builder setDivider(Drawable divider){
            this.divider=divider;
            return this;
        }
        public Builder setPopAnimStyle(int style){
            this.popAnimStyle=style;
            return this;
        }

        public Builder setPopupWindowHeight(int height) {
            this.popupWindowHeight = height;
            return this;
        }
        public Builder setPopupWindowWidth(int width) {
            this.popupWindowWidth = width;
            return this;
        }

        public Builder setModal(boolean modal) {
            this.modal = modal;
            return this;
        }

        public Builder setItems(String... items) {
            if (itemData == null) {
                this.itemData = new ArrayList<>();
                this.itemData.addAll(Arrays.asList(items));
            } else {
                this.itemData.clear();
                this.itemData.addAll(Arrays.asList(items));
            }
            return this;
        }

        public Builder setItems(List<String> list) {
            this.itemData = list;
            return this;
        }

        public Builder setAdapter(ListAdapter adapter) {
            this.listAdapter = adapter;
            return this;
        }

        public Builder setBackgroundColor(int bgColor) {
            this.bgColor = bgColor;
            return this;
        }

        public Builder setItemClickListener(OnItemClickListener listener) {
            this.onItemClickListener = listener;
            return this;
        }

        public PopWindowView show() {
            PopWindowView pop = new PopWindowView(this);
            pop.show();
            return pop;
        }
    }

}

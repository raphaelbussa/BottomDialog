/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2016 Raphaël Bussa
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package rebus.bottomdialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.view.SupportMenuInflater;
import android.support.v7.view.menu.MenuBuilder;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by raphaelbussa on 19/01/16.
 */
public class BottomDialog {

    private CustomDialog customDialog;

    public BottomDialog(Context context) {
        customDialog = new CustomDialog(context);
    }

    public void title(String title) {
        customDialog.title(title);
    }

    public void title(int title) {
        customDialog.title(title);
    }

    public void inflateMenu(int menu) {
        customDialog.inflateMenu(menu);
    }

    public void addItems(List<Item> items) {
        customDialog.addItems(items);
    }

    public void addItem(Item item) {
        customDialog.addItem(item);
    }

    public void cancelable(boolean value) {
        customDialog.setCancelable(value);
    }

    public void canceledOnTouchOutside(boolean value) {
        customDialog.setCanceledOnTouchOutside(value);
    }

    public void setOnItemSelectedListener(OnItemSelectedListener onItemSelectedListener) {
        customDialog.setOnItemSelectedListener(onItemSelectedListener);
    }

    public void show() {
        customDialog.show();
    }

    public void dismiss() {
        customDialog.dismiss();
    }

    public interface OnItemSelectedListener {
        boolean onItemSelected(int id);
    }

    private class CustomDialog extends Dialog implements View.OnClickListener {

        private final String TAG = CustomDialog.class.getName();

        private int padding;
        private int icon;
        private LinearLayout container;
        private OnItemSelectedListener onItemSelectedListener;
        private List<Item> items;

        public CustomDialog(Context context) {
            super(context);
            items = new ArrayList<>();
            icon = getContext().getResources().getDimensionPixelSize(R.dimen.dimen_32_dp);
            padding = getContext().getResources().getDimensionPixelSize(R.dimen.dimen_8_dp);
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            container = new LinearLayout(getContext());
            container.setLayoutParams(params);
            container.setBackgroundColor(Color.WHITE);
            container.setOrientation(LinearLayout.VERTICAL);
            container.setPadding(0, padding, 0, padding);
            ScrollView scrollView = new ScrollView(getContext());
            scrollView.addView(container);
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            setContentView(scrollView, params);
            setCancelable(true);
            setCanceledOnTouchOutside(true);
            getWindow().setGravity(Gravity.BOTTOM);
            getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
            getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        }

        public void cancelable(boolean value) {
            setCancelable(value);
        }

        public void canceledOnTouchOutside(boolean value) {
            setCanceledOnTouchOutside(value);
        }

        public void addItems(List<Item> itemList) {
            items.clear();
            items.addAll(itemList);
            for (Item item : items) {
                addItem(item);
            }
        }

        public void addItem(Item item) {
            int size = icon + padding + padding;
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, size);
            TextView row = new TextView(getContext());
            row.setId(item.getId());
            row.setLayoutParams(params);
            row.setMaxLines(1);
            row.setEllipsize(TextUtils.TruncateAt.END);
            row.setGravity(Gravity.CENTER_VERTICAL);
            row.setText(item.getTitle());
            row.setTypeface(Typeface.DEFAULT_BOLD);
            row.setOnClickListener(this);
            row.setTextColor(Utils.colorStateListText(getContext()));
            TypedValue typedValue = new TypedValue();
            getContext().getTheme().resolveAttribute(android.R.attr.selectableItemBackground, typedValue, true);
            row.setBackgroundResource(typedValue.resourceId);
            if (item.getIcon() != null) {
                row.setCompoundDrawablesWithIntrinsicBounds(icon(item.getIcon()), null, null, null);
                row.setCompoundDrawablePadding(padding);
                row.setPadding(padding, padding, padding, padding);
            } else {
                row.setPadding(size, padding, padding, padding);
            }
            container.addView(row);
        }

        public void inflateMenu(int menu) {
            MenuInflater menuInflater = new SupportMenuInflater(getContext());
            MenuBuilder menuBuilder = new MenuBuilder(getContext());
            menuInflater.inflate(menu, menuBuilder);
            List<Item> items = new ArrayList<>();
            for (int i = 0; i < menuBuilder.size(); i++) {
                MenuItem menuItem = menuBuilder.getItem(i);
                Item item = new Item();
                item.setId(menuItem.getItemId());
                item.setIcon(menuItem.getIcon());
                item.setTitle(menuItem.getTitle().toString());
                items.add(item);
            }
            addItems(items);
        }

        /**
         * @param drawable Drawable from menu item
         * @return Drawable resized 32dp x 32dp and colored with color textColorSecondary
         */
        private Drawable icon(Drawable drawable) {
            if (drawable != null) {
                Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
                Drawable resizeIcon = new BitmapDrawable(getContext().getResources(), Bitmap.createScaledBitmap(bitmap, icon, icon, true));
                Drawable.ConstantState state = resizeIcon.getConstantState();
                resizeIcon = DrawableCompat.wrap(state == null ? resizeIcon : state.newDrawable()).mutate();
                DrawableCompat.setTintList(resizeIcon, Utils.colorStateListIcon(getContext()));
                return resizeIcon;
            }
            return null;
        }

        public void title(int title) {
            title(getContext().getString(title));
        }

        public void title(String title) {
            int size = getContext().getResources().getDimensionPixelSize(R.dimen.dimen_16_dp) + padding;
            TextView item = new TextView(getContext());
            item.setText(title);
            item.setTextSize(TypedValue.COMPLEX_UNIT_SP, 32);
            item.setPadding(size, padding, padding, padding);
            container.addView(item);
        }

        @Override
        public void onClick(View v) {
            if (onItemSelectedListener != null) {
                if (onItemSelectedListener.onItemSelected(v.getId())) {
                    dismiss();
                }
            }
        }

        public void setOnItemSelectedListener(OnItemSelectedListener onItemSelectedListener) {
            this.onItemSelectedListener = onItemSelectedListener;
        }

    }

}

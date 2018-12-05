package com.mikhalevich.roadtogoal.goalmenu;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.mikhalevich.roadtogoal.R;

import java.util.ArrayList;
import java.util.List;

public class GoalMenuView extends FrameLayout {
    private final float DEFAULT_DISTANCE = 50.0f;

    private float distance;

    private List<View> buttons = new ArrayList<>();

    private class OnButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(final View view) {
        }
    }

    private class OnButtonLongClickListener implements View.OnLongClickListener {
        @Override
        public boolean onLongClick(final View view) {
            return true;
        }
    }

    public GoalMenuView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GoalMenuView(@NonNull Context context, @Nullable AttributeSet attrs,
                        @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        if (attrs == null) {
            throw new IllegalArgumentException("No buttons icons or colors set");
        }

        final List<Integer> icons;
        final List<Integer> colors;

        final TypedArray a = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.GoalMenuView, 0, 0);
        try {
            final int iconArrayId = a.getResourceId(R.styleable.GoalMenuView_button_icons,
                    0);
            final int colorArrayId = a.getResourceId(R.styleable.GoalMenuView_button_colors,
                    0);

            final TypedArray iconsIds = getResources().obtainTypedArray(iconArrayId);
            try {
                final int[] colorsIds = getResources().getIntArray(colorArrayId);
                final int buttonsCount = Math.min(iconsIds.length(), colorsIds.length);

                icons = new ArrayList<>(buttonsCount);
                colors = new ArrayList<>(buttonsCount);

                for (int i = 0; i < buttonsCount; i++) {
                    icons.add(iconsIds.getResourceId(i, -1));
                    colors.add(colorsIds[i]);
                }
            } finally {
                iconsIds.recycle();
            }

            distance = a.getDimension(R.styleable.GoalMenuView_distance, DEFAULT_DISTANCE);
        } finally {
            a.recycle();
        }

        initLayout(context);
        initButtons(context, icons, colors);
    }

    private void initLayout(@NonNull Context context) {
        LayoutInflater.from(context).inflate(R.layout.goal_menu, this, true);

        /*
        setWillNotDraw(true);
        setClipChildren(false);
        setClipToPadding(false);
        */
    }

    private void initButtons(@NonNull Context context, @NonNull List<Integer> icons,
                             @NonNull List<Integer> colors) {
        final int buttonsCount = Math.min(icons.size(), colors.size());
        for (int i = 0; i < buttonsCount; i++) {
            final FloatingActionButton button = new FloatingActionButton(context);
            button.setImageResource(icons.get(i));
            button.setBackgroundTintList(ColorStateList.valueOf(colors.get(i)));
            button.setClickable(true);
            button.setOnClickListener(new GoalMenuView.OnButtonClickListener());
            button.setOnLongClickListener(new GoalMenuView.OnButtonLongClickListener());
            button.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
                    LayoutParams.WRAP_CONTENT));
            button.setVisibility(INVISIBLE);

            addView(button);
            buttons.add(button);
        }
    }

    public void open(float centerX, float centerY) {
        // draw only even number of buttons in the menu
        if (buttons.size() % 2 != 0)
            throw new RuntimeException();

        for (int i = 0, cnt = buttons.size(); i < cnt; i += 2) {
            final float xoffset = distance * ((i / 2) + 1);

            View button = buttons.get(i);
            button.setX(centerX + xoffset);
            button.setY(centerY);
            button.setVisibility(VISIBLE);

            button = buttons.get(i + 1);
            button.setX(centerX - xoffset);
            button.setY(centerY);
            button.setVisibility(VISIBLE);
        }
    }
}

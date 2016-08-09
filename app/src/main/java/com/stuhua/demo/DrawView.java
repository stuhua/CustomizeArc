package com.stuhua.demo;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * 自定义view，一个弧形进度条
 * Created by llh on 2016/8/9.
 */
public class DrawView extends View {
    //默认值
    public static final float default_startAngle = -45;
    public static final float default_sweepAngle = 90;
    public static final int default_startColor = Color.rgb(62, 62, 62);
    public static final int default_endColor = Color.rgb(255, 255, 255);
    public static final float default_paintWidth = 5;

    private float startAngle;//开始角度，以三点钟方向为起点
    private float sweepAngle;  //扫描过的角度
    private int startColor;   //背景线颜色
    private int endColor;    //进度线颜色
    private float strokeWidth; //
    private float paintWidth;

    private int max; //进度条最大值
    private int progress;//当前进度

    private Paint paint;
    private RectF rectF = new RectF();

    public DrawView(Context context) {
        this(context, null);
    }

    public DrawView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DrawView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.ArcProgress, defStyleAttr, 0);
        initByAttributes(typedArray);
        typedArray.recycle();
        initPaint();
    }

    private void initPaint() {
        paint = new Paint();
        paint.setColor(startColor);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(paintWidth);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeCap(Paint.Cap.ROUND);
    }

    private void initByAttributes(TypedArray typedArray) {
        startAngle = typedArray.getFloat(R.styleable.ArcProgress_startAngle, default_startAngle);
        sweepAngle = typedArray.getFloat(R.styleable.ArcProgress_sweepAngle, default_sweepAngle);
        startColor = typedArray.getColor(R.styleable.ArcProgress_startColor, default_startColor);
        endColor = typedArray.getColor(R.styleable.ArcProgress_endColor, default_endColor);
        paintWidth = typedArray.getFloat(R.styleable.ArcProgress_paintWidth, default_paintWidth);
        strokeWidth = Utils.dp2px(getResources(), 4);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        rectF.set(strokeWidth / 2f, strokeWidth / 2f, width - strokeWidth / 2f, height - strokeWidth / 2f);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float finishedSweepAngle = progress / (float) getMax() * sweepAngle;
        canvas.drawArc(rectF, startAngle, sweepAngle, false, paint);
        paint.setColor(endColor);
        canvas.drawArc(rectF, startAngle, finishedSweepAngle, false, paint);
    }

    @Override
    public void invalidate() {
        super.invalidate();
        initPaint();
    }

    public int getProgress() {
        return progress;
    }

    /**
     * 设置当前进度
     *
     * @param progress
     */
    public void setProgress(int progress) {
        this.progress = progress;
        if (this.progress > getMax()) {
            this.progress %= getMax();
        }
        invalidate();
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        if (max > 0) {
            this.max = max;
            invalidate();
        }
    }

    public static class Utils {
        public static float dp2px(Resources resources, float dp) {
            final float scale = resources.getDisplayMetrics().density;
            return dp * scale + 0.5f;
        }

        public static float sp2px(Resources resources, float sp) {
            final float scale = resources.getDisplayMetrics().scaledDensity;
            return sp * scale;
        }
    }
}

package com.dingar.twok.lottery.presentation.ui;

import android.content.Context;

import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class ArcBackground extends View {

    private final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final Path path = new Path();

    public ArcBackground(Context context) {
        super(context);
    }

    public ArcBackground(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ArcBackground(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (canvas == null)
            return;

        canvas.drawColor(0x000000);
        paint.setColor(0x1DAF3C);

        float desiredWidth = 100F;
        float desiredHeight = 80F;

        float curveYaxis = 0.6f * desiredHeight;
        float curveControlPointY = desiredHeight/1.4f;
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            curveControlPointY = desiredHeight / 1.2f;
        }

        path.moveTo(0f,curveYaxis);
        path.quadTo(desiredWidth/2f,curveControlPointY,
                desiredWidth,curveYaxis);

        path.lineTo(desiredWidth,0f);
        path.lineTo(0f, 0f);
        path.lineTo(0f, curveYaxis);

        /*

        float horizontalOffset = desiredWidth * .8f;
        float top = -desiredHeight * .8f;
        float bottom = desiredHeight;

        RectF ovalRect = new RectF(-horizontalOffset, top, desiredWidth + horizontalOffset, bottom);
        path.lineTo(ovalRect.left, top);
        path.arcTo(ovalRect, 0, 180, false);
        path.setFillType(Path.FillType.INVERSE_EVEN_ODD);

         */

        canvas.drawPath(path,paint);

    }
}

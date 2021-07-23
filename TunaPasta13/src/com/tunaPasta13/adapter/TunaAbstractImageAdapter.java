package com.tunaPasta13.adapter;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.tunaPasta13.widget.TunaStage;

/**
 * This class is an adapter that provides base, abstract class for images  adapter.
 */
public abstract class TunaAbstractImageAdapter extends BaseAdapter {
    /**
     * The width.
     */
    private float width = 0;
    /**
     * The height.
     */
    private float height = 0;
    /**
     * The bitmap map.
     */
    private final Map<Integer, WeakReference<Bitmap>> bitmapMap = new HashMap<Integer, WeakReference<Bitmap>>();

    public TunaAbstractImageAdapter() {
        super();
    }

    /**
     * Set width for all pictures width picture height
     */
    public synchronized void setWidth(final float width) {
        this.width = width;
    }

    /**
     * Set height for all pictures height picture height
     */
    public synchronized void setHeight(final float height) {
        this.height = height;
    }

    @Override
    public final Bitmap getItem(int position) {
        WeakReference<Bitmap> weakBitmapReference = bitmapMap.get(position);
        if (weakBitmapReference != null) {
            Bitmap bitmap = weakBitmapReference.get();
            if (bitmap != null) {
                return bitmap;
            }
        }
        Bitmap bitmap = createBitmap(position);
        bitmapMap.put(position, new WeakReference<Bitmap>(bitmap));
        return bitmap;
    }

    /**
     * Creates new bitmap for the position specified.
     *
     * @param position position
     * @return Bitmap created
     */
    protected abstract Bitmap createBitmap(int position);

    /*
     * (non-Javadoc)
     *
     * @see android.widget.Adapter#getItemId(int)
     */
    @Override
    public final synchronized long getItemId(final int position) {
        return position;
    }

    /*
     * (non-Javadoc)
     *
     * @see android.widget.Adapter#getView(int, android.view.View,
     * android.view.ViewGroup)
     */
    @Override
    public final synchronized ImageView getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            Context context = parent.getContext();
            imageView = new ImageView(context);
            imageView.setLayoutParams(new TunaStage.LayoutParams((int) width, (int) height));
        } else {
            imageView = (ImageView) convertView;
        }
        imageView.setImageBitmap(getItem(position));
        imageView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        return imageView;
    }
}

package com.tunaPasta13.adapter;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;

import com.tunaPasta13.R;


/**
 * This class is an adapter that provides images from a fixed set of resource
 * ids. Bitmaps and ImageViews are kept as weak references so that they can be
 * cleared by garbage collection when not needed.
 * 
 */
public class TunaResourceImageAdapter extends TunaAbstractImageAdapter {

    /** The Constant DEFAULT_LIST_SIZE. */
    private static final int DEFAULT_LIST_SIZE = 20;

    /** The Constant IMAGE_RESOURCE_IDS. */
    private static final List<Integer> IMAGE_RESOURCE_IDS = new ArrayList<Integer>(DEFAULT_LIST_SIZE);

    /** The Constant DEFAULT_RESOURCE_LIST. */
    private static final int[] DEFAULT_RESOURCE_LIST = { 
        R.drawable.viewpagertest_imageview_resource01,
        R.drawable.viewpagertest_imageview_resource02,
        R.drawable.viewpagertest_imageview_resource03,
          R.drawable.viewpagertest_imageview_resource04, 
            };

    /** The bitmap map. */
    private final Map<Integer, WeakReference<Bitmap>> bitmapMap = new HashMap<Integer, WeakReference<Bitmap>>();

    private final Context context;

    /**
     * Creates the adapter with default set of resource images.
     * 
     * @param context
     *            context
     */
    public TunaResourceImageAdapter(final Context context) {
        super();
        this.context = context;
        
        IMAGE_RESOURCE_IDS.clear();
        for (final int resourceId : DEFAULT_RESOURCE_LIST) {
            IMAGE_RESOURCE_IDS.add(resourceId);
        }
        notifyDataSetChanged();
    }

    /*
     * (non-Javadoc)
     * 
     * @see android.widget.Adapter#getCount()
     */
    @Override
    public synchronized int getCount() {
        return IMAGE_RESOURCE_IDS.size();
    }

    /*
     * (non-Javadoc)
     * 
     * @see pl.polidea.coverflow.AbstractCoverFlowImageAdapter#createBitmap(int)
     */
    @Override
    protected Bitmap createBitmap(int position) {
        Bitmap bitmap = ((BitmapDrawable) context.getResources().getDrawable(IMAGE_RESOURCE_IDS.get(position))).getBitmap();
        bitmapMap.put(position, new WeakReference<Bitmap>(bitmap));
        return bitmap;
    }
}
package com.epicodus.djmusicmanager.adapters;


import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.view.View;

import com.epicodus.djmusicmanager.models.Record;
import com.epicodus.djmusicmanager.util.ItemTouchHelperAdapter;
import com.epicodus.djmusicmanager.util.OnStartDragListener;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

public class FirebaseRecordListAdapter extends FirebaseRecyclerAdapter<Record,
        FirebaseRecordViewHolder> implements ItemTouchHelperAdapter {
    private DatabaseReference mRef;
    private OnStartDragListener mOnStartDragListener;
    private Context mContext;

    public FirebaseRecordListAdapter(Class<Record> modelClass, int modelLayout,
                                     Class<FirebaseRecordViewHolder> viewHolderClass, Query ref,
                                     OnStartDragListener onStartDragListener, Context context){
        super(modelClass, modelLayout, viewHolderClass, ref);
        mRef = ref.getRef();
        mOnStartDragListener = onStartDragListener;
        mContext = context;
    }

    @Override
    protected void populateViewHolder(final FirebaseRecordViewHolder viewHolder, Record model, int position){
        viewHolder.bindRecord(model);
        viewHolder.mRecordTitleArtistTextView.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event){
                if(MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN){
                    mOnStartDragListener.onStartDrag(viewHolder);
                }
                return false;
            }
        });
    }
    @Override
    public boolean onItemMove(int fromPosition, int toPosition){
        return false;
    }
    @Override
    public void onItemDismiss(int position){

    }
}

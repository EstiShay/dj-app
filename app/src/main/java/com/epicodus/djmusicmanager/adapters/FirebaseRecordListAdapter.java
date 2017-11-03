package com.epicodus.djmusicmanager.adapters;


import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.view.View;

import com.epicodus.djmusicmanager.models.Record;
import com.epicodus.djmusicmanager.util.ItemTouchHelperAdapter;
import com.epicodus.djmusicmanager.util.OnStartDragListener;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.Collections;

public class FirebaseRecordListAdapter extends FirebaseRecyclerAdapter<Record,
        FirebaseRecordViewHolder> implements ItemTouchHelperAdapter {
    private DatabaseReference mRef;
    private OnStartDragListener mOnStartDragListener;
    private Context mContext;
    private ChildEventListener mChildEventListener;
    private ArrayList<Record> mRecords = new ArrayList<>();

    public FirebaseRecordListAdapter(Class<Record> modelClass, int modelLayout,
                                     Class<FirebaseRecordViewHolder> viewHolderClass, Query ref,
                                     OnStartDragListener onStartDragListener, Context context){
        super(modelClass, modelLayout, viewHolderClass, ref);
        mRef = ref.getRef();
        mOnStartDragListener = onStartDragListener;
        mContext = context;

        mChildEventListener = mRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                mRecords.add(dataSnapshot.getValue(Record.class));
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
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
        Collections.swap(mRecords, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        return false;
    }
    @Override
    public void onItemDismiss(int position){
        mRecords.remove(position);
        getRef(position).removeValue();
    }

    private void setIndexInFirebase(){
        for(Record record : mRecords){
            int index = mRecords.indexOf(record);
            DatabaseReference ref = getRef(index);
            record.setIndex(Integer.toString(index));
            ref.setValue(record);
        }
    }

    @Override
    public void cleanup(){
        super.cleanup();
        setIndexInFirebase();
        mRef.removeEventListener(mChildEventListener);
    }
}

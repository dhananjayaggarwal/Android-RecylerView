package com.example.recyclerview;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class LuggageCollectAdapter extends RecyclerView.Adapter<LuggageCollectViewHolder> {
private static final String TAG = "SportAdapter";
public static final int VIEW_TYPE_EMPTY = 0;
public static final int VIEW_TYPE_NORMAL = 1;

private Callback mCallback;
private List<LuggageCollectModel> mLuggageList;

public LuggageCollectAdapter(List<LuggageCollectModel> luggageList) {
        mLuggageList = luggageList;
        }

public void setCallback(Callback callback) {
        mCallback = callback;
        }

@Override
public void onBindViewHolder(LuggageCollectViewHolder holder, int position) {
    holder.onBind(position);
        }

@Override
public LuggageCollectViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
        case VIEW_TYPE_NORMAL:
        return new ViewHolder(
        LayoutInflater.from(parent.getContext()).inflate(R.layout.luggage_collect_item, parent, false));
        case VIEW_TYPE_EMPTY:
default:
        return new EmptyViewHolder(
        LayoutInflater.from(parent.getContext())
        .inflate(R.layout.luggage_collect_empty_view, parent, false));
        }
        }

@Override
public int getItemViewType(int position) {
        if (mLuggageList != null && mLuggageList.size() > 0) {
        return VIEW_TYPE_NORMAL;
        } else {
        return VIEW_TYPE_EMPTY;
        }
        }

@Override
public int getItemCount() {
        if (mLuggageList != null && mLuggageList.size() > 0) {
        return mLuggageList.size();
        } else {
        return 1;
        }
        }

public void addItems(List<LuggageCollectModel> sportList) {
        mLuggageList.addAll(sportList);
        notifyDataSetChanged();
        }


public interface Callback {
    void onEmptyViewRetryClick();
}


public class ViewHolder extends LuggageCollectViewHolder {

    @BindView(R.id.employeeName)
    TextInputEditText employeeName;
    @BindView(R.id.employeeID)
    TextInputEditText employeeID;
    @BindView(R.id.mobileNumber)
    TextInputEditText mobileNumber;
    @BindView(R.id.tokenNumber)
    TextInputEditText tokenNumber;
    @BindView(R.id.remarks)
    TextInputEditText remarks;
    @BindView(R.id.collectButton)
    Button collectButton;

    public ViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    protected void clear() {
        Log.d("DEBUG", "clear: remarks "+remarks.getText());
        employeeName.setText("");
        employeeID.setText("");
        tokenNumber.setText("");
        mobileNumber.setText("");
        remarks.setText("");
    }

    public void onBind(final int position) {
        Log.d("DEBUG", "onBind: p: "+position);
        super.onBind(position);
        final LuggageCollectModel mLuggage = mLuggageList.get(position);
        if (mLuggage.getEmployeeName() != null) {
            employeeName.setText(mLuggage.getEmployeeName());
        }
        if (mLuggage.getEmployeeID() != null) {
            employeeID.setText(mLuggage.getEmployeeID());
        }
        if (mLuggage.getTokenNumber() != null) {
            tokenNumber.setText(mLuggage.getTokenNumber());
        }
        if (mLuggage.getMobileNumber() != null) {
            mobileNumber.setText(mLuggage.getMobileNumber());
        }
        if (mLuggage.getRemarks() != null) {
            remarks.setText(mLuggage.getRemarks());
        }
        collectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("DEBUG", "onBind: token: "+mLuggage.getTokenNumber());
                Log.d("DEBUG", "onBind: remarks: "+mLuggage.getRemarks());
                Log.d("DEBUG", "onBind: name: "+mLuggage.getEmployeeName());
                Log.d("DEBUG", "onBind: id: "+mLuggage.getEmployeeID());
                Log.d("DEBUG", "onBind: mob: "+mLuggage.getMobileNumber());
            }
        });

        remarks.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                Log.d("DEBUG", "onTextChanged: "+s+": start: "+start+" :before: "+before+" :count: "+count);
            }
            @Override
            public void afterTextChanged(Editable s) {
                Log.d("DEBUG", "afterTextChanged: "+s+" :position: "+position);
                mLuggage.setRemarks(""+s);
//                Log.d("DEBUG", "afterTextChanged: mLuggage: "+mLuggage.getRemarks());
                mLuggageList.set(position, mLuggage);
//                Log.d("DEBUG", "afterTextChanged: mLuggageList: "+ mLuggageList.get(getAdapterPosition()).getRemarks());
                Log.d("DEBUG", "afterTextChanged: mLuggageList.size(): "+ mLuggageList.size());
//                for (int i = 0; i < mLuggageList.size() ; i++) {
//                    Log.d("DEBUG: "+i, ""+mLuggageList.get(i).getRemarks());
//                }
//                notifyItemChanged(position);
            }
        });
    }
}

public class EmptyViewHolder extends LuggageCollectViewHolder {
    @BindView(R.id.tv_message)
    TextView messageTextView;
    @BindView(R.id.buttonRetry)
    TextView buttonRetry;
    EmptyViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
    @Override
    protected void clear() {
    }
}
}

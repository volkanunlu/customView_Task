package com.volkanunlu.ecommerceapp.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.volkanunlu.ecommerceapp.R;
import com.volkanunlu.ecommerceapp.models.AddressModel;

import java.util.List;

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.AddressHolder> {

    Context context;
    List<AddressModel> addressModelList;
    SelectedAddress selectedAddress;
    private  RadioButton selectedRadioBtn;


    public AddressAdapter(Context context, List<AddressModel> addressModelList, SelectedAddress selectedAddress) {
        this.context = context;
        this.addressModelList = addressModelList;
        this.selectedAddress = selectedAddress;
    }

    public class AddressHolder extends RecyclerView.ViewHolder {

        TextView address;
        RadioButton radioButton;

        public AddressHolder(@NonNull View itemView) {
            super(itemView);

            address=itemView.findViewById(R.id.address_add);
            radioButton=itemView.findViewById(R.id.select_address);
        }
    }

    @NonNull
    @Override
    public AddressHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new AddressHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.address_item,parent,false));

    }   

    @Override
    public void onBindViewHolder(@NonNull AddressHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.address.setText(addressModelList.get(position).getAddress());
        holder.radioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                 for (AddressModel address:addressModelList){
                     address.setSelection(false);
                 }

                 addressModelList.get(position).setSelection(true);

                 if (selectedRadioBtn!=null){
                     selectedRadioBtn.setChecked(false);
                 }

                 selectedRadioBtn=(RadioButton) view;
                 selectedRadioBtn.setChecked(true);
                 selectedAddress.setAddress(addressModelList.get(position).getAddress());

            }
        });
    }

    @Override
    public int getItemCount() {
        return addressModelList.size();
    }

    public interface SelectedAddress{
        void setAddress(String address);
    }


}

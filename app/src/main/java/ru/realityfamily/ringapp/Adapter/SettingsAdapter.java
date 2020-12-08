package ru.realityfamily.ringapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ru.realityfamily.ringapp.Logic.NotifySettings;
import ru.realityfamily.ringapp.Logic.RingSettings;
import ru.realityfamily.ringapp.Models.CloudObject;
import ru.realityfamily.ringapp.R;

public class SettingsAdapter extends RecyclerView.Adapter<SettingsAdapter.SettingsViewHolder> {
    List<CloudObject> elements;
    Context context;

    public SettingsAdapter(Context context, List<CloudObject> elements) {
        this.elements = elements;
        this.context = context;
    }

    @NonNull
    @Override
    public SettingsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SettingsViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.settings_element, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SettingsViewHolder holder, int position) {
        holder.elementName.setText(elements.get(position).getName());
        holder.elementRing.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    RingSettings.RingOn(context, elements.get(position).getId());
                } else {
                    RingSettings.RingOff(context, elements.get(position).getId());
                }
            }
        });
        holder.elementNotify.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    NotifySettings.NotifyOn(context, elements.get(position).getId());
                } else {
                    NotifySettings.NotifyOff(context, elements.get(position).getId());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return elements.size();
    }

    class SettingsViewHolder extends RecyclerView.ViewHolder{

        TextView elementName;
        SwitchCompat elementRing;
        SwitchCompat elementNotify;

        public SettingsViewHolder(@NonNull View itemView) {
            super(itemView);

            elementName = itemView.findViewById(R.id.elementName);
            elementRing = itemView.findViewById(R.id.elementRing);
            elementNotify = itemView.findViewById(R.id.elementNotify);
        }
    }
}

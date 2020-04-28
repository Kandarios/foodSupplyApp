package com.example.vorratsschrank.gui;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vorratsschrank.basic.VorratsItem;

import java.util.List;

import de.codecrafters.tableview.TableDataAdapter;

public class VorratTableDataAdapter extends TableDataAdapter<VorratsItem> {

    public VorratTableDataAdapter(Context context, List<VorratsItem> data) {
        super(context, data);
    }

    @Override
    public View getCellView(int rowIndex, int columnIndex, ViewGroup parentView) {
        VorratsItem item = getRowData(rowIndex);
        View renderedView = null;

        switch (columnIndex) {
            case 0:
                TextView titleView = new TextView(this.getContext());
                titleView.setText(item.getName());
                renderedView = titleView;
                break;
            case 1:
                TextView dateView = new TextView(this.getContext());
                dateView.setText(item.getAblaufDatum());
                renderedView = dateView;
                break;
        }

        return renderedView;
    }
}

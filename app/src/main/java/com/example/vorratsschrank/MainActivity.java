package com.example.vorratsschrank;

import android.os.Bundle;

import com.example.vorratsschrank.basic.VorratsItem;
import com.example.vorratsschrank.gui.AddItemDialog;
import com.example.vorratsschrank.gui.DeleteItemDialog;
import com.example.vorratsschrank.gui.VorratTableDataAdapter;
import com.example.vorratsschrank.helper.AblaufdatumComparator;
import com.example.vorratsschrank.helper.NameComparator;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;

import android.view.View;


import de.codecrafters.tableview.SortableTableView;
import de.codecrafters.tableview.listeners.TableDataLongClickListener;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;

public class MainActivity extends AppCompatActivity {

    private static final String[] TABLE_HEADERS = { "Produkt", "Ablaufdatum" };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onAddItemClicked();
            }
        });
        SortableTableView<VorratsItem> tableView = (SortableTableView<VorratsItem>) findViewById(R.id.tableView);
        tableView.setColumnComparator(1, new AblaufdatumComparator());
        tableView.setColumnComparator(0, new NameComparator());
        tableView.setHeaderAdapter(new SimpleTableHeaderAdapter(this, TABLE_HEADERS));
        tableView.addDataLongClickListener(new TableDataLongClickListener<VorratsItem>() {
            @Override
            public boolean onDataLongClicked(int rowIndex, VorratsItem clickedData) {
                onDeleteItemClicked(clickedData);
                return false;
            }
        });
        updateList();
    }


    public void onAddItemClicked () {
        FragmentManager fm = getSupportFragmentManager();
        AddItemDialog alertDialog = new AddItemDialog();
        alertDialog.addSaveListener(new AddItemDialog.SaveItemListener() {
            @Override
            public void onItemSaved(String name, String ablaufDatum) {
                addItem(name, ablaufDatum);
            }
        });
        alertDialog.show(fm, "fragment_alert");
    }

    private void onDeleteItemClicked(final VorratsItem clickedData) {
        FragmentManager fm = getSupportFragmentManager();
        DeleteItemDialog alertDialog = new DeleteItemDialog();
        alertDialog.addDeleteListener(new DeleteItemDialog.DeleteItemListener() {
            @Override
            public void onItemDeleted() {
                deleteItem(clickedData);
            }
        });
        alertDialog.show(fm, "fragment_alert");
    }

    public void updateList() {
        SortableTableView<VorratsItem> tableView = (SortableTableView<VorratsItem>) findViewById(R.id.tableView);
        tableView.setDataAdapter(new VorratTableDataAdapter(this,  VorratsItem.listAll(VorratsItem.class)));
    }

    public void addItem(String name, String ablaufDatum) {
        VorratsItem item = new VorratsItem(name, ablaufDatum);
        item.save();
        updateList();
    }

    public void deleteItem(VorratsItem clickedData) {
        clickedData.delete();
        updateList();
    }

}

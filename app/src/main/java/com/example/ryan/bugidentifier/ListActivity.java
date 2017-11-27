package com.example.ryan.bugidentifier;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Michael R. Roark on 11/25/2017.
 */

public class ListActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayList<Item> listItems;
    public DatabaseHelper databaseHelper;

    Bitmap[] bitmapArray;
    String[] insectArray;
    String[] imageIdArray;

    //-----------------------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_list);
        listView = findViewById(R.id.list_view);
        databaseHelper = CameraActivity.databaseHelper;
        listItems = new ArrayList<>();
        databaseHelper.getAll(listItems);

        int numberOfItems = listItems.size();
        bitmapArray = new Bitmap[numberOfItems];
        insectArray = new String[numberOfItems];
        imageIdArray = new String[numberOfItems];
        for (int i = 0; i < numberOfItems; i++) {
            bitmapArray[i] = listItems.get(i).getImage();
            insectArray[i] = listItems.get(i).getInsectType();
            imageIdArray[i] = listItems.get(i).getImageId();
        }

        final CustomListAdapter adapter = new CustomListAdapter(this, imageIdArray, insectArray, bitmapArray);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                PassingTable passer = PassingTable.getInstance();
                passer.passingItem = listItems.get(+position);
                Intent intent = new Intent(ListActivity.this, InspectActivity.class);
                startActivity(intent);
            }
        });

    }// end of onCreate()

    @Override
    protected void onResume() {
        super.onResume();
    }// end of onResume()

    @Override
    protected void onPause() {
        super.onPause();
    }// end of oPause()

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }// end of onDestroy()
//-----------------------------------------------------------------------------
}

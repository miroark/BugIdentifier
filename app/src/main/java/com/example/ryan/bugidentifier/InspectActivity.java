package com.example.ryan.bugidentifier;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Ryan on 11/26/2017.
 */

public class InspectActivity extends AppCompatActivity {
    private PassingTable passer = PassingTable.getInstance();
    private Item selectedItem;
    private ImageView imageView;
    private TextView imageIdTextView;
    private TextView insectIdTextView;
    private Button correctionButton;

    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_inspect);
        selectedItem = passer.passingItem;

        imageView = findViewById(R.id.inspected_image);
        imageView.setImageBitmap(selectedItem.getImage());

        imageIdTextView = findViewById(R.id.text_image_id);
        imageIdTextView.setText(selectedItem.getImageId());

        insectIdTextView = findViewById(R.id.text_insect_id);
        insectIdTextView.setText(selectedItem.getInsectType());

        correctionButton = findViewById(R.id.correction_button);
        assert correctionButton != null;
        correctionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(InspectActivity.this, "Sorry, that functionality isn't currently available.", Toast.LENGTH_LONG).show();
                Toast.makeText(InspectActivity.this, "Infrastructure assembly is underway!", Toast.LENGTH_LONG).show();

            }
        }); //end of button listener
    }// end of onCreate()
}

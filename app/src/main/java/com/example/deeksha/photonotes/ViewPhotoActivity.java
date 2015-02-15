package com.example.deeksha.photonotes;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;


public class ViewPhotoActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_photo);

        Intent intent = getIntent();
        String caption = intent.getStringExtra("PhotoCaption");
        displayCaption(caption);
        Uri location = Uri.parse(intent.getStringExtra("PhotoLocation"));
        displayPhoto(getPath(getApplicationContext(), location));

    }


    /*
    DisplayCaption on ListView Activity
     */
    private void displayCaption(String caption)
    {
        TextView displayCaption = (TextView) findViewById(R.id.displayCaption);
        displayCaption.setText(caption);
    }

    /*
    Renders photo ListView Activity
     */
    private void displayPhoto(String location) {
        File imageFile = new File(location);
        if(imageFile.exists())
        {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 20;
            Bitmap bitmap = BitmapFactory.decodeFile(location,options);

            ImageView displayImage = (ImageView) findViewById(R.id.displayPhoto);
            displayImage.setImageBitmap(bitmap);
        }
    }

    /*
    Get Absolute path for the image
     */
    public String getPath(Context context, Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] mediaData = { MediaStore.Images.Media.DATA };
            cursor = context.getContentResolver().query(contentUri,  mediaData, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_photo, menu);
        getMenuInflater().inflate(R.menu.options_menu_add_button, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }
        if(id == R.id.addPhoto)
        {
            Intent intent = new Intent(this, AddPhotoActivity.class);
            startActivity(intent);
        }
        if(id == R.id.action_uninstall)
        {
            Uri packageURI = Uri.parse("package:com.example.deeksha.photonotes");
            Intent intent = new Intent(Intent.ACTION_DELETE,packageURI);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}

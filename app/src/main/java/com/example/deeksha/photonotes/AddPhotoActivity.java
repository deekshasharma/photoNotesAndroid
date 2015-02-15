package com.example.deeksha.photonotes;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class AddPhotoActivity extends ActionBarActivity {

    private Button clickPhoto;
    private Button savePhoto;
    private EditText caption;
    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
    private Uri fileUri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_photo);

        clickPhoto = (Button) findViewById(R.id.clickPhoto);
        savePhoto = (Button) findViewById(R.id.savePhoto);
        clickPhotoButtonListener();
        savePhotoButtonListener();
    }


    /*
   Listens to ClickPhoto Button on ViewPhotoActivity.
    */
    private void clickPhotoButtonListener() {
        clickPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // create an intent to take picture and return control to the calling application
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
                onActivityResult(CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE, RESULT_OK, intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                fileUri = data.getData();
                Toast.makeText(this, "Image location is: " + data.getData(), Toast.LENGTH_SHORT).show();
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Image capture cancelled", Toast.LENGTH_SHORT).show();
            }
        }
    }


    /*
       Listens to SavePhoto Button on ViewPhotoActivity.
     */
    private void savePhotoButtonListener() {
        savePhoto.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View v) {
                                             caption = (EditText) findViewById(R.id.caption);
                                             Photo photo = new Photo();
                                             photo.setPHOTO_CAPTION(caption.getText().toString());
                                             photo.setPHOTO_LOCATION(fileUri);
                                             PhotoDBManager manager = new PhotoDBManager(getApplicationContext());
                                             try
                                             {
                                                 manager.insertToDatabase(photo);
                                                 Intent intent = new Intent(getApplicationContext(), ListActivity.class);
                                                 startActivity(intent);
                                             }catch (NullPointerException e)
                                             {
                                                 Log.d("Null Pointer Exception", " Photo location is missing");
                                                 Toast.makeText(getApplicationContext(), "Please ClickPhoto before saving!",Toast.LENGTH_SHORT).show();
                                             }

                                         }
                                     }
        );
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_photo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
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

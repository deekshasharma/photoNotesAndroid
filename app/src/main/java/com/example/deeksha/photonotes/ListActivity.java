package com.example.deeksha.photonotes;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class ListActivity extends ActionBarActivity {

    List<Photo> photoListFromDb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        ListView captionList = (ListView) findViewById(R.id.captionList);
        final List<String> allCaptions = getAllCaptions();
        captionList.setAdapter(new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, allCaptions));

        captionList.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(getApplicationContext(),ViewPhotoActivity.class);
                intent.putExtra("PhotoCaption", allCaptions.get(position));
                intent.putExtra("PhotoLocation", photoListFromDb.get(position).getPHOTO_LOCATION().toString());
                intent.setType("text/plain");
                startActivity(intent);

            }
        });
    }

    private List<String> getAllCaptions()
    {
        PhotoDBManager manager = new PhotoDBManager(getApplicationContext());

        photoListFromDb = manager.getFromDB();

        List<String> allCaptions = new ArrayList<>();
        for(Photo photo: photoListFromDb)
        {
            allCaptions.add(photo.getPHOTO_CAPTION());
        }
        return allCaptions;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list, menu);
        getMenuInflater().inflate(R.menu.options_menu_add_button,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        if(id == R.id.addPhoto) {

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

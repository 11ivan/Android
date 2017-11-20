package com.example.icastillo.activityforresultcamara;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.support.v4.content.FileProvider.getUriForFile;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    final int REQUEST_IMAGE_CAPTURE=1;
    Button botonCamara;
    ImageView foto;

    String mCurrentPhotoPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botonCamara=(Button) findViewById(R.id.btnCamara);
        foto=(ImageView) findViewById(R.id.foto);
        botonCamara.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        takePictureIntent();
    }

    public void takePictureIntent(){
        Intent takePicture=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(takePicture, REQUEST_IMAGE_CAPTURE);
        /*if(takePicture.resolveActivity(getPackageManager()) != null){
            startActivityForResult(takePicture, REQUEST_IMAGE_CAPTURE);

            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File

            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = getUriForFile(this,"com.example.android.fileprovider",photoFile);
                takePicture.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePicture, REQUEST_IMAGE_CAPTURE);
        }
            //Uri photoURI = getUriForFile(this,"com.example.android.fileprovider",File.createTempFile());
        }*/

    }



    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        //File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File storageDir = new File(getFilesDir(), "Pictures");
        //File image = File.createTempFile(imageFileName,".jpg", storageDir );
        File image=new File(storageDir, imageFileName+".jpg");
        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Bundle extras=null;
        Bitmap imageBitMap=null;

        if (requestCode == REQUEST_IMAGE_CAPTURE) {
            if(resultCode == Activity.RESULT_OK){
                extras=data.getExtras();
                imageBitMap=(Bitmap)extras.get("data");
                foto.setImageBitmap(imageBitMap);
                //foto.setImageURI(data.getData());

            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }//onActivityResult


}

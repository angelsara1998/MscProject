package com.example.classifierapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Button;
import android.provider.MediaStore;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import android.graphics.Bitmap;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;




public class GetImage extends AppCompatActivity  {
    public ActivityResultLauncher<Intent> CaptImgLauncher;
    public ActivityResultLauncher<Intent> UploadImgLauncher;
    boolean check=false;

    String imgpath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_image);

        ImageView imageView = findViewById(R.id.imageView);
        Button cameraButton = findViewById(R.id.cameraButton);
        Button uploadButton = findViewById(R.id.uploadButton);
        Button nextButton = findViewById(R.id.nextButton);




        CaptImgLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        if (result.getData() != null) {
                            Bitmap capturedImage = (Bitmap) result.getData().getExtras().get("data");
                            imageView.setImageBitmap(capturedImage);

                            File imgloc=saveImageToFile(capturedImage, "captured_image.PNG");
                            imgpath = imgloc.getAbsolutePath();

                            check=true;
                        }
                    }
                });

        UploadImgLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        if (result.getData() != null) {
                            Uri imageUri = result.getData().getData();
                            try{
                                Bitmap uploadedImage = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                                imageView.setImageBitmap(uploadedImage);
                                File imgloc=saveImageToFile(uploadedImage, "uploaded_image.PNG");
                                imgpath = imgloc.getAbsolutePath();
                                check=true;
                            }
                            catch(IOException err){
                                err.printStackTrace();
                            }


                        }
                    }
                });




        cameraButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    CaptImgLauncher.launch(takePictureIntent);
                }



            }
        });

        uploadButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent uploadPictureIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                UploadImgLauncher.launch(uploadPictureIntent);
            }
        });




        nextButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(check==true)
                    {Intent intent = new Intent(GetImage.this, CheckImage.class);
                    intent.putExtra("filePathExtra", imgpath);
                    startActivity(intent);}

            }
        });



        // Your initialization or setup code here
    }

    public File saveImageToFile(Bitmap bitmap, String fileName) {
        File file = new File(getFilesDir(), fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.flush();
            fos.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }
}
